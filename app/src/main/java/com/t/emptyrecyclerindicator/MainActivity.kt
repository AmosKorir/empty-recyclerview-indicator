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
    var myApplication = application as MyApplication
    myApplication.getEmptyIndicator().show(this, recyclerView)
    //add indicator

    //setup button click listeners
    someDataBtn.setOnClickListener {
      getData(true)
    }
    noContentBtn.setOnClickListener {
      getData(false)
    }
  }

  override fun onStart() {
    super.onStart()
  }

  private fun getData(state: Boolean) {
    var data = Repository().getSomeContent(state)
    var adapter = MyRecyclerViewAdapter(applicationContext, data)
    recyclerView.adapter = adapter
  }

}
