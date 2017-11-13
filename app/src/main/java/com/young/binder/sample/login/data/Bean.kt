package com.young.binder.sample.login.data

import com.young.binder.BinderCloud

/**
 * Created by young on 2017/11/10.
 */

open class Bean(binderCloud: BinderCloud)

class User(val binderCloud: BinderCloud,
           val name: String,
           val age: Int,
           val address: String,
           icon: String? = "") : Bean(binderCloud) {
    var icon = icon
        set(value) {
            field = value
            println("do the set function")
            binderCloud.notifyDataChanged("icon")
        }
}