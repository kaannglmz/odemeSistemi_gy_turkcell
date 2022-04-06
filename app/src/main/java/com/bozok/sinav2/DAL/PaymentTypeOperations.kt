package com.bozok.sinav2.DAL

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.bozok.sinav2.Model.Payment
import com.bozok.sinav2.Model.PaymentType

class PaymentTypeOperations(context: Context) {
    var dbOpenHelper:DatabaseOpenHelper
    var dbPaymentType: SQLiteDatabase?=null

    init {
        dbOpenHelper=DatabaseOpenHelper(context,"PaymentDb",null,1)
    }

    fun Open(){
        dbPaymentType=dbOpenHelper.writableDatabase
    }

    fun Close(){
        if(dbPaymentType != null && dbPaymentType!!.isOpen){
            dbPaymentType!!.close()
        }
    }
                                            //@@@@@
    fun InsertPayment(payment: Payment){
        val cv= ContentValues()
        cv.put("Amount",payment.amount)
        cv.put("Date",payment.date)
        cv.put("TypeId",payment.TypeId)

        Open()
        dbPaymentType!!.insert("Payment",null,cv)
        Close()
    }

    fun Insert(paymentType: PaymentType){
        val cv= ContentValues()
        cv.put("Title",paymentType.title)
        cv.put("Period",paymentType.period)
        cv.put("PeriodDay",paymentType.periodDay)

        Open()
        dbPaymentType!!.insert("PaymentType",null,cv)
        Close()

    }

    fun Update(paymentType: PaymentType){
        val cv=ContentValues()
        cv.put("Title",paymentType.title)
        cv.put("Period",paymentType.period)
        cv.put("PeriodDay",paymentType.periodDay)

        Open()
        dbPaymentType!!.update("PaymentType",cv,"Id=?", arrayOf(paymentType.Id.toString()))
        Close()
    }

                                //@@@@@@
    fun DeletePayment(id:Int){
        Open()
        dbPaymentType!!.delete("Payment","Id=?", arrayOf(id.toString()))
        Close()
    }
    fun DeleteAllPayments(id:Int){
        Open()
        dbPaymentType!!.delete("Payment","Id=?", arrayOf(id.toString()))
        Close()
    }

    fun Delete(id:Int){
        Open()
        dbPaymentType!!.delete("PaymentType","Id=?", arrayOf(id.toString()))
        Close()
    }


                                //@@@@@@@
    @SuppressLint("Range")
    fun GetAllPayments(id:Int):ArrayList<Payment>{

        var paymentList=ArrayList<Payment>()
        Open()

        val sql="SELECT * FROM Payment Where TypeId=?"
        val c=dbPaymentType!!.rawQuery(sql, arrayOf(id.toString()))

        if(c.moveToFirst()){
            var payment:Payment
            do {
                payment= Payment()
                payment.Id=c.getInt(c.getColumnIndex("Id"))
                payment.amount=c.getString(c.getColumnIndex("Amount"))
                payment.date=c.getString(c.getColumnIndex("Date"))
                payment.TypeId=c.getString(c.getColumnIndex("TypeId"))

                paymentList.add(payment)

            }while (c.moveToNext())
        }
        Close()
        return paymentList
    }


    @SuppressLint("Range")
    fun GetAllPaymentTypes():ArrayList<PaymentType>{

        var paymentTypeList=ArrayList<PaymentType>()
        Open()

        val sql="SELECT * FROM PaymentType"
        val c=dbPaymentType!!.rawQuery(sql,null)

        if(c.moveToFirst()){
            var paymentType:PaymentType
            do {
                paymentType= PaymentType()
                paymentType.Id=c.getInt(c.getColumnIndex("Id"))
                paymentType.title=c.getString(c.getColumnIndex("Title"))
                paymentType.period=c.getString(c.getColumnIndex("Period"))
                paymentType.periodDay=c.getString(c.getColumnIndex("PeriodDay"))
                paymentTypeList.add(paymentType)

            }while (c.moveToNext())
        }
        Close()
        return paymentTypeList
    }

    @SuppressLint("Range")
    fun GetPaymentType(id:Int):PaymentType?{ // detay ekranında tek bir kişinin getirilmesi için
        var paymentType:PaymentType?=null
        Open()

        val sql="SELECT * FROM PaymentType WHERE Id=?"
        val c=dbPaymentType!!.rawQuery(sql, arrayOf(id.toString()))

        if(c.moveToFirst()) {

            paymentType= PaymentType()
            paymentType.Id=c.getInt(c.getColumnIndex("Id"))
            paymentType.title=c.getString(c.getColumnIndex("Title"))
            paymentType.period=c.getString(c.getColumnIndex("Period"))
            paymentType.periodDay=c.getString(c.getColumnIndex("PeriodDay"))

        }

        Close()
        return paymentType

    }


}