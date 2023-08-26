package com.example.reference13.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reference13.databinding.ItemRvBinding
import com.example.reference13.models.Sotuvci
import com.example.reference13.models.Xaridor

class SotuvchiXaridorAdapter<T>(var list:ArrayList<T> = ArrayList()):RecyclerView.Adapter<SotuvchiXaridorAdapter<T>.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(myObject: T){
            try {
                val sotuvci:Sotuvci=myObject as Sotuvci
                itemRvBinding.tvName.text=sotuvci.name
                itemRvBinding.tvNumber.text=sotuvci.number
            }catch (e:Exception){
                val xaridor:Xaridor = myObject as Xaridor
                itemRvBinding.tvName.text=xaridor.name
                itemRvBinding.tvNumber.text=xaridor.number
                itemRvBinding.tvAddress.visibility=View.VISIBLE
                itemRvBinding.tvAddress.text=xaridor.address
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}
  