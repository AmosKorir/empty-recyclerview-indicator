package com.t.emptyrecyclerindicator.emptyreclerviewindicator

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import com.t.emptyrecyclerindicator.R
import kotlinx.android.synthetic.main.empty_view.view.emptyRecyProgressbar
import kotlinx.android.synthetic.main.empty_view.view.status

/**
 * Created by Korir on 1/30/20.
 * amoskrr@gmail.com
 */
class RecyclerViewIndicator {

  private lateinit var indicatorLayout: View
  private lateinit var selectedRecyclerView: RecyclerView
  private lateinit var ownerActivity: Activity
  private var window: PopupWindow? = null
  private var emptyViewWidth: Int = 310
  private var emptyViewHeight: Int = 450
  private var emptyViewShowing: Boolean = false

  fun show(activity: Activity, recylerView: RecyclerView) {
    ownerActivity = activity
    selectedRecyclerView = recylerView
    observeViewTree()
  }

  private fun observeViewTree() {
    selectedRecyclerView.viewTreeObserver.addOnGlobalLayoutListener {
      //the recycler has been drawn
      attachEmptyView()
      // listen for data change
      listForDataChange()
    }
  }

  private fun attachEmptyView() {
    indicatorLayout = LayoutInflater.from(ownerActivity).inflate(R.layout.empty_view, null)
    if (window == null) {
      window = PopupWindow(indicatorLayout, emptyViewWidth, emptyViewHeight, false)
    }
    window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    window?.isOutsideTouchable = false
    window?.isClippingEnabled = true
    if (!window?.isShowing!!) {
      window?.showAtLocation(selectedRecyclerView, Gravity.CENTER, 0, 0)
    }
    window?.contentView?.emptyRecyProgressbar?.visibility = View.VISIBLE

  }

  private fun listForDataChange() {
    selectedRecyclerView.adapter?.registerAdapterDataObserver(object : AdapterDataObserver() {
      override fun onChanged() {
        super.onChanged()
        selectedRecyclerView.adapter?.itemCount?.let { recylerViewState(it) }
      }

    })
  }

  fun refresh() {
    window?.contentView?.emptyRecyProgressbar?.visibility = View.VISIBLE
  }

  fun recylerViewState(count: Int) {

    if (count < 1) {
      window?.contentView?.emptyRecyProgressbar?.visibility = View.INVISIBLE
      window?.contentView?.status?.text = "no yoooo data"

    } else {
      window?.dismiss()
    }
  }
}

