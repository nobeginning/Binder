package com.young.binder.controller

import android.app.Activity
import android.content.Context

/**
 * Created by young on 2017/11/13.
 */
interface ActivityController : Controller {
    override fun getContext(): Context {
        return getActivity()
    }

    fun getActivity(): Activity
}