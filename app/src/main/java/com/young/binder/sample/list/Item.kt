package com.young.binder.sample.list

import com.young.binder.BinderCloud

/**
 * Created by young on 2017/11/13.
 */
data class Item(val binderCloud: BinderCloud, val title:String, var desc:String, val icon:String){
    var description = desc
        set(value) {
            field = value
            binderCloud.notifyDataChanged("descChanged")
        }
}