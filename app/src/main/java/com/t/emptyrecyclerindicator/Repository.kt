package com.t.emptyrecyclerindicator

/**
 * Created by Korir on 1/30/20.
 */
class Repository {
  /*
  Has no pattern , it is an example
   */

  fun getSomeContent(state: Boolean): List<String> {
    //hey wait for 3 seconds
    Thread.sleep(3000)

    return if (state) {
      mutableListOf("one", "two", "three", "four")
    } else {
      mutableListOf()
    }
  }

}