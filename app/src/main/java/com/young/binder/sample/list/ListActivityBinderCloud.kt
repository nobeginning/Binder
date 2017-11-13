package com.young.binder.sample.list

import com.young.binder.BinderCloud

/**
 * Created by young on 2017/11/13.
 */
class ListActivityBinderCloud: BinderCloud() {
    var isChoosing:Boolean = false
        set(value) {
            field = value
            notifyDataChanged("choosing")
        }

    var dataSource:List<Item> = ArrayList()
        set(value) {
            field = value
            notifyDataChanged("dataSetChanged")
        }
}