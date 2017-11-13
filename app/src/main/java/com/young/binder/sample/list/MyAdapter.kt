package com.young.binder.sample.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.young.binder.*
import com.young.binder.sample.R
import kotlinx.android.synthetic.main.cell_list.view.*

/**
 * Created by young on 2017/11/13.
 */
class MyAdapter(private val binderCloud: ListActivityBinderCloud,
                private val controller: ListActivityController) : BaseAdapter() {

    private val adapterBinderCloud: AdapterBinderCloud = AdapterBinderCloud()


    override fun getItem(position: Int): Item {
        println("执行getItem $position")
        return binderCloud.dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return binderCloud.dataSource.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        println("执行getView $position")
        val view: View
//        val holder:Holder
        if (convertView == null) {
            view = LayoutInflater.from(controller.getOwnerContext()).inflate(R.layout.cell_list, parent, false)
//            holder = Holder()
//            holder.ivIcon = view.ivIcon
//            holder.tvTitle = view.tvTitle
//            holder.tvDesc = view.tvDesc
//            holder.ivStatus = view.ivStatus
//            view.tag = holder
        } else {
            view = convertView
//            holder = view.tag as Holder
        }
//        Glide.with(controller.getOwnerActivity()).load(getItem(position).icon).into(holder.ivIcon)
//        holder.tvTitle!!.text = getItem(position).title
//        holder.tvDesc!!.text = getItem(position).desc
//        if (binderCloud.isChoosing) {
//            holder.ivStatus!!.visibility = View.VISIBLE
//        } else {
//            holder.ivStatus!!.visibility = View.GONE
//        }

        view.ivIcon.inAdapter().bind(binderCloud, "iconChanged"){
            Glide.with(controller.getOwnerActivity()).load(getItem(position).icon).into(view.ivIcon)
        }
        view.tvTitle.inAdapter().bindText(binderCloud, "titleChanged"){
            println("触发了titleChanged $position")
            getItem(position).title
        }
        view.tvDesc.inAdapter().bindText(binderCloud, "descChanged"){
            getItem(position).description
        }
        view.ivStatus.inAdapter().bindVisibility(binderCloud, "choosing"){
            if (binderCloud.isChoosing){
                Visibility.VISIBLE
            }else{
                Visibility.GONE
            }
        }
//        adapterBinderCloud.positionChanged(view)
        return view
    }

    class Holder(var ivIcon:ImageView?=null, var tvTitle:TextView?=null, var tvDesc:TextView?=null, var ivStatus:ImageView?=null)

}