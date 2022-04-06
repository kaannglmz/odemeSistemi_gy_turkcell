package com.bozok.sinav2.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bozok.sinav2.Adapter.PaymentTypeAdapter
import com.bozok.sinav2.DAL.PaymentTypeOperations
import com.bozok.sinav2.Model.PaymentType
import com.bozok.sinav2.Util.Extensions.from
import com.bozok.sinav2.ViewModel.MainActivityViewModel
import com.bozok.sinav2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var paymentList=ArrayList<PaymentType>()
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVM()
        initLm()
        binding.btnAddNewPaymentType.setOnClickListener {
            btnAddNewPaymentType_Onclick()
        }

    }
    fun initVM(){

        mainActivityViewModel=ViewModelProvider(this).get(MainActivityViewModel::class.java)
        paymentList=mainActivityViewModel.getAllPaymentTypes(this) //paymentTypeOperations.GetAllPaymentTypes()

    }

    fun initLm(){
        val lm= LinearLayoutManager(this)

        lm.orientation= LinearLayoutManager.VERTICAL
        binding.rvPaymentTypeList.layoutManager=lm
        binding.rvPaymentTypeList.adapter=PaymentTypeAdapter(this,paymentList,::itemClick,::itemButtonClick)
    }
    fun itemClick(position:Int){
        val i=Intent(this,DetailActivity::class.java)
        i.putExtra("old",paymentList.get(position).Id)
        resultLauncher.launch(i)
    }

    fun itemButtonClick(position:Int){
        val i=Intent(this,AddNewPaymentActivity::class.java)
        i.putExtra("old",paymentList.get(position).Id)
        resultLauncher.launch(i)
    }


    fun btnAddNewPaymentType_Onclick(){
        val i=Intent(this,AddNewPaymentTypeActivity::class.java)
        from="new"
        resultLauncher.launch(i)
    }


    var resultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == RESULT_OK){
            
            paymentList.clear()
            paymentList.addAll(mainActivityViewModel.getAllPaymentTypes(this) )
            binding.rvPaymentTypeList.adapter?.notifyDataSetChanged()

        }else if(result.resultCode== RESULT_CANCELED){

        }

    }


}