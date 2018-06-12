package com.young.binder.sample.login.vm

import android.arch.lifecycle.MutableLiveData
import com.young.binder.lifecycle.Data
import com.young.binder.lifecycle.DataCenterViewModel
import com.young.binder.sample.login.data.User
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import java.util.*

class LoginViewModel : DataCenterViewModel(), ILoginViewModel {
    override var loginStatus: Data<ILoginViewModel.LoginStatus> = Data(ILoginViewModel.LoginStatus.NONE)
    override var user: MutableLiveData<User> = MutableLiveData()
    override var loginDoing: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loginDoing.value = false
    }

//    var userIcon: String? = null
//        set(value) {
//            field = value
//            notifyDataChanged("icon_changed")
//        }

    private var loginTask: Deferred<User>? = null
    private val random = Random()
    private val provinces = arrayListOf("北京市", "河北省", "安徽省", "湖南省", "辽宁省", "云南省", "山东省", "浙江省")

    override fun login(username: String, password: String) {
        async(UI) {
            loginDoing.value = true
            loginStatus.value = ILoginViewModel.LoginStatus.LOGIN_DOING
            loginTask = bg {
                Thread.sleep(5000)
                User(null,
                        username,
                        random.nextInt(100) + 1,
                        provinces[random.nextInt(provinces.size)])
            }
            user.value = loginTask!!.await().apply {
                icon = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510317751015&di=d3069b19b868e9293403eda672729dc7&imgtype=0&src=http%3A%2F%2Fwww.mobanwang.com%2Ficon%2FUploadFiles_8971%2F200806%2F20080602162136915.png"
            }
//            userIcon = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510317751015&di=d3069b19b868e9293403eda672729dc7&imgtype=0&src=http%3A%2F%2Fwww.mobanwang.com%2Ficon%2FUploadFiles_8971%2F200806%2F20080602162136915.png"
            loginDoing.value = false
            loginStatus.value = ILoginViewModel.LoginStatus.LOGIN_COMPLETED
        }
    }

    override fun onCleared() {
        super.onCleared()
        loginTask?.apply {
            if (isActive) {
                cancel()
            }
        }
    }


}