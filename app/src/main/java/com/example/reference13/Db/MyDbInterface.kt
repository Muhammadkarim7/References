package com.example.reference13.Db

import com.example.reference13.models.Buyurtma
import com.example.reference13.models.Sotuvci
import com.example.reference13.models.Xaridor

interface MyDbInterface {


    fun addSotuvci(sotuvci: Sotuvci)
    fun getAllSotuvci():List<Sotuvci>

    fun addXaridor(xaridor: Xaridor)
    fun getAllXaridor():List<Xaridor>

    fun addBuyurtma(buyurtma: Buyurtma)
    fun getAllBuyurtma():List<Buyurtma>

    fun getSotuvciById(id:Int):Sotuvci
    fun getXaridorById(id: Int):Xaridor
}