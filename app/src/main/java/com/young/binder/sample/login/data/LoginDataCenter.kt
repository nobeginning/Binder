package com.young.binder.sample.login.data

import com.young.binder.AbstractDataCenter
import com.young.binder.DataCenter
import com.young.binder.lifecycle.Data

/**
 * Created by young on 2017/11/10.
 */
public class LoginDataCenter : AbstractDataCenter() {

    enum class LoginStatus {
        NONE, LOGIN_DOING, LOGIN_COMPLETED
    }

    init {
        debugMode = DataCenter.DebugMode.MODE_DETAILED
    }

    var loginStatus: Data<LoginStatus> = Data(LoginStatus.NONE)

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