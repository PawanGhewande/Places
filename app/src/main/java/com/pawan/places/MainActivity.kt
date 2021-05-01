package com.pawan.places

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pawan.places.adapters.GridRecycleAdapter
import com.pawan.places.viewmodel.ImagesViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val imagesViewModel by viewModel<ImagesViewModel>()
    private lateinit var countriesAdapter: GridRecycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imagesViewModel.getAllImages();
        Log.e("IMAGES:: ",""+imagesViewModel.imagesList.value?.size)
//        var recyclerView = findViewById<RecyclerView>(R.id.imgLoader)
//        recyclerView.apply {
//            layoutManager = GridLayoutManager(this@MainActivity, 2)
//            adapter = GridRecycleAdapter(applicationContext, imagesViewModel.imagesList)
//        }
    }
}