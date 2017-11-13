package com.young.binder

import android.os.Handler
import android.os.Looper
import android.view.View
import java.util.ArrayList
import kotlin.collections.forEach

/**
 * Created by young on 2017/11/13.
 */
abstract class BinderCloud {

    private val binderMap = HashMap<String, ArrayList<Event>>()
    private val adapterBinderMap = HashMap<String, HashMap<View, Event>>()
    private var mainHandler: Handler? = Handler(Looper.getMainLooper())

    @Deprecated("用不到，不会影响到GC")
    public fun clear() {
        mainHandler = null
        binderMap.clear()
    }

    protected fun getBinderMap():HashMap<String, ArrayList<Event>>{
        return binderMap
    }

    fun addEvent(eventTag: String, event: Event) {
        var existList: ArrayList<Event>? = binderMap[eventTag]
        if (existList == null) {
            existList = ArrayList()
            binderMap.put(eventTag, existList)
        }
        existList.add(event)
    }

    fun addAdapterEvent(view: View, eventTag: String, event: Event){
        var existBinderMap = adapterBinderMap[eventTag]
        if (existBinderMap==null){
            existBinderMap = HashMap()
            existBinderMap.put(view, event)
            adapterBinderMap.put(eventTag, existBinderMap)
        } else {
            existBinderMap.put(view, event)
        }
    }

    fun notifyDataChanged(eventTag: String) {
        val existList: ArrayList<Event>? = binderMap[eventTag]
        existList?.forEach {
            mainHandler?.post { it.changed() }
        }
        val eventsInAdapter:HashMap<View, Event>? = adapterBinderMap[eventTag]
        eventsInAdapter?.values?.forEach {
            mainHandler?.post { it.changed() }
        }
    }
}