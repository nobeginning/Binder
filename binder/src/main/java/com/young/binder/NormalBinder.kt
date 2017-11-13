package com.young.binder

import android.view.View
import com.young.binder.controller.Controller

/**
 * Created by young on 2017/11/10.
 */
interface NormalBinder<in T : Controller, in R : BinderCloud> {
    fun bind(view: View, activityBinder: T, dataBinder: R)
}