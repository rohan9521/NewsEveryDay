package com.example.newseveryday

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class NewsObjectViewModel:ViewModel() {
    private val url = "http://newsapi.org/v2/top-headlines?country=in&apiKey=a5d9663c01f2423a82595f9ab3534711"
    private var _listNews = MutableLiveData<MutableList<NewsObject>>()
    val listNews: LiveData<MutableList<NewsObject>>
    get() = _listNews

     fun fetchData(context: Context){
        var volley = Volley.newRequestQueue(context
        )


        var requestData = JsonObjectRequest(Request.Method.GET,url,null, Response.Listener {
                jsonObjectOne-> run{
            var listNewsObject: MutableList<NewsObject> = mutableListOf()
                var jsonObject = jsonObjectOne.getJSONArray("data")
                for (i in 0 until 20) {
                    var newsObject = NewsObject()
                    newsObject.title = jsonObject.getJSONObject(i).getString("title")
                    newsObject.description =
                        jsonObject.getJSONObject(i).getString("description")
                    newsObject.author = jsonObject.getJSONObject(i).getString("author")

                    newsObject.url = jsonObject.getJSONObject(i).getString("url")
                    newsObject.urlToImage = jsonObject.getJSONObject(i).getString("image")
                    newsObject.publishedAt = jsonObject.getJSONObject(i).getString("published_at")
                    if(listNewsObject.size!=0){
                        if (listNewsObject[listNewsObject.lastIndex].title!=newsObject.title){
                            listNewsObject.add(newsObject)
                        }
                    }else{
                        listNewsObject.add(newsObject)
                    }

                }
            _listNews.value?.clear()
            _listNews.value = listNewsObject
            }


        }, Response.ErrorListener {
                error ->
            Toast.makeText(context,error.toString(),Toast.LENGTH_SHORT).show()
            Log.d("ErrorVolley",error.toString())
        })
        volley.add(requestData)


    }


}