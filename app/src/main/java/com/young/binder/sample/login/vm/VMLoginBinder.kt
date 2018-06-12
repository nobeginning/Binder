package com.young.binder.sample.login.vm

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_login.view.*

class VMLoginBinder(val context: Context,
                    val lifecycleOwner: LifecycleOwner,
                    val rootView: View,
                    val loginViewModel: ILoginViewModel) {


    @SuppressLint("SetTextI18n")
    fun bind() {
        setupInvariableViews()

        loginViewModel.loginDoing.observe(lifecycleOwner, Observer {
            rootView.progressBar.visibility = if (it != null && it == true) View.VISIBLE else View.GONE
        })

        loginViewModel.user.observe(lifecycleOwner, Observer {
            it?.run {
                rootView.tvResult.text = "Hello! $name, 您的信息如下：\n" +
                        "用户名：$name\n" +
                        "年龄：$age\n" +
                        "地址：$address"

                Glide.with(rootView.ivIcon)
                        .load(icon)
                        .into(rootView.ivIcon)
                Unit
            }
        })

        loginViewModel.loginStatus.observe(lifecycleOwner) {
            rootView.btnLogin.text = when (it) {
                ILoginViewModel.LoginStatus.LOGIN_DOING -> "登录中..."
                ILoginViewModel.LoginStatus.LOGIN_COMPLETED -> "登录完成"
                else -> "登录"
            }
        }

    }

    private fun setupInvariableViews() {
        val watcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkLoginEnable()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        checkLoginEnable()
        rootView.etUsername.addTextChangedListener(watcher)
        rootView.etPassword.addTextChangedListener(watcher)

        rootView.btnLogin.setOnClickListener {
            loginViewModel.login(rootView.etUsername.text.toString(),
                    rootView.etPassword.text.toString())
        }
    }

    private fun checkLoginEnable() {
        if (rootView.etUsername.text.isNotEmpty()
                && rootView.etPassword.text.isNotBlank()) {
            if (!rootView.btnLogin.isEnabled) {
                rootView.btnLogin.isEnabled = true
            }
        } else {
            rootView.btnLogin.isEnabled = false
        }
    }

}