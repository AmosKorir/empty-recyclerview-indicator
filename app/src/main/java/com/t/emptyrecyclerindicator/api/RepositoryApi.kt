package com.t.emptyrecyclerindicator.api

import com.t.emptyrecyclerindicator.models.Job
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Korir on 2/2/20.
 * amoskrr@gmail.com
 */
interface RepositoryApi {
  @GET("positions.json?page=1&search=code")
  fun getJobs(): Call<List<Job>>

}