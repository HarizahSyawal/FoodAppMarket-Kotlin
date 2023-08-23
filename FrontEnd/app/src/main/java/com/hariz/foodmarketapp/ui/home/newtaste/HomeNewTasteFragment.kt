package com.hariz.foodmarketapp.ui.home.newtaste

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hariz.foodmarketapp.R
import com.hariz.foodmarketapp.model.dummy.HomeVerticalModel
import com.hariz.foodmarketapp.ui.home.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeNewTasteFragment : Fragment(), HomeNewTasteAdapter.ItemAdapterCallback {

    private var foodList : ArrayList<HomeVerticalModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_new_taste, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDummy()
        var adapter = HomeNewTasteAdapter(foodList, this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter
    }

    fun initDummy(){
        foodList = ArrayList()
        foodList.add(HomeVerticalModel("Coto Makassar", "Rp.30.000", "", 5f))
        foodList.add(HomeVerticalModel("Pallu Basa", "Rp.30.000", "", 5f))
        foodList.add(HomeVerticalModel("Sop Sodara", "Rp.30.000", "", 4f))
    }

    override fun onClick(v: View, data: HomeVerticalModel) {
        Toast.makeText(context, "Percobaan klik item " + data.title, Toast.LENGTH_SHORT).show()
    }

}