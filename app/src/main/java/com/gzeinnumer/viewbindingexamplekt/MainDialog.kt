package com.gzeinnumer.viewbindingexamplekt

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.gzeinnumer.viewbindingexamplekt.databinding.FragmentMainDialogBinding

class MainDialog : DialogFragment() {

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

    companion object{
        public val TAG = "MainDialog"
        fun newInstance(): MainDialog {
            return MainDialog()
        }
    }


    lateinit var binding: FragmentMainDialogBinding
    override fun onStart() {
        super.onStart()
        dialog?.let { d ->
            d.setCanceledOnTouchOutside(false)
            d.window?.let { w ->
                w.apply {
                    setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    setLayout(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    setBackgroundDrawableResource(R.drawable.mygzn_background_dialog)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainDialogBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
    }

    private fun initView() {
        binding.title.text = "Disini Pesannya"
    }

    private fun initOnClick() {
        binding.btnOke.setOnClickListener(View.OnClickListener {
            Toast.makeText(requireContext(), "Ok", Toast.LENGTH_SHORT).show()
            dialog!!.dismiss()
        })
        binding.btnCancel.setOnClickListener(View.OnClickListener {
            Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
            dialog!!.dismiss()
        })
    }
}