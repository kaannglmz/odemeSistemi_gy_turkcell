package com.bozok.sinav2.DAL

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseOpenHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(p0: SQLiteDatabase?) {
        val sql="CREATE TABLE PaymentType(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Title TEXT, Period TEXT, PeriodDay TEXT)"
        p0!!.execSQL(sql)

        val sql2="CREATE TABLE Payment(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Amount TEXT, Date TEXT, TypeId TEXT)"
        p0!!.execSQL(sql2)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}