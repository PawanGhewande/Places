package com.pawan.places

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pawan.places.adapters.GridRecycleAdapter
import com.pawan.places.db.model.ImageData
import com.pawan.places.viewmodel.ImagesViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val imagesViewModel by viewModel<ImagesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            imagesViewModel.getAllImages();

        var recyclerView = findViewById<RecyclerView>(R.id.imgLoader)
        imagesViewModel.imagesList
            .observe(this,
                Observer <List<ImageData>> { userPost ->
                    recyclerView.apply {
                        layoutManager = GridLayoutManager(this@MainActivity, 2)
                        adapter = GridRecycleAdapter(applicationContext,userPost)
                    }
                    Log.e("DATA GATHERED :: ",userPost.toString())
                })
    }
}