package com.young.binder.sample.login.data

import com.young.binder.DataCenter

/**
 * Created by young on 2017/11/10.
 */
public class LoginDataCenter : DataCenter() {

    init {
        debugMode = DebugMode.MODE_DETAILED
    }

    var user: User? = null
        set(value) {
            field = value
            notifyDataChanged("loginSuccess")
        }

    var loginDoing: Boolean = false
        set(value) {
            field = value
            notifyDataChanged("loginStart")
        }
}