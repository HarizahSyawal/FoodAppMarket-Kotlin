package com.hariz.foodmarketapp.ui.order

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hariz.foodmarketapp.R
import kotlinx.android.synthetic.main.fragment_orders.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class OrderFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_orders, container, false)
        return root
    }
}