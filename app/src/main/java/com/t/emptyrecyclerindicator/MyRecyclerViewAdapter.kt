package com.t.emptyrecyclerindicator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.t.emptyrecyclerindicator.MyRecyclerViewAdapter.MyViewHolder
import com.t.emptyrecyclerindicator.models.Job
import kotlinx.android.synthetic.main.recycler_item_layout.view.nameTv
import kotlinx.android.synthetic.main.recycler_item_layout.view.positionTv

/**
 * Created by Korir on 1/30/20.
 * amoskrr@gmail.com
 */
class MyRecyclerViewAdapter() : RecyclerView.Adapter<MyViewHolder>() {

  lateinit var context: Context
  lateinit var itemList: List<Job>

  constructor(cxt: Context, contList: List<Job>) : this() {
    context = cxt
    itemList = contList
  }

  class MyViewHolder(@NonNull itemView: View) : ViewHolder(itemView) {
    var view = itemView
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    return MyViewHolder(
      LayoutInflater.from(context).inflate(
        R.layout.recycler_item_layout,
        parent,
        false
      )
    )
  }

  override fun getItemCount(): Int {
    return itemList.size

  }

  override fun registerAdapterDataObserver(observer: AdapterDataObserver) {
    super.registerAdapterDataObserver(observer)
    val myApplication = context.applicationContext as MyApplication
    myApplication.getEmptyIndicator().recyclerViewState(itemList.size)

  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    holder.view.nameTv.text = itemList[position].company
    holder.view.positionTv.text = position.toString()
  }

}