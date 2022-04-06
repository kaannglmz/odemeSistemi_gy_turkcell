package com.bozok.sinav2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bozok.sinav2.Model.PaymentType
import com.bozok.sinav2.R

class PaymentTypeAdapter(val context: Context, var paymentTypeList:ArrayList<PaymentType>, var itemClick:(position:Int)->Unit, var itemButtonClick:(position:Int)->Unit)
    : RecyclerView.Adapter<PaymentTypeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentTypeViewHolder {
        val v= LayoutInflater.from(context).inflate(R.layout.rv_item,parent,false)
        return PaymentTypeViewHolder(v,itemClick,itemButtonClick)
    }

    override fun onBindViewHolder(holder: PaymentTypeViewHolder, position: Int) {
        holder.bind(paymentTypeList.get(position))
    }

    override fun getItemCount(): Int {
        return paymentTypeList.size
    }
}