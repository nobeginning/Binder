package com.young.binder.sample.login.data

import com.young.binder.BinderCloud
import com.young.binder.sample.login.data.User

/**
 * Created by young on 2017/11/10.
 */
public class LoginBinderCloud : BinderCloud() {

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