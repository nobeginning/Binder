package com.young.binder

import android.view.View
import java.util.ArrayList

/**
 * Created by young on 2017/11/13.
 */
open class AdapterBinderCloud : BinderCloud() {

    private val adapterEvents = HashMap<View, ArrayList<Event>>()

    public fun addAdapterEvent(convertView: View, event: Event){
        var existList:ArrayList<Event>? = adapterEvents[convertView]
        if (existList == null){
            existList = ArrayList()
            adapterEvents.put(convertView, existList)
        }
        existList.add(event)
    }

    public fun positionChanged(convertView: View){
        adapterEvents[convertView]?.forEach {
            it.changed()
        }
    }

}