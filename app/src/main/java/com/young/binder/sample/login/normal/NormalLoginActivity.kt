package com.young.binder.sample.login.normal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.young.binder.DataCenter
import com.young.binder.sample.R
import com.young.binder.sample.login.controller.LoginController
import com.young.binder.sample.login.controller.LoginControllerImpl
import com.young.binder.sample.login.data.LoginDataCenter
import org.jetbrains.anko.contentView

class NormalLoginActivity : AppCompatActivity() {

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, NormalLoginActivity::class.java))
        }
    }

    private val dataBinder = LoginDataCenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataCenter.globalDebugMode = DataCenter.DebugMode.MODE_DETAILED
        setContentView(R.layout.activity_login)
        val loginController: LoginController = LoginControllerImpl(this, this, dataBinder)
        NormalLoginBinder().bind(contentView!!, loginController, dataBinder)
    }
}
