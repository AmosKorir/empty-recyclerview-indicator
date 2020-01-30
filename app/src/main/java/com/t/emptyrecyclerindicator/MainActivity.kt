package com.t.emptyrecyclerindicator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.noContentBtn
import kotlinx.android.synthetic.main.activity_main.recyclerView
import kotlinx.android.synthetic.main.activity_main.someDataBtn

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initView()
  }

  private fun initView() {

    //setup the recycleview

    recyclerView.layoutManager = LinearLayoutManager(this)

    //setup button click listeners
    someDataBtn.setOnClickListener {
      getData(true)
    }
    noContentBtn.setOnClickListener {
      getData(false)
    }
  }

  private fun getData(state: Boolean) {
    var data = Repository().getSomeContent(state)
    var adapter = MyRecyclerViewAdapter(this, data)
    recyclerView.adapter = adapter
  }
}
