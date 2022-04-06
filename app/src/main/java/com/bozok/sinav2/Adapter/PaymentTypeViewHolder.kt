package com.bozok.sinav2.Adapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bozok.sinav2.Model.PaymentType
import com.bozok.sinav2.R

class PaymentTypeViewHolder(itemView: View, var itemClick:(position:Int)->Unit, var itemButtonClick:(position:Int)->Unit)
    : RecyclerView.ViewHolder(itemView) {

    var tvTitle:TextView
    var tvPeriod:TextView
    var tvPeriodDay:TextView
    var btnAddPayment:Button

    init {
        tvTitle=itemView.findViewById(R.id.tv_title)
        tvPeriod=itemView.findViewById(R.id.tv_period)
        tvPeriodDay=itemView.findViewById(R.id.tv_period_day)
        btnAddPayment=itemView.findViewById(R.id.btnAddPayment)

        itemView.setOnClickListener {
            itemClick(adapterPosition)
        }

        btnAddPayment.setOnClickListener {
            itemButtonClick(adapterPosition)
        }

    }

    fun bind(paymentType: PaymentType){
        tvTitle.text=paymentType.title
        tvPeriod.text=paymentType.period
        tvPeriodDay.text=paymentType.periodDay
    }



}