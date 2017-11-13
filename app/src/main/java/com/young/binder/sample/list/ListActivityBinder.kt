package com.young.binder.sample.list

import android.view.View
import com.young.binder.NormalBinder
import com.young.binder.bind
import kotlinx.android.synthetic.main.activity_list.view.*

/**
 * Created by young on 2017/11/13.
 */
class ListActivityBinder: NormalBinder<ListActivityController, ListActivityBinderCloud> {

    override fun bind(view: View, controller: ListActivityController, binderCloud: ListActivityBinderCloud) {
        view.listView.adapter = MyAdapter(binderCloud, controller)
        view.listView.setOnItemLongClickListener {
            parent, view, position, id ->
            binderCloud.isChoosing = true
            true
        }
        view.listView.setOnItemClickListener {
            parent, view, position, id ->
            val item:Item = parent.adapter.getItem(position) as Item
            item.description = "desc after click"
        }
        view.listView.bind(binderCloud, "dataSetChanged"){
            (view.listView.adapter as MyAdapter).notifyDataSetChanged()
        }
    }
}