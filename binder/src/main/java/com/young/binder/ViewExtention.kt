package com.young.binder

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by young on 2017/11/13.
 */


val ID_IN_ADAPTER:Int = 0x10000001

fun <T:View> T.inAdapter(): T {
    setTag(ID_IN_ADAPTER, true)
    return this
}

fun View.bind(binderCloud: BinderCloud, eventTag: String, initValueEnabled: Boolean = true, block: () -> Unit) {
    val event = SuperEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    binderCloud.addEvent(eventTag, event)
    val adapterTag = getTag(ID_IN_ADAPTER)
    if (adapterTag!=null){
        binderCloud.addAdapterEvent(this, eventTag, event)
    }
}

fun TextView.bindText(binderCloud: BinderCloud, eventTag: String, initValueEnabled: Boolean = true, block: () -> CharSequence) {
    val event = TextEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    binderCloud.addEvent(eventTag, event)
    val adapterTag = getTag(ID_IN_ADAPTER)
    if (adapterTag!=null){
        binderCloud.addAdapterEvent(this, eventTag, event)
    }
}

fun View.bindVisibility(binderCloud: BinderCloud, eventTag: String, initValueEnabled: Boolean = true, block: () -> Visibility) {
    val event = VisibilityEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    binderCloud.addEvent(eventTag, event)
    val adapterTag = getTag(ID_IN_ADAPTER)
    if (adapterTag!=null){
        binderCloud.addAdapterEvent(this, eventTag, event)
    }
}

fun ImageView.bindImageBitmap(binderCloud: BinderCloud, eventTag: String, initValueEnabled: Boolean = true, block: () -> Bitmap) {
    val event = BitmapEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    binderCloud.addEvent(eventTag, event)
    val adapterTag = getTag(ID_IN_ADAPTER)
    if (adapterTag!=null){
        binderCloud.addAdapterEvent(this, eventTag, event)
    }
}

fun ImageView.bindImageResource(binderCloud: BinderCloud, eventTag: String, initValueEnabled: Boolean = true, block: () -> Int) {
    val event = ImageResourceEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    binderCloud.addEvent(eventTag, event)
    val adapterTag = getTag(ID_IN_ADAPTER)
    if (adapterTag!=null){
        binderCloud.addAdapterEvent(this, eventTag, event)
    }
}
