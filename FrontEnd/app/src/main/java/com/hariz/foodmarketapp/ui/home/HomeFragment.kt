package com.hariz.foodmarketapp.ui.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.hariz.foodmarketapp.R
import com.hariz.foodmarketapp.model.dummy.HomeModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.flowOf

    class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback{

    private var foodList : ArrayList<HomeModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)

            initDataDummy()
            var adapter = HomeAdapter(foodList, this)
            var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rcList.layoutManager = layoutManager
            rcList.adapter = adapter

            val sectionPagerAdapter = SectionPagerAdapter(
                childFragmentManager
            )
            viewPager.adapter = sectionPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
        }

    fun initDataDummy(){
        foodList = ArrayList()
        foodList.add(HomeModel("Coto Makassar", "", 5f))
        foodList.add(HomeModel("Pallu Basa", "", 5f))
        foodList.add(HomeModel("Sop Sodara", "", 4f))
    }

        override fun onClick(v: View, data: HomeModel) {
            Toast.makeText(context, "Percobaan klik item " + data.title, Toast.LENGTH_SHORT).show()
        }
    }