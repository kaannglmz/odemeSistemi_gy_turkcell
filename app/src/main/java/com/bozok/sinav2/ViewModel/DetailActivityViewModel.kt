package com.bozok.sinav2.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.bozok.sinav2.DAL.PaymentTypeOperations
import com.bozok.sinav2.Model.Payment
import com.bozok.sinav2.Model.PaymentType

class DetailActivityViewModel:ViewModel() {
    fun getAllPayments(context: Context,typeId:Int):ArrayList<Payment>{
        return PaymentTypeOperations(context).GetAllPayments(typeId)
    }

    fun getPaymentType(context: Context,Id:Int): PaymentType? {
        return PaymentTypeOperations(context).GetPaymentType(Id)
    }

    fun deletePayment(context: Context,paymentId: Int){
        PaymentTypeOperations(context).DeletePayment(paymentId)
    }

}