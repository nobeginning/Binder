package com.young.binder.sample.login.controller

import com.young.binder.controller.ActivityController

/**
 * Created by young on 2017/11/10.
 */
interface LoginController : ActivityController {
    fun login(username: String, password: String)
}