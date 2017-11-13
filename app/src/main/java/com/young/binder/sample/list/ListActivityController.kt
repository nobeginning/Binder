package com.young.binder.sample.list

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by young on 2017/11/13.
 */
class ListActivityController(private val act: ListActivity, private val binderCloud: ListActivityBinderCloud) : ListActivityBiz {

    override fun loadListItems() {
        async(UI){
            val loading = bg {
                Thread.sleep(2000)
                val item1 = Item(binderCloud, "Title 1", "desc 1", "https://www.baidu.com/img/baidu_jgylogo3.gif")
                arrayListOf(item1,
                        item1.copy(title = "Title 2", desc = "desc 2"),
                        item1.copy(title = "Title 3", desc = "desc 3"),
                        item1.copy(title = "Title 4", desc = "desc 4"),
                        item1.copy(title = "Title 5", desc = "desc 5"),
                        item1.copy(title = "Title 6", desc = "desc 6"),
                        item1.copy(title = "Title 7", desc = "desc 7"),
                        item1.copy(title = "Title 8", desc = "desc 8"),
                        item1.copy(title = "Title 9", desc = "desc 9"),
                        item1.copy(title = "Title 10", desc = "desc 10"),
                        item1.copy(title = "Title 11", desc = "desc 11"),
                        item1.copy(title = "Title 12", desc = "desc 12"),
                        item1.copy(title = "Title 13", desc = "desc 13"),
                        item1.copy(title = "Title 14", desc = "desc 14"),
                        item1.copy(title = "Title 15", desc = "desc 15"),
                        item1.copy(title = "Title 16", desc = "desc 16"),
                        item1.copy(title = "Title 17", desc = "desc 17"),
                        item1.copy(title = "Title 18", desc = "desc 18"),
                        item1.copy(title = "Title 19", desc = "desc 19")
                        )
            }
            binderCloud.dataSource = loading.await()
        }
    }

    override fun getOwnerActivity(): ListActivity {
        return act
    }


}