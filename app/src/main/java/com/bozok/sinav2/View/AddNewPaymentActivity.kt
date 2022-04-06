package com.bozok.sinav2.View

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.bozok.sinav2.DAL.PaymentTypeOperations
import com.bozok.sinav2.Model.Payment
import com.bozok.sinav2.Model.PaymentType
import com.bozok.sinav2.ViewModel.AddNewPaymentActivitiyViewModel
import com.bozok.sinav2.ViewModel.DetailActivityViewModel
import com.bozok.sinav2.databinding.ActivityAddNewPaymentBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class AddNewPaymentActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddNewPaymentBinding
    private var day=0
    private var month=0
    private var year=0
    private var payment:Payment?=null
    private lateinit var addNewPaymentActivitiyViewModel: AddNewPaymentActivitiyViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddNewPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVM()
        currentDate()

        binding.ibtnCalender.setOnClickListener {
            setDate()
        }

        binding.btnPaymentSave.setOnClickListener {
            btnPaymentSave_Onclick()
        }

    }

    fun initVM(){
        addNewPaymentActivitiyViewModel= ViewModelProvider(this).get(AddNewPaymentActivitiyViewModel::class.java)
        payment= Payment()
    }

    fun btnPaymentSave_Onclick(){
        var typeId=intent.getIntExtra("old",-1)

        payment?.amount= "₺"+binding.etPaymentTotal.text.toString()
        payment?.date=binding.etDate.text.toString()
        payment?.TypeId=typeId.toString()
        addNewPaymentActivitiyViewModel.insertPayment(this,payment!!)

        setResult(RESULT_OK)
        finish()

    }

    fun setDate(){
        val cal:Calendar= Calendar.getInstance()
        day=cal.get(Calendar.DAY_OF_MONTH)
        month=cal.get(Calendar.MONTH)
        year=cal.get(Calendar.YEAR)

        val dpd=DatePickerDialog(this,DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            binding.etDate.setText("${day}.${month+1}.${year}")
        },year,month,day)
        dpd.datePicker.maxDate=System.currentTimeMillis()
        dpd.show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun currentDate(){
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val formatted = current.format(formatter)
        binding.etDate.setText(formatted)
    }


}