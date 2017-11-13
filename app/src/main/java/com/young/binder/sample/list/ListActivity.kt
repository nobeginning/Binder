package com.young.binder.sample.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.young.binder.sample.R

class ListActivity : AppCompatActivity() {

    companion object {
        fun launch(context:Context){
            context.startActivity(Intent(context, ListActivity::class.java))
        }
    }


    private val binderCloud:ListActivityBinderCloud = ListActivityBinderCloud()
    private val controller:ListActivityController = ListActivityController(this, binderCloud)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = LayoutInflater.from(this).inflate(R.layout.activity_list, null)
        setContentView(view)
        ListActivityBinder().bind(view, controller, binderCloud)
        controller.loadListItems()
    }
}
