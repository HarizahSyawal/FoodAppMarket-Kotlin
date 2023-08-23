package com.hariz.foodmarketapp.ui.home.popular

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hariz.foodmarketapp.R
import com.hariz.foodmarketapp.model.dummy.HomeVerticalModel
import com.hariz.foodmarketapp.ui.home.newtaste.HomeNewTasteAdapter
import kotlinx.android.synthetic.main.item_home_vertical.view.*

class HomePopularAdapter(
    private val listData : List<HomeVerticalModel>,
    private val itemAdapterCallback : HomePopularAdapter.ItemAdapterCallback,
) : RecyclerView.Adapter<HomePopularAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_vertical, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: HomePopularAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: HomeVerticalModel, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                tvTitle.text = data.title
                tvPrice.text = data.price
                rbFood.rating = data.rating

//                Glide.with(context)
//                    .load(data.src)
//                    .into(ivPoster)

                itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data: HomeVerticalModel)
    }
}