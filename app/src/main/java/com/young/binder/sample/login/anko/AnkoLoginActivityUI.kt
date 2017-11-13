package com.young.binder.sample.login.anko

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.young.binder.*
import com.young.binder.sample.login.controller.LoginController
import com.young.binder.sample.login.data.LoginBinderCloud
import com.young.binder.anko.AnkoBinderComponent
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by young on 2017/11/10.
 */
class AnkoLoginActivityUI : AnkoBinderComponent<LoginController, LoginBinderCloud> {
    override fun createView(ui: AnkoContext<LoginController>, dataBinder: LoginBinderCloud): View = with(ui) {
        val viewBindData = object : BinderCloud() {
            var username: String = ""
                set(value) {
                    field = value
                    notifyDataChanged("input")
                }
            var password: String = ""
                set(value) {
                    field = value
                    notifyDataChanged("input")
                }
        }
        val ID_LOGIN_AREA = 0x1001
        relativeLayout {
            verticalLayout {
                id = ID_LOGIN_AREA
                linearLayout {
                    textView("用户名：") {
                        gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
                    }.lparams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                    editText {
                        inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                        hint = "请输入用户名"
                        addTextChangedListener(object : TextWatcher {
                            override fun afterTextChanged(s: Editable?) {
                                viewBindData.username = s!!.toString()
                            }

                            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                            }

                            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                            }
                        })
                    }.lparams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 3f)
                }
                linearLayout {
                    textView("密  码：") {
                        gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
                    }.lparams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                    editText {
                        inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
                        hint = "请输入密码"
                        addTextChangedListener(object : TextWatcher {
                            override fun afterTextChanged(s: Editable?) {
                                viewBindData.password = s!!.toString()
                            }

                            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                            }

                            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                            }
                        })
                    }.lparams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 3f)
                }
                button("登录") {
                    onClick {
                        owner.login(viewBindData.username, viewBindData.password)
                    }
                    bind(viewBindData, "input") {
                        isEnabled = viewBindData.username.isNotEmpty() && viewBindData.password.isNotEmpty()
                    }
                }
            }.lparams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) {
                horizontalMargin = dip(8)
            }

            textView {
                bindText(dataBinder, "loginSuccess", false) {
                    "Hello! ${dataBinder.user?.name}, 您的信息如下：\n" +
                            "用户名：${dataBinder.user?.name}\n" +
                            "年龄：${dataBinder.user?.age}\n" +
                            "地址：${dataBinder.user?.address}"
                }
            }.lparams {
                below(ID_LOGIN_AREA)
                centerHorizontally()
                topMargin = 20
            }

            progressBar {
                bindVisibility(dataBinder, "loginStart") {
                    when (dataBinder.loginDoing) {
                        true -> Visibility.VISIBLE
                        else -> Visibility.GONE
                    }
                }
            }.lparams {
                centerInParent()
            }
        }
    }
}