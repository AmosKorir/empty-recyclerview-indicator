package com.t.emptyrecyclerindicator.emptyreclerviewindicator

/**
 * Created by Korir on 1/30/20.
 */
class EmptyViewIndicator {
  private var recyclerViewIndicator = RecyclerViewIndicator()

  companion object {
    private var emptyViewIndicatorInstance: EmptyViewIndicator? = null
    fun instance(): EmptyViewIndicator? {
      emptyViewIndicatorInstance ?: init()
      return this.emptyViewIndicatorInstance
    }

    private fun init() {
      emptyViewIndicatorInstance = EmptyViewIndicator()
    }

  }

  fun build(): RecyclerViewIndicator {
    return recyclerViewIndicator
  }

}