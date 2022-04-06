package com.bozok.sinav2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bozok.sinav2.Model.Payment
import com.bozok.sinav2.R

class PaymentAdapter(val context: Context, var paymentList:ArrayList<Payment>, var itemClick:(position:Int)->Unit)
    :RecyclerView.Adapter<PaymentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val v= LayoutInflater.from(context).inflate(R.layout.rv_payment_item,parent,false)
        return PaymentViewHolder(v,itemClick)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.bind(paymentList.get(position))
    }

    override fun getItemCount(): Int {
        return paymentList.size
    }
}