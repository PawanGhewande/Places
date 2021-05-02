package com.pawan.places

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pawan.places.adapters.GridRecycleAdapter
import com.pawan.places.db.model.ImageData
import com.pawan.places.viewmodel.ImagesViewModel
import com.pawan.places.viewmodel.MyPreferences
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val imagesViewModel by viewModel<ImagesViewModel>()
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        imagesViewModel.getAllImages()

        recyclerView = findViewById<RecyclerView>(R.id.imgLoader)
        val themeBtn = findViewById<Button>(R.id.btn)

        imagesViewModel.imagesList
            .observe(this,
                Observer<List<ImageData>> { userPost ->
                    recyclerView.apply {
                        layoutManager = GridLayoutManager(this@MainActivity, 2)
                        adapter = GridRecycleAdapter(applicationContext, userPost)
                        shimmerFrameLayout.stopShimmerAnimation()
                        shimmerFrameLayout.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                    }
                    //Log.e("DATA GATHERED :: ", userPost.toString())
                })

        checkCurrentTheme()
        themeBtn.setOnClickListener {
            imagesViewModel.changeTheme(this)
        }
    }

    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmerAnimation()
    }

    override fun onPause() {
        shimmerFrameLayout.stopShimmerAnimation()
        super.onPause()
    }

    fun checkCurrentTheme() {
        if (MyPreferences(this).darkMode == 1) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

}