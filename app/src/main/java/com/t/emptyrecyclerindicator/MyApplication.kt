package com.t.emptyrecyclerindicator

import android.app.Application
import com.t.emptyrecyclerindicator.emptyreclerviewindicator.EmptyViewIndicator
import com.t.emptyrecyclerindicator.emptyreclerviewindicator.RecyclerViewIndicator

/**
 * Created by Korir on 1/30/20.
 */
class MyApplication : Application() {
  private lateinit var emptyViewIndicator: EmptyViewIndicator
  override fun onCreate() {
    super.onCreate()
    emptyViewIndicator = EmptyViewIndicator.instance()!!
  }

  fun getEmptyIndicator(): RecyclerViewIndicator {
    return emptyViewIndicator.build()
      .setCustomLayout(R.layout.custom_layout)
      .setHeight(500)
      .setWidth(400)

  }

}