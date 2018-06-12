package com.young.binder.sample.login.data

import com.young.binder.AbstractDataCenter

/**
 * Created by young on 2017/11/10.
 */

open class Bean(dataCenter: AbstractDataCenter?)

class User(private val dataCenter: AbstractDataCenter? = null,
           val name: String,
           val age: Int,
           val address: String,
           icon: String? = "") : Bean(dataCenter) {
    var icon = icon
        set(value) {
            field = value
            println("do the set function")
            dataCenter?.notifyDataChanged("icon")
        }
}