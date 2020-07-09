package com.gzeinnumer.viewbindingexamplekt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gzeinnumer.viewbindingexamplekt.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    /*
    android {
        untuk android versi di bawah 4
        viewBinding {
            enabled = true
        }

        untuk android versi 4 -> gradel versi 6.1.1 -> android gradle plugin version 4.0.0
        buildFeatures{
            viewBinding = true
        }
    }
    */

    fun newInstance(): MainFragment {
        return MainFragment()
    }

    private var binding: FragmentMainBinding? =null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}