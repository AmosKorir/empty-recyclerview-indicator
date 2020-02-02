package com.t.emptyrecyclerindicator.emptyreclerviewindicator

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import com.t.emptyrecyclerindicator.R

/**
 * Created by Korir on 1/30/20.
 * amoskrr@gmail.com
 */
class RecyclerViewIndicator {

  private lateinit var indicatorLayout: View
  private lateinit var selectedRecyclerView: RecyclerView
  private lateinit var ownerActivity: Activity
  private lateinit var emptyProgressbar: ProgressBar
  private var emptyImageView: ImageView? = null
  private lateinit var statusTxt: TextView
  private var window: PopupWindow? = null
  private var emptyViewWidth: Int = 310
  private var emptyViewHeight: Int = 450
  private var layoutView: Int = R.layout.empty_view
  private var noContentExplanation: String = "Sorry, No content here"
  private var loadingExplanation: String = "Loading.."

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
    indicatorLayout = LayoutInflater.from(ownerActivity).inflate(layoutView, null)
    if (window == null) {
      window = PopupWindow(indicatorLayout, emptyViewWidth, emptyViewHeight, false)
    }
    statusTxt = window?.contentView?.findViewById(R.id.emptyViewStatus)!!
    emptyProgressbar = window?.contentView?.findViewById(R.id.emptyRecyProgressbar)!!
    emptyProgressbar.visibility = View.VISIBLE
    try {
      emptyImageView = window?.contentView?.findViewById(R.id.emptyImageView)
      emptyImageView?.visibility = View.INVISIBLE
    } catch (exception: Exception) {
    }

    window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    window?.isOutsideTouchable = false
    window?.isClippingEnabled = true
    if (!window?.isShowing!!) {
      window?.showAtLocation(selectedRecyclerView, Gravity.CENTER, 0, 0)
    }
  }

  private fun listForDataChange() {
    selectedRecyclerView.adapter?.registerAdapterDataObserver(object : AdapterDataObserver() {
      override fun onChanged() {
        super.onChanged()
        selectedRecyclerView.adapter?.itemCount?.let { recyclerViewState(it) }
      }
    })
  }

  fun setNoDataExplaination(explaination: String): RecyclerViewIndicator {
    noContentExplanation = explaination
    return this
  }

  fun setLoadingExplaination(loadingStatus: String): RecyclerViewIndicator {
    loadingExplanation = loadingStatus
    return this
  }

  fun setCustomLayout(layout: Int): RecyclerViewIndicator {
    layoutView = layout
    return this
  }

  fun setWidth(with: Int): RecyclerViewIndicator {
    emptyViewWidth = with
    return this
  }

  fun setHeight(height: Int): RecyclerViewIndicator {
    emptyViewWidth = height
    return this
  }

  fun refresh() {
    window?.dismiss()
    statusTxt.text = loadingExplanation
    attachEmptyView()
  }

  fun recyclerViewState(count: Int) {
    emptyProgressbar.visibility = View.INVISIBLE
    if (count < 1) {

      if (emptyImageView != null) {
        emptyImageView?.visibility = View.VISIBLE
      }
      statusTxt.text = noContentExplanation

    } else {

      window?.dismiss()
      window = null
    }
  }
}

