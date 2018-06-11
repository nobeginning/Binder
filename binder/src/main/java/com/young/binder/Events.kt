package com.young.binder

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by young on 2017/11/13.
 */

interface Event<out T> {
    fun changed()
    fun getObserver(): T
}

data class SuperEvent<T, out R>(private val t: T, private val block: T.() -> R) : Event<T> {
    override fun getObserver(): T {
        return t
    }

    override fun changed() {
        t.block()
    }
}

data class TextEvent<T : TextView>(private val view: T, private val block: T.() -> CharSequence) : Event<T> {
    override fun getObserver(): T {
        return view
    }

    public override fun changed() {
        view.text = view.block()
    }
}

data class BitmapEvent<T : ImageView>(private val view: T, private val block: T.() -> Bitmap) : Event<T> {
    override fun getObserver(): T {
        return view
    }
    public override fun changed() {
        view.setImageBitmap(view.block())
    }
}

data class ImageResourceEvent<T : ImageView>(private val view: T, private val block: T.() -> Int) : Event<T> {
    override fun getObserver(): T {
        return view
    }
    public override fun changed() {
        view.setImageResource(view.block())
    }
}

data class VisibilityEvent<T : View>(private val view: T, private val block: T.() -> Visibility) : Event<T> {
    override fun getObserver(): T {
        return view
    }

    override fun changed() {
        view.visibility = when (view.block()) {
            Visibility.VISIBLE -> View.VISIBLE
            Visibility.INVISIBLE -> View.INVISIBLE
            else -> View.GONE
        }
    }
}