package com.t.emptyrecyclerindicator

import com.t.emptyrecyclerindicator.api.RepositoryApi
import com.t.emptyrecyclerindicator.models.Job
import okhttp3.OkHttpClient.Builder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Korir on 1/30/20.
 */
class Repository {
  var API_BASE_URL = "https://jobs.github.com/"
  lateinit var repositoryInterface: RepositoryInterface
  /*
  Has no pattern , it is an example
   */

  var httpClient = Builder()
    .addInterceptor { chain ->
      val original = chain.request()
      val request = original.newBuilder()
        .method(original.method(), original.body())
        .build()
      chain.proceed(request)
    }

  var builder = Retrofit.Builder()
    .baseUrl(API_BASE_URL)
    .addConverterFactory(
      GsonConverterFactory.create()
    )

  var retrofit = builder
    .client(
      httpClient.build()
    )
    .build()

  var client: RepositoryApi = retrofit.create<RepositoryApi>(RepositoryApi::class.java)

  fun getSomeContent(state: Boolean) {
    client.getJobs().enqueue(object : Callback<List<Job>> {
      override fun onFailure(call: Call<List<Job>>, t: Throwable) {
      }

      override fun onResponse(call: Call<List<Job>>, response: Response<List<Job>>) {
        if (state) {
          response.body()?.let { repositoryInterface.setRecyclerView(it) }

        } else {
          repositoryInterface.setRecyclerView(ArrayList<Job>())
        }

      }

    })

  }

  interface RepositoryInterface {
    fun setRecyclerView(items: List<Job>)
  }

}