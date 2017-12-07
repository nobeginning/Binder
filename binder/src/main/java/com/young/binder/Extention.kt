package com.young.binder

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by young on 2017/11/13.
 */

@Deprecated("Use #T.bindInAdapter instead")
val ID_IN_ADAPTER:Int = 0x10000001

@Deprecated("Use #T.bindInAdapter instead")
fun <T:View> T.inAdapter(): T {
    setTag(ID_IN_ADAPTER, true)
    return this
}

fun <T, R> T.bind(binderCloud: BinderCloud, eventTag: String, initValueEnabled: Boolean = true, block: T.() -> R) {
    val event = SuperEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    binderCloud.addEvent(eventTag, event)
}

fun <T, R> T.bindInAdapter(binderCloud: BinderCloud, eventTag: String, initValueEnabled: Boolean = true, block: T.() -> R) {
    val event = SuperEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    binderCloud.addAdapterEvent(this!!, eventTag, event)
}

fun <T : View> T.bind(binderCloud: BinderCloud, eventTag: String, initValueEnabled: Boolean = true, block: T.() -> Unit) {
    val event = SuperEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    val adapterTag = getTag(ID_IN_ADAPTER)
    if (adapterTag!=null){
        binderCloud.addAdapterEvent(this, eventTag, event)
    }else {
        binderCloud.addEvent(eventTag, event)
    }
}

fun <T : TextView> T.bindText(binderCloud: BinderCloud, eventTag: String, initValueEnabled: Boolean = true, block: T.() -> CharSequence) {
    val event = TextEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    val adapterTag = getTag(ID_IN_ADAPTER)
    if (adapterTag!=null){
        binderCloud.addAdapterEvent(this, eventTag, event)
    }else {
        binderCloud.addEvent(eventTag, event)
    }
}

fun <T : View> T.bindVisibility(binderCloud: BinderCloud, eventTag: String, initValueEnabled: Boolean = true, block: T.() -> Visibility) {
    val event = VisibilityEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    val adapterTag = getTag(ID_IN_ADAPTER)
    if (adapterTag!=null){
        binderCloud.addAdapterEvent(this, eventTag, event)
    }else {
        binderCloud.addEvent(eventTag, event)
    }
}

fun <T : ImageView> T.bindImageBitmap(binderCloud: BinderCloud, eventTag: String, initValueEnabled: Boolean = true, block: T.() -> Bitmap) {
    val event = BitmapEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    val adapterTag = getTag(ID_IN_ADAPTER)
    if (adapterTag!=null){
        binderCloud.addAdapterEvent(this, eventTag, event)
    }else {
        binderCloud.addEvent(eventTag, event)
    }
}

fun <T : ImageView> T.bindImageResource(binderCloud: BinderCloud, eventTag: String, initValueEnabled: Boolean = true, block: T.() -> Int) {
    val event = ImageResourceEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    val adapterTag = getTag(ID_IN_ADAPTER)
    if (adapterTag!=null){
        binderCloud.addAdapterEvent(this, eventTag, event)
    }else {
        binderCloud.addEvent(eventTag, event)
    }
}
