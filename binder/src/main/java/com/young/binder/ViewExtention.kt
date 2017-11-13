package com.young.binder

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by young on 2017/11/13.
 */

fun View.bind(binderCloud: BinderCloud, eventTag: String, initValueEnabled: Boolean = true, block: () -> Unit) {
    val event = SuperEvent(this, block)
    if (initValueEnabled) {
        event.changed()
    }
    binderCloud.addEvent(eventTag, event)
}

fun TextView.bindText(binderCloud: BinderCloud, eventTag: String, initValueEnabled: Boolean = true, block: () -> CharSequence) {
    val textEvent = TextEvent(this, block)
    if (initValueEnabled) {
        textEvent.changed()
    }
    binderCloud.addEvent(eventTag, textEvent)
}

fun View.bindVisibility(binderCloud: BinderCloud, eventTag: String, initValueEnabled: Boolean = true, block: () -> Visibility) {
    val visibilityEvent = VisibilityEvent(this, block)
    if (initValueEnabled) {
        visibilityEvent.changed()
    }
    binderCloud.addEvent(eventTag, visibilityEvent)
}

fun ImageView.bindImageBitmap(binderCloud: BinderCloud, eventTag: String, initValueEnabled: Boolean = true, block: () -> Bitmap) {
    val bitmapEvent = BitmapEvent(this, block)
    if (initValueEnabled) {
        bitmapEvent.changed()
    }
    binderCloud.addEvent(eventTag, bitmapEvent)
}

fun ImageView.bindImageResource(binderCloud: BinderCloud, eventTag: String, initValueEnabled: Boolean = true, block: () -> Int) {
    val imageResourceEvent = ImageResourceEvent(this, block)
    if (initValueEnabled) {
        imageResourceEvent.changed()
    }
    binderCloud.addEvent(eventTag, imageResourceEvent)
}
