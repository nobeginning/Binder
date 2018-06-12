package com.young.binder.lifecycle

import android.arch.lifecycle.ViewModel
import android.support.annotation.CallSuper
import com.young.binder.AbstractDataCenter
import com.young.binder.DataCenter
import com.young.binder.Event
import java.util.ArrayList
import java.util.HashMap

abstract class DataCenterViewModel : ViewModel(), DataCenter {

    private val delegate: DelegateDataCenter = DelegateDataCenter()

    override var debugMode: DataCenter.DebugMode = DataCenter.DebugMode.MODE_NONE

    @CallSuper
    override fun onCleared() {
        destroy()
    }

    @CallSuper
    override fun clear() {
        delegate.clear()
    }

    @CallSuper
    override fun destroy() {
        delegate.destroy()
    }

    override fun remove(any: Any, eventTag: String?) {
        delegate.remove(any, eventTag)
    }

    override fun getBinderMap(): HashMap<String, ArrayList<Event<*>>> {
        return delegate.getBinderMap()
    }

    override fun addEvent(eventTag: String, event: Event<*>) {
        delegate.addEvent(eventTag, event)
    }

    override fun addAdapterEvent(t: Any, eventTag: String, event: Event<*>) {
        delegate.addAdapterEvent(t, eventTag, event)
    }

    override fun notifyDataChanged(eventTag: String) {
        delegate.notifyDataChanged(eventTag)
    }

    inner class DelegateDataCenter : AbstractDataCenter()
}