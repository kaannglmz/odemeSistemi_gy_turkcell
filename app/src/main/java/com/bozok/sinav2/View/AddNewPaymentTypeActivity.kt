package com.bozok.sinav2.View

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.bozok.sinav2.Model.Payment
import com.bozok.sinav2.Model.PaymentType
import com.bozok.sinav2.R
import com.bozok.sinav2.Util.Extensions.from
import com.bozok.sinav2.ViewModel.AddNewPaymentTypeActivityViewModel
import com.bozok.sinav2.ViewModel.DetailActivityViewModel
import com.bozok.sinav2.databinding.ActivityAddNewPaymentTypeBinding

class AddNewPaymentTypeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNewPaymentTypeBinding
    private var periodType:String?="-"
    private var paymentType:PaymentType?=null
    private lateinit var addNewPaymentTypeActivityViewModel: AddNewPaymentTypeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddNewPaymentTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVM()
        intentControl()
        periodSpinner()

        binding.btnSave.setOnClickListener {
            if(TextUtils.isEmpty(binding.etPaymentTypeTitle.text.toString())){
                binding.etPaymentTypeTitle.error="It's not empty!"
            }else {
                periodDayController()
            }
        }

        binding.btnDelete.setOnClickListener {
            btnDelete_OnClick()
        }

    }

    fun initVM(){
        addNewPaymentTypeActivityViewModel= ViewModelProvider(this).get(AddNewPaymentTypeActivityViewModel::class.java)
    }

    fun btnDelete_OnClick(){
        val adb=AlertDialog.Builder(this)
        adb.setTitle("Delete")
        adb.setMessage("Do you want delete?")
        adb.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->

            addNewPaymentTypeActivityViewModel.deletePaymentType(this,paymentType!!.Id!!)
            addNewPaymentTypeActivityViewModel.deleteAllPayment(this,paymentType!!.Id!!)
            setResult(RESULT_OK)
            finish()
        })
        adb.setNegativeButton("No",null)
        adb.show()

    }

    fun periodSpinner(){
        val spinnerList= arrayListOf<String>("-","Weekly","Monthly","Yearly")

        var mySpinnerAdapter= ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,spinnerList)
        binding.spPeriod.adapter=mySpinnerAdapter
        mySpinnerAdapter.notifyDataSetChanged()

        binding.spPeriod.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                periodType=binding.spPeriod.adapter.getItem(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                periodType="-"
            }

        }

    }

    fun periodDayController(){
        var periodDayStation:Int?

        if(TextUtils.isEmpty(binding.etPeriodDay.text.toString())){
            periodDayStation=0
        }else{
            periodDayStation = binding.etPeriodDay.text.toString().toInt()
        }
        if(periodDayStation<0){
            binding.etPeriodDay.error="Please enter a number greater than 0"
        }else{

            if(periodType=="Weekly" && periodDayStation>7){
                binding.etPeriodDay.error="Enter a number between 0-7"
            }else if(periodType=="Monthly" && periodDayStation>30){
                binding.etPeriodDay.error="Enter a number between 0-30"
            }else if(periodType=="Yearly" && periodDayStation>365){
                binding.etPeriodDay.error="Enter a number between 0-365"
            }else{

                paymentType?.title=binding.etPaymentTypeTitle.text.toString()
                paymentType?.period=periodType
                paymentType?.periodDay = binding.etPeriodDay.text.toString()

                if(paymentType!!.Id == null){
                    addNewPaymentTypeActivityViewModel.insertPaymentType(this,paymentType!!)
                }else{
                    addNewPaymentTypeActivityViewModel.updatePaymentType(this,paymentType!!)
                }

                setResult(RESULT_OK)
                finish()

           }
        }

    }

    fun intentControl(){
        var pageStation= intent.getIntExtra("old",-1)
        if(from=="old"){

            paymentType=addNewPaymentTypeActivityViewModel.getPaymentType(this,pageStation)

            binding.etPaymentTypeTitle.setText(paymentType!!.title)
            binding.etPeriodDay.setText(paymentType!!.periodDay)
            periodType=paymentType!!.period
            binding.btnDelete.visibility= View.VISIBLE
        }else{
            paymentType= PaymentType()
            binding.btnDelete.visibility=View.INVISIBLE
        }
    }

}