package com.young.binder.anko

import android.view.View
import com.young.binder.BinderCloud
import com.young.binder.controller.Controller
import org.jetbrains.anko.AnkoContextImpl

/**
 * Created by Young on 2017/11/8.
 */

fun <T : Controller, R : BinderCloud> AnkoBinderComponent<T, R>.setContentView(controller: T, bindData: R): View {
    return createView(AnkoContextImpl(controller.getContext(), controller, true), bindData)
}

public interface AnkoBinderComponent<in T : Controller, in R : BinderCloud> {
    public abstract fun createView(ui: org.jetbrains.anko.AnkoContext<T>, bindData: R): android.view.View
}