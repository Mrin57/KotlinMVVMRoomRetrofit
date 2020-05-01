package com.electron.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.electron.R
import com.electron.database.entity.MyApps
import com.electron.network.model.AppEntry
import com.electron.utility.roundToTwo
import com.squareup.picasso.Picasso

class MainAdapter(private val entries: ArrayList<AppEntry>?, private val dbEntries: List<MyApps>?) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private lateinit var mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_app, parent, false)
        mContext = parent.context
        return MainViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        if (entries != null)
            return entries.size
        else
            if (dbEntries != null)
                return dbEntries.size
            else
                return 0
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        if (entries != null)
            holder.bindItems(entries[position], null)
        else
            holder.bindItems(null, dbEntries!![position])
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(entry: AppEntry?, dbEntry: MyApps?) {
            val ivApp = itemView.findViewById(R.id.iv_app) as ImageView
            val tvName = itemView.findViewById(R.id.tv_app_name) as TextView
            val tvCategory = itemView.findViewById(R.id.tv_category) as TextView
            val tvReleaseDate = itemView.findViewById(R.id.tv_date) as TextView
            val tvPrice = itemView.findViewById(R.id.tv_price) as TextView

            if (entry != null) {
                tvName.text = entry.imName.label
                Picasso.get().load(entry.imImage[2].label).into(ivApp)
                tvCategory.text = entry.category.attributes.label
                tvReleaseDate.text = entry.imReleaseDate.attributes.label
                tvPrice.text = "$ " + roundToTwo(entry.imPrice.attributes.amount)
            } else {
                tvName.text = dbEntry?.appName
                tvCategory.text = dbEntry?.category
                tvReleaseDate.text = dbEntry?.releaseDate
                tvPrice.text = "$ " + dbEntry?.price
            }
        }
    }
}