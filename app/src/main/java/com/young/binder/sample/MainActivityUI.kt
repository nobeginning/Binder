package com.young.binder.sample

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.young.binder.MATCH_PARENT
import com.young.binder.sample.login.anko.AnkoLoginActivity
import com.young.binder.sample.login.normal.NormalLoginActivity
import com.young.binder.sample.login.vm.VMLoginActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by Young on 2017/11/8.
 */
class MainActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        verticalLayout {
            textView("测试入口") {
                textSize = sp(12).toFloat()
                gravity = Gravity.CENTER
            }.lparams(width = ViewGroup.LayoutParams.MATCH_PARENT) {
                verticalMargin = dip(12)
            }
            button("启动XML模式的登录页面") {
                onClick {
                    NormalLoginActivity.launch(owner)
                }
            }.lparams(width = MATCH_PARENT) {
                horizontalMargin = dip(16)
                verticalMargin = dip(8)
            }
            button("启动Anko模式的登录页面") {
                onClick {
                    AnkoLoginActivity.launch(owner)
                }
            }.lparams(width = MATCH_PARENT) {
                horizontalMargin = dip(16)
                verticalMargin = dip(8)
            }
            button("启动ViewModel架构的登录页面") {
                onClick {
                    VMLoginActivity.launch(owner)
                }
            }.lparams(width = MATCH_PARENT) {
                horizontalMargin = dip(16)
                verticalMargin = dip(8)
            }
        }
    }
}