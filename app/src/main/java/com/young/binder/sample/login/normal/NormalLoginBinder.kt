package com.young.binder.sample.login.normal

import android.app.Activity
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.young.binder.AbstractDataCenter
import com.young.binder.NormalBinder
import com.young.binder.bind
import com.young.binder.lifecycle.bind
import com.young.binder.sample.list.ListActivity
import com.young.binder.sample.login.controller.LoginController
import com.young.binder.sample.login.data.LoginDataCenter
import kotlinx.android.synthetic.main.activity_login.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by young on 2017/11/10.
 */
class NormalLoginBinder : NormalBinder<LoginController, LoginDataCenter> {

    inner class ViewAbstractDataCenter : AbstractDataCenter() {
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

    val viewBinder = ViewAbstractDataCenter()
    private val listener: Any = Any()
    var toast: Toast? = null

    override fun bind(view: View, loginController: LoginController, dataCenter: LoginDataCenter) {
        val context: Activity = loginController.getOwnerActivity()
        var lifecycleOwner: LifecycleOwner? = null
        if (context is LifecycleOwner) {
            lifecycleOwner = context
        }

        dataCenter.loginStatus.observe(lifecycleOwner) {
            view.btnLogin.text = when (it) {
                LoginDataCenter.LoginStatus.LOGIN_DOING -> {
                    "登录中..."
                }
                LoginDataCenter.LoginStatus.LOGIN_COMPLETED -> "登录完成"
                else -> "登录"
            }
        }



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
//        view.btnLogin.bind(viewBinder, "input") {
//            view.btnLogin.isEnabled = viewBinder.username.isNotEmpty() && viewBinder.password.isNotEmpty()
//        }

        view.btnLogin.bind(viewBinder, "input") {
            isEnabled = viewBinder.username.isNotEmpty() && viewBinder.password.isNotEmpty()
        }

        listener.bind(viewBinder, "input") {
            if (viewBinder.username.length >= 2 && viewBinder.password.length >= 4) {
                if (toast != null) {
                    toast!!.cancel()
                }
                toast = Toast.makeText(loginController.getOwnerContext(), "username: ${viewBinder.username}\npassword: ${viewBinder.password}", Toast.LENGTH_SHORT)
                toast?.show()
            }
        }

        view.btnLogin.onClick { loginController.login(viewBinder.username, viewBinder.password) }
        view.tvResult.bind(lifecycleOwner, dataCenter, "loginSuccess", false) {
            text = "Hello! ${dataCenter.user?.name}, 您的信息如下：\n" +
                    "用户名：${dataCenter.user?.name}\n" +
                    "年龄：${dataCenter.user?.age}\n" +
                    "地址：${dataCenter.user?.address}"
        }
        view.progressBar.bind(lifecycleOwner, dataCenter, "loginStart") {
            visibility = when (dataCenter.loginDoing) {
                true -> View.VISIBLE
                else -> View.GONE
            }
        }
        view.ivIcon.bind(lifecycleOwner, dataCenter, "icon", false) {
            Glide.with(view.ivIcon)
                    .load(dataCenter.user?.icon)
                    .into(view.ivIcon)
        }
        view.ivIcon.setOnClickListener {
            ListActivity.launch(loginController.getOwnerActivity())
        }
    }
}