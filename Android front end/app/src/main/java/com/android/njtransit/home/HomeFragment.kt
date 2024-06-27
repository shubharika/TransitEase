package com.android.njtransit.home

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.android.njtransit.R
import com.android.njtransit.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var tag:Tag
    private lateinit var binding : FragmentHomeBinding

    private val viewModel: HomeFragmentViewModel by viewModels()
    private var status = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.pingButton.setOnClickListener {
            if(!status){
                viewModel.pingUser("2", "2000","200")
                binding.statusValue.text = "On Train"
                binding.checkInCard.visibility = View.VISIBLE
                binding.textView8.visibility = View.VISIBLE
                binding.statusValue.text = "On Train"
                binding.boardingStation.text = "Newark"
                status = true
            }
            else{
                viewModel.pingUser("2", "4000","200")
                binding.textView10.visibility = View.VISIBLE
                binding.deBoardingStation.visibility = View.VISIBLE
                binding.journeyCost.visibility = View.VISIBLE
                binding.textView11.visibility = View.VISIBLE
                status = true
                binding.statusValue.text = "Outside Train"
                binding.balance.text = "$494"
            }
        }
        binding.autopaySwitch.setOnCheckedChangeListener { compoundButton, b ->
            if(b){
                binding.statusValue.text = "Outside Train"
            }

        }




        return binding.root
    }
}