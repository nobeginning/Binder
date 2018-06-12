package com.young.binder.sample.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
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

    private val animationDisplay = AnimationUtils.loadAnimation(controller.getOwnerContext(), R.anim.scale_in)
    private val animationHidden = AnimationUtils.loadAnimation(controller.getOwnerContext(), R.anim.scale_out)

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
        val view: View = convertView ?: LayoutInflater.from(controller.getOwnerContext()).inflate(R.layout.cell_list, parent, false)

        view.ivStatus.bindInAdapter(binderCloud, "choosing") {
            if (binderCloud.isChoosing) {
                if (visibility != View.VISIBLE) {
                    startAnimation(animationDisplay)
                }
                visibility = View.VISIBLE
            } else {
                if (visibility != View.GONE) {
                    startAnimation(animationHidden)
                }
                visibility = View.GONE
            }
        }

        view.ivIcon.bindInAdapter(binderCloud, "iconChanged") {
            Glide.with(controller.getOwnerActivity()).load(getItem(position).icon).into(view.ivIcon)
        }
        view.tvTitle.bindInAdapter(binderCloud, "titleChanged") {
            println("触发了titleChanged $position")
            getItem(position).title
        }
        view.tvDesc.bindInAdapter(binderCloud, "descChanged") {
            getItem(position).description
        }
        return view
    }

//    class Holder(var ivIcon:ImageView?=null, var tvTitle:TextView?=null, var tvDesc:TextView?=null, var ivStatus:ImageView?=null)

}