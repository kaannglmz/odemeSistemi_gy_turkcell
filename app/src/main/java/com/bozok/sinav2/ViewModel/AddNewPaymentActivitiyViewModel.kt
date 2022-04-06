package com.bozok.sinav2.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.bozok.sinav2.DAL.PaymentTypeOperations
import com.bozok.sinav2.Model.Payment
import com.bozok.sinav2.Model.PaymentType

class AddNewPaymentActivitiyViewModel:ViewModel() {


    fun insertPayment(context: Context,payment: Payment){
        PaymentTypeOperations(context).InsertPayment(payment)
    }



}