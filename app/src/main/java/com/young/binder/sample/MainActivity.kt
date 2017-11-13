package com.young.binder.sample

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.young.binder.controller.ActivityController
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity(), ActivityController {
    override fun getActivity(): Activity {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
    }

}
