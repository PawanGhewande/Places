package com.pawan.places

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pawan.places.adapters.GridRecycleAdapter
import com.pawan.places.db.model.ImageData
import com.pawan.places.viewmodel.ImagesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val imagesViewModel by viewModel<ImagesViewModel>()
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imagesViewModel.getAllImages()

        recyclerView = findViewById<RecyclerView>(R.id.imgLoader)
        imagesViewModel.imagesList
            .observe(this,
                Observer<List<ImageData>> { userPost ->
                    recyclerView.apply {
                        layoutManager = GridLayoutManager(this@MainActivity, 2)
                        adapter = GridRecycleAdapter(applicationContext, userPost)
                    }
                    //Log.e("DATA GATHERED :: ", userPost.toString())
                })
    }


}