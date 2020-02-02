package com.t.emptyrecyclerindicator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.t.emptyrecyclerindicator.Repository.RepositoryInterface
import com.noconent.emptyreclerviewindicator.RecyclerViewIndicator
import com.t.emptyrecyclerindicator.models.Job
import kotlinx.android.synthetic.main.activity_main.noContentBtn
import kotlinx.android.synthetic.main.activity_main.recyclerView
import kotlinx.android.synthetic.main.activity_main.someDataBtn

class MainActivity : AppCompatActivity(), RepositoryInterface {
  lateinit var emptyRecyclerIndicator: RecyclerViewIndicator
  lateinit var repository: Repository
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initView()
  }

  private fun initView() {

    emptyRecyclerIndicator = (applicationContext as MyApplication).getEmptyIndicator()

    recyclerView.layoutManager = LinearLayoutManager(this)
    emptyRecyclerIndicator.show(this, recyclerView)
    //add indicator

    //setup button click listeners
    someDataBtn.setOnClickListener {
      emptyRecyclerIndicator.refresh()
      getData(true)
    }
    noContentBtn.setOnClickListener {
      emptyRecyclerIndicator.refresh()
      getData(false)
    }

  }

  override fun onStart() {
    super.onStart()
    repository = Repository()
    repository.repositoryInterface = this
  }

  private fun getData(state: Boolean) {
    repository.getSomeContent(state)
  }

  override fun setRecyclerView(items: List<Job>) {
    var adapter = MyRecyclerViewAdapter(applicationContext, items)
    recyclerView.adapter = adapter
  }

}
