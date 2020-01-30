package com.t.emptyrecyclerindicator.emptyreclerviewindicator

import android.view.View

/**
 * Created by Korir on 1/30/20.
 */
class EmptyViewIndicator {
  lateinit var emptyViewLayout: View
  var recyclerViewIndicator = RecyclerViewIndicator()

  companion object {
    var emptyViewIndicatorInstance: EmptyViewIndicator? = null
    fun instance(): EmptyViewIndicator? {
      emptyViewIndicatorInstance ?: init()
      return this.emptyViewIndicatorInstance
    }

    private fun init() {
      emptyViewIndicatorInstance = EmptyViewIndicator()

    }
  }

}