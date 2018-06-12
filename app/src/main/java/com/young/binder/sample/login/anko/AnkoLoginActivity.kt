package com.young.binder.sample.login.anko

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.young.binder.anko.setContentView
import com.young.binder.sample.login.controller.LoginController
import com.young.binder.sample.login.controller.LoginControllerWithoutLifecycle
import com.young.binder.sample.login.data.LoginDataCenter

class AnkoLoginActivity : AppCompatActivity() {

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, AnkoLoginActivity::class.java))
        }
    }

    private val bindData = LoginDataCenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginController: LoginController = LoginControllerWithoutLifecycle(this, bindData)
        AnkoLoginActivityUI().setContentView(loginController, bindData)
    }

}
