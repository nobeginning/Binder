package com.young.binder

import android.os.Handler
import android.os.Looper
import java.util.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.forEach

/**
 * Created by young on 2017/11/13.
 */
abstract class BinderCloud {

    private val binderMap = HashMap<String, ArrayList<Event>>()
    private var mainHandler: Handler? = Handler(Looper.getMainLooper())

    @Deprecated("用不到，不会影响到GC")
    public fun clear() {
        mainHandler = null
        binderMap.clear()
    }

    fun addEvent(eventTag: String, event: Event) {
        var existList: ArrayList<Event>? = binderMap[eventTag]
        if (existList == null) {
            existList = ArrayList()
            binderMap.put(eventTag, existList)
        }
        existList.add(event)
    }

    fun notifyDataChanged(eventTag: String) {
        val existList: ArrayList<Event>? = binderMap[eventTag]
        existList?.forEach {
            mainHandler?.post { it.changed() }
        }
    }
}