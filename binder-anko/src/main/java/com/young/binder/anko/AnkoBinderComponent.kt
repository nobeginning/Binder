package com.young.binder.anko

import android.view.View
import com.young.binder.AbstractDataCenter
import com.young.binder.DataCenter
import com.young.binder.controller.Controller
import org.jetbrains.anko.AnkoContextImpl

/**
 * Created by Young on 2017/11/8.
 */

fun <T : Controller, R : DataCenter> AnkoBinderComponent<T, R>.setContentView(controller: T, dataCenter: R): View {
    return createView(AnkoContextImpl(controller.getOwnerContext(), controller, true), dataCenter)
}

public interface AnkoBinderComponent<in T : Controller, in R : DataCenter> {
    public abstract fun createView(ui: org.jetbrains.anko.AnkoContext<T>, dataCenter: R): android.view.View
}