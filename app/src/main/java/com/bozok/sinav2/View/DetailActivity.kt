package com.bozok.sinav2.View

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bozok.sinav2.Adapter.PaymentAdapter
import com.bozok.sinav2.Adapter.PaymentTypeAdapter
import com.bozok.sinav2.DAL.PaymentTypeOperations
import com.bozok.sinav2.Model.Payment
import com.bozok.sinav2.Model.PaymentType
import com.bozok.sinav2.Util.Extensions.from
import com.bozok.sinav2.ViewModel.DetailActivityViewModel
import com.bozok.sinav2.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    var paymentList=ArrayList<Payment>()
    private var paymentType:PaymentType?=null
    private lateinit var detailActivityViewModel: DetailActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVM()
        initLm()
        getInformation()

        binding.btnEdit.setOnClickListener {
            btnEdit_Onclick()
        }
        binding.btnPaymentAdd.setOnClickListener {
            btnPaymentAdd_Onclick()
        }
    }

    fun initVM(){
        detailActivityViewModel=ViewModelProvider(this).get(DetailActivityViewModel::class.java)

        val getDetailInformation=intent.getIntExtra("old",-1)
        paymentList= detailActivityViewModel.getAllPayments(this,getDetailInformation)   //paymentTypeOperations.GetAllPayments(getDetailInformation!!.toInt())
    }

    fun initLm(){
        val lm=LinearLayoutManager(this)
        lm.orientation= LinearLayoutManager.VERTICAL
        binding.rvPaymentList.layoutManager=lm
        binding.rvPaymentList.adapter= PaymentAdapter(this,paymentList,::itemClick)
    }

    fun itemClick(position:Int){
        val getDetailInformation=intent.getIntExtra("old",-1)
        val adb=AlertDialog.Builder(this)
        adb.setTitle("Delete")
        adb.setMessage("Do you want delete?")
        adb.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->

            detailActivityViewModel.deletePayment(this,paymentList.get(position).Id!!)
            paymentList.clear()
            paymentList.addAll(detailActivityViewModel.getAllPayments(this,getDetailInformation))
            binding.rvPaymentList.adapter!!.notifyDataSetChanged()

        })
        adb.setNegativeButton("No",null)
        adb.show()
    }

    fun btnPaymentAdd_Onclick(){
        val i=Intent(this,AddNewPaymentActivity::class.java)
        val getDetailIntent=intent.getIntExtra("old",-1)
        i.putExtra("old",getDetailIntent)
        resultLauncher.launch(i)
    }

    fun btnEdit_Onclick(){
        val i= Intent(this,AddNewPaymentTypeActivity::class.java)
        from="old"
        val getDetailIntent=intent.getIntExtra("old",-1)
        i.putExtra("old",getDetailIntent)
        resultLauncher.launch(i)
        setResult(RESULT_OK)
        finish()
    }

    fun getInformation(){
        val getDetailInformation=intent.getIntExtra("old",-1)

        paymentType=detailActivityViewModel.getPaymentType(this,getDetailInformation)
        binding.tvTitle.setText(paymentType!!.title)
        binding.tvPeriodDay.setText(paymentType!!.periodDay)
        binding.tvPeriod.setText(paymentType!!.period)

    }


    var resultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == RESULT_OK){
            val getDetailInformation=intent.getIntExtra("old",-1)
            paymentList.clear()
            paymentList.addAll( detailActivityViewModel.getAllPayments(this,getDetailInformation)    /*detailActivityViewModel.getAllPayment(this,getDetailInformation)*/)
            binding.rvPaymentList.adapter!!.notifyDataSetChanged()

        }else if(result.resultCode== RESULT_CANCELED){
        }

    }
}