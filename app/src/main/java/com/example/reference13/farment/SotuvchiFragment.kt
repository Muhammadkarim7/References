package com.example.reference13.farment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.reference13.Adapter.SotuvchiXaridorAdapter
import com.example.reference13.Db.MyDbHelper
import com.example.reference13.databinding.FragmentSotuvchiBinding
import com.example.reference13.models.Sotuvci
import java.util.ArrayList

class SotuvciFragment : Fragment() {

    private val binding by lazy { FragmentSotuvchiBinding.inflate(layoutInflater) }
    private lateinit var sotuvciXaridorAdapter:SotuvchiXaridorAdapter<Sotuvci>
    private lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.btnSave.setOnClickListener {
            val sotuvci = Sotuvci(
                0,
                binding.edtName.text.toString().trim(),
                binding.edtNumber.text.toString().trim()
            )
            myDbHelper.addSotuvci(sotuvci)
            onResume()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.edtNumber.text.clear()
        binding.edtName.text.clear()
        myDbHelper= MyDbHelper(binding.root.context)
        sotuvciXaridorAdapter= SotuvchiXaridorAdapter(myDbHelper.getAllSotuvci() as ArrayList<Sotuvci>)
        binding.rv.adapter = sotuvciXaridorAdapter
    }
}