package com.young.binder

import android.view.View
import com.young.binder.controller.Controller

/**
 * Created by young on 2017/11/10.
 */
interface NormalBinder<in T : Controller, in R : AbstractDataCenter> {
    fun bind(view: View, controller: T, dataCenter: R)
}