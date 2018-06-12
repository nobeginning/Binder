package com.young.binder

import java.util.*

interface DataCenter {

    enum class DebugMode {
        MODE_NONE, MODE_NORMAL, MODE_DETAILED
    }

    var debugMode: DebugMode

    public fun clear()

    public fun destroy()

    fun remove(any: Any, eventTag: String?)

    fun getBinderMap(): HashMap<String, ArrayList<Event<*>>>

    fun addEvent(eventTag: String, event: Event<*>)

    fun addAdapterEvent(t: Any, eventTag: String, event: Event<*>)

    fun notifyDataChanged(eventTag: String)

}