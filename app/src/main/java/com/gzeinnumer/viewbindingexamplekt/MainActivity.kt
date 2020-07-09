package com.gzeinnumer.viewbindingexamplekt

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gzeinnumer.viewbindingexamplekt.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity(), AdapterRVMultiType.MyOnClick, AdapterRVSpesialKT.MyOnClick {

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
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        initView()
        initOnClick()
        spesialRecyclerView()
    }

    private fun initView() {
        binding!!.btn.text = "Click To Open Dialog"
    }

    private fun initOnClick() {
        binding!!.btn.setOnClickListener {
            val transaction =
                supportFragmentManager.beginTransaction()
            val previous =
                supportFragmentManager.findFragmentByTag(MainDialog.TAG)
            if (previous != null) {
                transaction.remove(previous)
            }
            val dialog: DialogFragment = MainDialog.newInstance()
            dialog.show(transaction, MainDialog.TAG)
        }
    }

    private fun spesialRecyclerView() {
        //type 1
        val adapterData = AdapterRV()
        adapterData.onClick = (object : AdapterRV.MyOnClick{
            override fun myOnClick(position: Int) {
                Toast.makeText(applicationContext, "$position", Toast.LENGTH_SHORT).show()
            }

        })
        //type 2
        val adapterMT = AdapterRVMultiType()
        adapterMT.onClick = this
        //type 3
        val adapterRVSpesialKT = AdapterRVSpesialKT(this)

        //type 4
        val adapterRVSpesialKT2 = AdapterRVSpesialKT2(){
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        }

        val list: MutableList<String> = arrayListOf()
        list.add("RV 1")
        list.add("RV 2")
        binding?.let {
            it.rv.apply {
                adapter = adapterRVSpesialKT2
                adapterRVSpesialKT2.setList(list as ArrayList<String>)
                hasFixedSize()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    override fun myOnClick(position: Int) {
        Toast.makeText(applicationContext, "$position", Toast.LENGTH_SHORT).show()
    }

    override fun myOnClickKT(position: Int) {
        Toast.makeText(applicationContext, "$position", Toast.LENGTH_SHORT).show()
    }
}
