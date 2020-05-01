package com.electron.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.electron.R
import com.electron.database.entity.MyApps
import com.electron.utility.isConnected
import com.electron.utility.roundToTwo
import com.electron.view.adapter.MainAdapter
import com.electron.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "App Store"
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.showProgress.observe(this, Observer {
            if (it)
                progressBar.visibility = VISIBLE
            else
                progressBar.visibility = GONE
        })
        rvApps.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        if (isConnected) {
            viewModel.changeState()
            viewModel.makeApiCallToGetData()
            viewModel.serviceData.observe(this, Observer {
                //rvApps.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                adapter = MainAdapter(it.feed.entry, null)
                rvApps.adapter = adapter
                viewModel.deleteAllApps()
                for (mEntries in it.feed.entry) {
                    val app = MyApps(
                        mEntries.imName.label,
                        mEntries.imImage[2].label,
                        mEntries.category.attributes.label,
                        "$ " + roundToTwo(mEntries.imPrice.attributes.amount),
                        mEntries.imReleaseDate.attributes.label
                    )
                    viewModel.insert(app)
                }
            })
        } else {
            viewModel.allMyApps.observe(this, Observer {
                adapter = MainAdapter(null, it)
                rvApps.adapter = adapter
            })
        }
    }


}
