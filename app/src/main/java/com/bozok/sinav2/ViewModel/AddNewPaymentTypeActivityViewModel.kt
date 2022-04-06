package com.bozok.sinav2.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.bozok.sinav2.DAL.PaymentTypeOperations
import com.bozok.sinav2.Model.Payment
import com.bozok.sinav2.Model.PaymentType

class AddNewPaymentTypeActivityViewModel:ViewModel() {

    fun deletePaymentType(context: Context, paymentTypeId: Int){
        PaymentTypeOperations(context).Delete(paymentTypeId)
    }

    fun insertPaymentType(context: Context, paymentType: PaymentType){
        PaymentTypeOperations(context).Insert(paymentType)
    }

    fun getPaymentType(context: Context,Id:Int): PaymentType? {
        return PaymentTypeOperations(context).GetPaymentType(Id)
    }

    fun updatePaymentType(context: Context, paymentType: PaymentType){
        PaymentTypeOperations(context).Update(paymentType)
    }

    fun deleteAllPayment(context: Context, paymentId:Int){
        PaymentTypeOperations(context).DeleteAllPayments(paymentId)
    }


}