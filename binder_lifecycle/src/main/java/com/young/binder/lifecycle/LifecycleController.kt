package com.young.binder.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import com.young.binder.controller.Controller
import java.lang.ref.WeakReference

abstract class LifecycleController(lifecycleOwner: LifecycleOwner,
                                   context: Context) : Controller {

    private val contextWeakRef: WeakReference<Context> = WeakReference(context)
    private val applicationContext: Context = context.applicationContext

    init {
        lifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy(lifecycleOwner: LifecycleOwner) {
                onDestroyed()
            }
        })
    }

    /**
     * 返回对象创建时传入的Context -- 此Context以弱引用的形式引用
     * 如果弱引用get为空，则返回ApplicationContext
     * 如果弱引用get不为空，则返回传入的Context
     *
     * 也就是说此方法有可能会返回ApplicationContext
     */
    override fun getOwnerContext(): Context {
        val ctx = contextWeakRef.get()
        return ctx ?: applicationContext
    }

    /**
     * 监控生命周期，当传入的生命周期调用onDestroy时，会同步调用此方法
     * 可以在此方法中做一些cancel操作
     */
    abstract fun onDestroyed()
}