# Empty-recyclerView-indicator 

<a href="https://bintray.com/skyways/RecyclerViewEmptyIndicator/RecyclerViewEmptyIndicator/1.0/link"><img src="https://api.bintray.com/packages/skyways/RecyclerViewEmptyIndicator/RecyclerViewEmptyIndicator/images/download.svg?version=1.0"/></a>
Show no content indicator for RecyclerView,

This is a reactive model that observes the recyclerView adapter items. It show "no content " when the number of items
within the recyclerView is zero. During loading process, it shows a progressBar with loading status. **It is not a customView** :smile:

![Alt Text](https://github.com/skyways/empty-recyclerview-indicator/tree/art/ezgif.com-video-to-gif.gif)

##How to use

<p > <h3 style='color:green'>How to use.</h3></p>

### Installation

```groovy
implementation 'com.nocontent.emptyrecyclerindicator:RecyclerViewEmptyIndicator:1.0'

```

### Creation of an Instance

Create an instance of the EmptyViewIndicator within the application class.

```kotlin
class MyApplication : Application() {

private lateinit var emptyViewIndicator: EmptyViewIndicator
override fun onCreate() {
  super.onCreate()
  emptyViewIndicator = EmptyViewIndicator.instance()!!
}

fun getEmptyIndicator(): RecyclerViewIndicator {
  return emptyViewIndicator.build()
}

}
```

### Usage in an activity

```kotlin
  //at the top of activity
 lateinit var emptyRecyclerIndicator: RecyclerViewIndicator

 //anywhere
  emptyRecyclerIndicator = (applicationContext as MyApplication).getEmptyIndicator()

  recyclerView.layoutManager = LinearLayoutManager(this)
  emptyRecyclerIndicator.show(this, recyclerView)

```

### Set RecyclerView Adapter

You need to override a method by registering DataObserver within the adapter as below.

```kotlin
override fun registerAdapterDataObserver(observer: AdapterDataObserver) {
    super.registerAdapterDataObserver(observer)
    val myApplication = context.applicationContext as MyApplication
    myApplication.getEmptyIndicator().recyclerViewState(itemList.size)

  }
```

### Refresh the View

The view need to be refreshed when new data is fetched.

```kotlin
   emptyRecyclerIndicator.refresh()
```

### EmptyRecyclerViewIndicator methods

Most of these methods are optional but essential for view customization.

<table>
<th  style="background-color:yellow;"><h5><strong>Methods</strong></h5></th>
<th  style="background-color:green;"><h5><strong>Use<strong></h5></th>
<tr>
<td font-color=><strong><font color="#000080" >setNoDataExplaination(explaination: String)</font></strong></td>
<td>Used to set the message shown when there is no content</td>
</tr>

<tr>
<td font-color=><Strong><font color="#000080">setLoadingExplaination(loadingStatus: String)</font></strong></td>
<td>Set the loading message</td>
</tr>

<tr>
<td font-color=><strong><font color="#000080">recyclerViewState(count: Int)</font></td></strong>
<td>Pass the items-count within the recyclerView adapter </td>
</tr>

<tr>
<td font-color=><strong><font color="#000080">refresh()</font><strong></td>
<td>Refresh the indicator state when fetching new data for the recyclerView</td>
</tr>
<tr>
<td font-color=><strong><font color="#000080">setWidth(width: Int)</font></strong></td>
<td>Set the width of the window that will be shown</td>
</tr>
<tr>
<td font-color=><strong><font color="#000080">setHeight(height: Int)</font></string></td>
<td>Set the height of the window</td>
</tr>
<tr>
<td font-color=><strong><font color="#000080">setCustomLayout(layout: Int)</font></strong></td>
<td>Set the custom layout</td>
</tr>
<tr>
<td font-color=><strong><font color="#000080">show(activity: Activity, recylerView: RecyclerView)</font></</td>
<td>Used to attach the emptyViewIndicator to the recyclerView</td>
</tr>
</table>
