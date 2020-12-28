package com.example.newseveryday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Method

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(NewsObjectViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NewsAdapter()
        recyclerView.adapter = adapter
        viewModel.listNews.observe(this, Observer {
            list->
            run {
                adapter.items = list
                adapter.context = this
                adapter.notifyDataSetChanged()

            }
        })

        viewModel.fetchData(this)

    }

}