package com.example.reference13.Db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.reference13.models.Buyurtma
import com.example.reference13.models.Sotuvci
import com.example.reference13.models.Xaridor


class MyDbHelper(val context:Context):SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION),
    MyDbInterface {
    companion object {
        var DB_NAME = "buyurtma_db"
        var DB_VERSION = 1
    }


    override fun onCreate(p0: SQLiteDatabase?) {
        val sotuvchiQuery =
            "create table sotuvci_table (id integer not null primary key autoincrement unique, name text not null, number text not null)"
        val xaridorQuery =
            "create table xaridor_table (id integer not null primary key autoincrement unique, name text not null, number text not null, address text not null)"
        val buyurtmaQuery =
            "create table buyurtma_table (id integer not null primary key autoincrement unique, name text not null, date text not null, sotuvci_id integer  not null, xaridor_id integer  not null, foreign key (sotuvci_id) references  sotuvci_table (id), foreign key (xaridor_id) references xaridor_table (id))"
        p0?.execSQL(sotuvchiQuery)
        p0?.execSQL(xaridorQuery)
        p0?.execSQL(buyurtmaQuery)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun addSotuvci(sotuvci: Sotuvci) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", sotuvci.name)
        contentValues.put("number", sotuvci.number)
        database.insert("sotuvci_table", null, contentValues)
        database.close()
    }

    override fun getAllSotuvci(): List<Sotuvci> {
        val database = this.readableDatabase
        val list = ArrayList<Sotuvci>()
        val query = "select * from sotuvci_table"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Sotuvci(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                )
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun addXaridor(xaridor: Xaridor) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", xaridor.name)
        contentValues.put("number", xaridor.number)
        contentValues.put("address", xaridor.address)
        database.insert("xaridor_table", null, contentValues)
        database.close()
    }

    override fun getAllXaridor(): List<Xaridor> {
        val database = this.readableDatabase
        val list = ArrayList<Xaridor>()
        val query = "select * from xaridor_table"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Xaridor(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                    )
                )
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun addBuyurtma(buyurtma: Buyurtma) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", buyurtma.name)
        contentValues.put("data", buyurtma.data)
        contentValues.put("sotuvci_id", buyurtma.sotuvci.id)
        contentValues.put("xaridor_id", buyurtma.xaridor.id)
        database.insert("buyurtma_table", null, contentValues)
        database.close()
    }

    override fun getAllBuyurtma(): List<Buyurtma> {
        val list = ArrayList<Buyurtma>()
        val database = this.readableDatabase

        val query = "select * from buyurtma_table"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Buyurtma(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        getSotuvciById(cursor.getInt(3)),
                        getXaridorById(cursor.getInt(4))
                    )
                )
            } while (cursor.moveToNext())
        }
            return list
        }

        override fun getSotuvciById(id: Int): Sotuvci {
            val database = this.readableDatabase
            val cursor = database.query(
                "sotuvci_table",
                arrayOf("id", "name", "number"),
                "id = ?",
                arrayOf(id.toString()),
                null, null, null
            )
            cursor.moveToFirst()
            val sotuvci = Sotuvci(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2)
            )
            return sotuvci
        }

        override fun getXaridorById(id: Int): Xaridor {
            val database = this.readableDatabase
            val cursor = database.query(
                "xaridor_table",
                arrayOf("id", "name", "number", "address"),
                "id = ?",
                arrayOf(id.toString()),
                null, null, null
            )
            cursor.moveToFirst()
            val sotuvci = Xaridor(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
            )
            return sotuvci
        }
    }