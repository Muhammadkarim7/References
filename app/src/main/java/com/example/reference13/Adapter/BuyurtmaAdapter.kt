package com.example.reference13.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reference13.databinding.ItemRvBinding
import com.example.reference13.farment.BuyurtmaFragment
import com.example.reference13.models.Buyurtma

class BuyurtmaAdapter(var list:ArrayList<Buyurtma> = ArrayList()):RecyclerView.Adapter<BuyurtmaAdapter.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(myObject: Buyurtma){
            itemRvBinding.tvName.text=myObject.name
            itemRvBinding.tvNumber.text=myObject.data
            itemRvBinding.tvSotuvchi.visibility=View.VISIBLE
            itemRvBinding.tvSotuvchi.text= myObject.sotuvci.toString()
            itemRvBinding.tvXaridor.visibility=View.VISIBLE
            itemRvBinding.tvXaridor.text=myObject.xaridor.toString()

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
