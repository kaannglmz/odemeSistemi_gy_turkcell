package com.bozok.sinav2.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.bozok.sinav2.DAL.PaymentTypeOperations
import com.bozok.sinav2.Model.PaymentType

class MainActivityViewModel: ViewModel() {

    fun getAllPaymentTypes(context: Context):ArrayList<PaymentType>{
        return PaymentTypeOperations(context).GetAllPaymentTypes()
    }

}