package com.young.binder.controller

import android.app.Activity
import android.content.Context

/**
 * Created by young on 2017/11/13.
 */
interface ActivityController<out T:Activity> : Controller {
    override fun getOwnerContext(): Context {
        return getOwnerActivity()
    }

    fun getOwnerActivity(): T
}