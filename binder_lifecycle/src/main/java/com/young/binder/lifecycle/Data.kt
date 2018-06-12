package com.young.binder.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.young.binder.AbstractDataCenter
import com.young.binder.DataCenter
import com.young.binder.Event
import java.util.*

class Data<T>(value: T? = null) {

    var value: T? = value
        set(value) {
            field = value
            delegate.notifyDataChanged(eventTag)
        }

    private val eventTag = "default_event_tag"
    private val delegate: DelegateDataCenter = DelegateDataCenter()

    fun observe(lifecycleOwner: LifecycleOwner?, initValueEnabled: Boolean = true, observer: Observer<T>) {
        if (lifecycleOwner == null
                || lifecycleOwner.lifecycle.currentState == Lifecycle.State.DESTROYED) {
            Log.w("Data observe", "The lifecycle is null or destroyed")
            return
        }
        val event = ObserverWrapperEvent(lifecycleOwner, this, observer)
        lifecycleOwner.lifecycle.addObserver(BinderLifecycleObserver(delegate, event))
        if (initValueEnabled) {
            event.onChanged(this)
        }
        delegate.addEvent(eventTag, event)
    }

    fun observe(lifecycleOwner: LifecycleOwner?, initValueEnabled: Boolean = true, block: (T?) -> Unit) {
        observe(lifecycleOwner, initValueEnabled, Observer {
            block(it)
        })
    }

    fun remove(observer: Observer<T>) {
        val binderMap = delegate.getBinderMap()
        val binderList = binderMap[eventTag]
        binderList?.apply {
            val iterator = iterator()
            while (iterator.hasNext()) {
                val v = iterator.next()
                if (v is Data<*>.ObserverWrapperEvent<*>) {
                    if (v.observer == observer) {
                        iterator.remove()
                    }
                }
            }
        }
    }

    fun remove(lifecycleOwner: LifecycleOwner?) {
        if (lifecycleOwner == null) {
            return
        }
        val binderMap = delegate.getBinderMap()
        val binderList = binderMap[eventTag]
        binderList?.apply {
            val iterator = iterator()
            while (iterator.hasNext()) {
                val v = iterator.next()
                if (v is Data<*>.ObserverWrapperEvent<*>) {
                    if (v.lifecycleOwner == lifecycleOwner) {
                        iterator.remove()
                    }
                }
            }
        }
    }

    private interface ObserverEvent<M> : Event<M> {
        override fun changed() {
            //do nothing
        }

        fun onChanged(m: M)
    }

    private inner class ObserverWrapperEvent<T>(val lifecycleOwner: LifecycleOwner,
                                                val data: Data<T>,
                                                val observer: Observer<T>) : ObserverEvent<Data<T>> {
        override fun onChanged(m: Data<T>) {
            observer.onChanged(m.value)
        }

        override fun getObserver(): Data<T> {
            return data
        }
    }

    private inner class DelegateDataCenter : AbstractDataCenter() {
        init {
            debugMode = DataCenter.DebugMode.MODE_DETAILED
        }

        val tag = "DelegateDataCenter"
        val mainHandler = Handler(Looper.getMainLooper())

        override fun notifyDataChanged(eventTag: String) {
            val binderMap = getBinderMap()

            if (globalDebugMode != DataCenter.DebugMode.MODE_NONE
                    || debugMode != DataCenter.DebugMode.MODE_NONE) {
                Log.d(tag, "notifyDataChanged : $eventTag")
            }
            val existList: ArrayList<Event<*>>? = binderMap[eventTag]
            existList?.forEach {
                if (it !is Data<*>.ObserverWrapperEvent<*>) {
                    return@forEach
                }
                if (globalDebugMode == DataCenter.DebugMode.MODE_DETAILED
                        || debugMode == DataCenter.DebugMode.MODE_DETAILED) {
                    Log.d(tag, "[$eventTag] -> detail notifyDataChanged : $it")
                }
                val event: ObserverWrapperEvent<T> = it as ObserverWrapperEvent<T>
                mainHandler.post { event.onChanged(this@Data) }
            }
        }
    }


}