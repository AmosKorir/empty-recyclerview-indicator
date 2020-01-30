package com.t.emptyrecyclerindicator

import android.app.Application
import com.t.emptyrecyclerindicator.emptyreclerviewindicator.EmptyViewIndicator
import com.t.emptyrecyclerindicator.emptyreclerviewindicator.RecyclerViewIndicator

/**
 * Created by Korir on 1/30/20.
 * amoskrr@gmail.com
 */
class MyApplication : Application() {
  lateinit var emptyViewIndicator: EmptyViewIndicator
  override fun onCreate() {
    super.onCreate()
    emptyViewIndicator = EmptyViewIndicator.instance()!!
  }

  fun getEmptyIndicator(): RecyclerViewIndicator {
    return emptyViewIndicator.recyclerViewIndicator
  }

}