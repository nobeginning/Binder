package com.young.binder.sample.login.vm

import android.arch.lifecycle.MutableLiveData
import com.young.binder.lifecycle.Data
import com.young.binder.sample.login.data.User

interface ILoginViewModel {
    enum class LoginStatus {
        NONE, LOGIN_DOING, LOGIN_COMPLETED
    }

    var loginDoing: MutableLiveData<Boolean>
    var user: MutableLiveData<User>
    var loginStatus: Data<LoginStatus>
    fun login(username: String, password: String)
}