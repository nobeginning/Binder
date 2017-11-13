package com.young.binder.sample.list

import com.young.binder.controller.ActivityController

/**
 * Created by young on 2017/11/13.
 */
interface ListActivityBiz : ActivityController<ListActivity> {

    fun loadListItems()

}