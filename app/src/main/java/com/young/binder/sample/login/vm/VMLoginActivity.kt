package com.young.binder.sample.login.vm

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.young.binder.sample.R
import org.jetbrains.anko.contentView

class VMLoginActivity : AppCompatActivity() {

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, VMLoginActivity::class.java))
        }
    }

    private lateinit var vm: ILoginViewModel
    private lateinit var binder: VMLoginBinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        vm = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binder = VMLoginBinder(this, this, contentView!!, vm)
        binder.bind()
    }
}
