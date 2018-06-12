package com.young.binder.sample.login.controller

import android.app.Activity
import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.util.Log
import com.young.binder.lifecycle.LifecycleController
import com.young.binder.sample.login.data.LoginDataCenter
import com.young.binder.sample.login.data.User
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import java.util.*

/**
 * Created by young on 2017/11/10.
 */
class LoginControllerImpl(
        private val lifecycleOwner: LifecycleOwner,
        private val act: Activity,
        private val dataCenter: LoginDataCenter)
    : LifecycleController(lifecycleOwner, act), LoginController {

    private val random = Random()
    private val provinces = arrayListOf("北京市", "河北省", "安徽省", "湖南省", "辽宁省", "云南省", "山东省", "浙江省")

    private var loginTask: Deferred<User>? = null

    override fun getOwnerActivity(): Activity {
        return act
    }

    override fun getOwnerContext(): Context {
        return super<LifecycleController>.getOwnerContext()
    }

    override fun onDestroyed() {
        Log.d("LoginControllerImpl", "LoginControllerImpl onDestroyed")
        loginTask?.apply {
            println("\n \t isCompleted : $isCompleted" +
                    "\n \t isCompletedExceptionally : $isCompletedExceptionally" +
                    "\n \t isCancelled : $isCancelled" +
                    "\n \t isActive : $isActive")

            if (isActive) {
                cancel()
            }
        }
    }

    override fun login(username: String, password: String) {
        async(UI) {
            dataCenter.loginDoing = true
            dataCenter.loginStatus.value = LoginDataCenter.LoginStatus.LOGIN_DOING
            loginTask = bg {
                Thread.sleep(5000)
                User(dataCenter,
                        username,
                        random.nextInt(100) + 1,
                        provinces[random.nextInt(provinces.size)])
            }
            dataCenter.user = loginTask!!.await()
            dataCenter.user?.icon = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510317751015&di=d3069b19b868e9293403eda672729dc7&imgtype=0&src=http%3A%2F%2Fwww.mobanwang.com%2Ficon%2FUploadFiles_8971%2F200806%2F20080602162136915.png"
            dataCenter.loginDoing = false
            dataCenter.loginStatus.value = LoginDataCenter.LoginStatus.LOGIN_COMPLETED
        }
    }
}