package com.young.binder

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by young on 2017/11/13.
 */

interface Event {
    fun changed()
}

data class SuperEvent(private val view: View, private val block: () -> Unit) : Event {
    override fun changed() {
        view.apply { block() }
    }
}

data class TextEvent(private val view: TextView, private val block: () -> CharSequence) : Event {
    public override fun changed() {
        view.text = block()
    }
}

data class BitmapEvent(private val view: ImageView, private val block: () -> Bitmap) : Event {
    public override fun changed() {
        view.setImageBitmap(block())
    }
}

data class ImageResourceEvent(private val view: ImageView, private val block: () -> Int) : Event {
    public override fun changed() {
        view.setImageResource(block())
    }
}

data class VisibilityEvent(private val view: View, private val block: () -> Visibility) : Event {
    override fun changed() {
        view.visibility = when (block()) {
            Visibility.VISIBLE -> View.VISIBLE
            Visibility.INVISIBLE -> View.INVISIBLE
            else -> View.GONE
        }
    }
}