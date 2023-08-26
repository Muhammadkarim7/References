package com.example.reference13.farment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.reference13.Adapter.BuyurtmaAdapter
import com.example.reference13.Db.MyDbHelper
import com.example.reference13.databinding.FragmentSotuvchiBinding
import com.example.reference13.models.Buyurtma
import com.example.reference13.models.Sotuvci
import com.example.reference13.models.Xaridor

class BuyurtmaFragment:Fragment() {
    private val binding by lazy { FragmentSotuvchiBinding.inflate(layoutInflater)}
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var buyurtmaAdapter: BuyurtmaAdapter
    private lateinit var sotuvciList:ArrayList<Sotuvci>
    private lateinit var xaridorList: ArrayList<Xaridor>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myDbHelper=MyDbHelper(binding.root.context)
        binding.edtNumber.hint="Data"
        binding.spinnerSotuvci.visibility=View.VISIBLE
        binding.spinnerXaridor.visibility=View.VISIBLE

        sotuvciList = myDbHelper.getAllSotuvci() as ArrayList<Sotuvci>
        xaridorList = myDbHelper.getAllXaridor() as ArrayList<Xaridor>

        val sotuvciNameList = ArrayList<String>()
        val xaridorNameList = ArrayList<String>()

        sotuvciList.forEach{
            sotuvciNameList.add(it.name)
        }
        xaridorList.forEach {
            xaridorNameList.add(it.name)
        }
        binding.spinnerXaridor.adapter = ArrayAdapter<String>(binding.root.context, android.R.layout.simple_list_item_1, xaridorNameList)
        binding.spinnerSotuvci.adapter = ArrayAdapter<String>(binding.root.context, android.R.layout.simple_list_item_1, sotuvciNameList)

        binding.btnSave.setOnClickListener {
            val buyurtma = Buyurtma(
                0,
                binding.edtName.text.toString().trim(),
                binding.edtNumber.text.toString().trim(),
                sotuvciList[binding.spinnerSotuvci.selectedItemPosition],
                xaridorList[binding.spinnerXaridor.selectedItemPosition]
            )
            myDbHelper.addBuyurtma(buyurtma)
            onResume()
        }

        return binding.root
    }
    override fun onResume() {
        super.onResume()
       buyurtmaAdapter = BuyurtmaAdapter(myDbHelper.getAllBuyurtma() as ArrayList<Buyurtma>)
        binding.rv.adapter = buyurtmaAdapter
    }
}