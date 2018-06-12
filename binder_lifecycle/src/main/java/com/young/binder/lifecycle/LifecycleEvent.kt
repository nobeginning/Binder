package com.young.binder.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import com.young.binder.AbstractDataCenter
import com.young.binder.DataCenter
import com.young.binder.Event

class LifecycleEvent<T, out R>(
        lifecycleOwner: LifecycleOwner,
        private val t: T,
        private val block: T.() -> R
) : Event<T> {

    val tag = "LifecycleEvent"

    var isActive: Boolean = false
    var hasBeenCalledWhenStopping = false

    init {
        lifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onStart(lifecycleOwner: LifecycleOwner) {
                isActive = true
                if (hasBeenCalledWhenStopping) {
                    if (AbstractDataCenter.globalDebugMode != DataCenter.DebugMode.MODE_NONE) {
                        Log.d(tag, "The lifecycle is active. " +
                                "There is a saved call request when the lifecycle has been stopped. " +
                                "It will be invoked automatically")
                    }
                    changed()
                    hasBeenCalledWhenStopping = false
                }
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onStop(lifecycleOwner: LifecycleOwner) {
                isActive = false
            }
        })
    }

    override fun changed() {
        if (isActive) {
            if (AbstractDataCenter.globalDebugMode != DataCenter.DebugMode.MODE_NONE) {
                Log.d(tag, "The call request has been invoked.")
            }
            t.block()
        } else {
            if (AbstractDataCenter.globalDebugMode != DataCenter.DebugMode.MODE_NONE) {
                Log.d(tag, "The lifecycle is not active, and the call request has been saved.")
            }
            hasBeenCalledWhenStopping = true
        }
    }

    override fun getObserver(): T {
        return t
    }

}