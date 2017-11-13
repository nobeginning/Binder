package com.young.binder.sample.login.normal

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.bumptech.glide.Glide
import com.young.binder.sample.login.controller.LoginController
import com.young.binder.sample.login.data.LoginBinderCloud
import kotlinx.android.synthetic.main.activity_login.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by young on 2017/11/10.
 */
class NormalLoginBinder : NormalBinder<LoginController, LoginBinderCloud> {

    inner class ViewBinderCloud : BinderCloud() {
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

    val viewBinder = ViewBinderCloud()

    override fun bind(view: View, loginController: LoginController, dataBinder: LoginBinderCloud) {
        view.etUsername.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewBinder.username = s!!.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        view.etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewBinder.password = s!!.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        view.btnLogin.bind(viewBinder, "input") {
            view.btnLogin.isEnabled = viewBinder.username.isNotEmpty() && viewBinder.password.isNotEmpty()
        }
        view.btnLogin.onClick { loginController.login(viewBinder.username, viewBinder.password) }
        view.tvResult.bindText(dataBinder, "loginSuccess", false) {
            "Hello! ${dataBinder.user?.name}, 您的信息如下：\n" +
                    "用户名：${dataBinder.user?.name}\n" +
                    "年龄：${dataBinder.user?.age}\n" +
                    "地址：${dataBinder.user?.address}"
        }
        view.progressBar.bindVisibility(dataBinder, "loginStart") {
            when (dataBinder.loginDoing) {
                true -> Visibility.VISIBLE
                else -> Visibility.GONE
            }
        }
        view.ivIcon.bind(dataBinder, "icon", false) {
            Glide.with(view.ivIcon)
                    .load(dataBinder.user?.icon)
                    .into(view.ivIcon)
        }
    }
}