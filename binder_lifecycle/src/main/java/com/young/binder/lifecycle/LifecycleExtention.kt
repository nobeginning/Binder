package com.young.binder.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import com.young.binder.DataCenter
import com.young.binder.bind
import com.young.binder.bindInAdapter
import com.young.binder.unBindFrom


fun <T, R> T.bind(lifecycleOwner: LifecycleOwner?,
                  dataCenter: DataCenter,
                  eventTag: String,
                  initValueEnabled: Boolean = true,
                  block: T.() -> R) {
    if (lifecycleOwner == null) {
        bind(dataCenter, eventTag, initValueEnabled, block)
        return
    }
    if (lifecycleOwner.lifecycle.currentState == Lifecycle.State.DESTROYED) {
        Log.w("bindWithLifecycle", "The lifecycle has destroyed")
        return
    }
    val event = LifecycleEvent(lifecycleOwner, this, block)
    lifecycleOwner.lifecycle.addObserver(BinderLifecycleObserver(dataCenter, event))
    if (initValueEnabled) {
        event.changed()
    }
    dataCenter.addEvent(eventTag, event)
}


fun <T, R> T.bindInAdapter(lifecycleOwner: LifecycleOwner?,
                           dataCenter: DataCenter,
                           eventTag: String,
                           initValueEnabled: Boolean = true,
                           block: T.() -> R) {
    if (lifecycleOwner == null) {
        bindInAdapter(dataCenter, eventTag, initValueEnabled, block)
        return
    }
    if (lifecycleOwner.lifecycle.currentState == Lifecycle.State.DESTROYED) {
        Log.w("bindWithLifecycle", "bindAdapterWithLifecycle : The lifecycle has destroyed")
        return
    }
    val event = LifecycleEvent(lifecycleOwner, this, block)
    lifecycleOwner.lifecycle.addObserver(BinderLifecycleObserver(dataCenter, event))
    if (initValueEnabled) {
        event.changed()
    }
    dataCenter.addAdapterEvent(this!!, eventTag, event)
}

class BinderLifecycleObserver(var dataCenter: DataCenter?,
                              var event: LifecycleEvent<*, *>?) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(lifecycleOwner: LifecycleOwner) {
        if (event != null && dataCenter != null) {
            event?.getObserver()?.unBindFrom(dataCenter!!)
        }
        dataCenter = null
        event = null
    }
}