package com.example.testingkoinapplication.ui.main.view

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testingkoinapplication.R
import com.example.testingkoinapplication.data.api.model.User
import com.example.testingkoinapplication.ui.main.viewmodel.MainViewModel
import com.example.testingkoinapplication.utils.Status
import com.mindorks.framework.mvvm.ui.main.adapter.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel:MainViewModel by viewModel()
    private lateinit var adapter:MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUi()
        setupObserver()

    }
    private fun setupUi(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver()
    {

        mainViewModel.users.observe(this, Observer {
            when(it.status)
            {
                Status.LOADING ->
                {
                    progressBar.visibility=View.VISIBLE
                    recyclerView.visibility=View.GONE
                }

                Status.SUCCESS ->
                {

                    progressBar.visibility=View.GONE
                    recyclerView.visibility=View.VISIBLE
                    it.data?.let {
                        data->renderList(data)
                    }
                }

                Status.ERROR->
                {
                    progressBar.visibility=View.GONE
                   Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun renderList(data: List<User>) {

        adapter.addData(data)
        adapter.notifyDataSetChanged()
    }
}