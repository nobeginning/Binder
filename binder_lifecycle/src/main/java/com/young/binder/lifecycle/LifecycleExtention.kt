package com.young.binder.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import com.young.binder.BinderCloud
import com.young.binder.unBindFrom


fun <T, R> T.bindWithLifecycle(lifecycleOwner: LifecycleOwner,
                               binderCloud: BinderCloud,
                               eventTag: String,
                               initValueEnabled: Boolean = true,
                               block: T.() -> R) {
    if (lifecycleOwner.lifecycle.currentState == Lifecycle.State.DESTROYED) {
        Log.w("bindWithLifecycle", "The lifecycle has destroyed")
        return
    }
    val event = LifecycleEvent(lifecycleOwner, this, block)
    lifecycleOwner.lifecycle.addObserver(BinderLifecycleObserver(binderCloud, event))
    if (initValueEnabled) {
        event.changed()
    }
    binderCloud.addEvent(eventTag, event)
}

class BinderLifecycleObserver(var binderCloud: BinderCloud?,
                              var event: LifecycleEvent<*, *>?) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(lifecycleOwner: LifecycleOwner) {
        if (event != null && binderCloud != null) {
            event?.getObserver()?.unBindFrom(binderCloud!!)
        }
        binderCloud = null
        event = null
    }
}