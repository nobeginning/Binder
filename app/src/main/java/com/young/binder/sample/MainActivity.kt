package com.young.binder.sample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.young.binder.controller.ActivityController
import com.young.binder.service.TimeService
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity(), ActivityController<Activity> {
    override fun getOwnerActivity(): Activity {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
//        startService(Intent(this, TimeService::class.java))
    }

}
