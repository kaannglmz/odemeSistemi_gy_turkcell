package com.bozok.sinav2.Adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bozok.sinav2.Model.Payment
import com.bozok.sinav2.R

class PaymentViewHolder(itemView: View, var itemClick:(position:Int)->Unit) : RecyclerView.ViewHolder(itemView) {

    var tvAmount:TextView
    var tvDate:TextView

    init {
        tvAmount=itemView.findViewById(R.id.tvPrice)
        tvDate=itemView.findViewById(R.id.tvDate)

        itemView.setOnClickListener {
            itemClick(adapterPosition)
        }

    }

    fun bind(payment:Payment){
        tvAmount.text=payment.amount.toString()
        tvDate.text=payment.date

    }


}