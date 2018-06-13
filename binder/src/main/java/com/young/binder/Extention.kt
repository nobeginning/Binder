package com.young.binder

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by young on 2017/11/13.
 */

fun <T : DataCenter> T.observeEvent(eventTag: String, block: () -> Unit) {
    val event = BlockEvent(block)
    addEvent(eventTag, event)
}

fun <T, R> T.bind(dataCenter: DataCenter, eventTag: String, initValueEnabled: Boolean = true, block: T.() -> R) {
    val event = SuperEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    dataCenter.addEvent(eventTag, event)
}

fun <T> T.unBindFrom(dataCenter: DataCenter, eventTag: String? = null) {
    dataCenter.remove(this as Any, eventTag)
}

fun <T, R> T.bindInAdapter(dataCenter: DataCenter, eventTag: String, initValueEnabled: Boolean = true, block: T.() -> R) {
    val event = SuperEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    dataCenter.addAdapterEvent(this!!, eventTag, event)
}

fun <T : View> T.bind(dataCenter: DataCenter, eventTag: String, initValueEnabled: Boolean = true, block: T.() -> Unit) {
    val event = SuperEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    dataCenter.addEvent(eventTag, event)
}

fun <T : TextView> T.bindText(dataCenter: DataCenter, eventTag: String, initValueEnabled: Boolean = true, block: T.() -> CharSequence) {
    val event = TextEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    dataCenter.addEvent(eventTag, event)
}

fun <T : View> T.bindVisibility(dataCenter: DataCenter, eventTag: String, initValueEnabled: Boolean = true, block: T.() -> Visibility) {
    val event = VisibilityEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    dataCenter.addEvent(eventTag, event)
}

fun <T : ImageView> T.bindImageBitmap(dataCenter: DataCenter, eventTag: String, initValueEnabled: Boolean = true, block: T.() -> Bitmap) {
    val event = BitmapEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    dataCenter.addEvent(eventTag, event)
}

fun <T : ImageView> T.bindImageResource(dataCenter: DataCenter, eventTag: String, initValueEnabled: Boolean = true, block: T.() -> Int) {
    val event = ImageResourceEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    dataCenter.addEvent(eventTag, event)
}
