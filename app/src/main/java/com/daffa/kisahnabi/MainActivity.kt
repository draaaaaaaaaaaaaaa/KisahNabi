package com.daffa.kisahnabi

import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_DATA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.daffa.kisahnabi.data.KisahResponse
import com.daffa.kisahnabi.databinding.ActivityMainBinding
import com.daffa.kisahnabi.ui.KisahAdapter
import com.daffa.kisahnabi.ui.MainViewModel
import com.daffa.kisahnabi.ui.OnItemClickCallback
import kotlin.math.log

class MainActivity : AppCompatActivity() , OnItemClickCallback{

    private var _binding: ActivityMainBinding? = null
    private  val binding get() = _binding as ActivityMainBinding

    private  var  _ViewModel : MainViewModel? = null
    private  val viewModel get() = _ViewModel as MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _ViewModel = ViewModelProvider(this)[MainViewModel ::class.java]

        viewModel.getData()

        viewModel.KisahResponse.observe(this){
            showData(it)
        }

        viewModel.isLoading.observe(this){showLoading(it)}

        viewModel.isError.observe(this){ showError(it)}

    }

    private fun showData(data: List<KisahResponse>?) {
        binding.recyclerMain.apply {
            val  mAdapter = KisahAdapter()
            mAdapter.setData(data)
            layoutManager = GridLayoutManager(this@MainActivity,2)
            adapter =mAdapter
            mAdapter.setOnItemClickCallback(object  : OnItemClickCallback{
                override fun onItemClicked(item: KisahResponse) {
                    startActivity(
                        Intent(applicationContext,DetailActivity::class.java)
                            .putExtra(DetailActivity.EXTRA_DATA,item)
                    )
                }
            })

        }
    }

    private fun showLoading(isLoading: Boolean?) {
        if (isLoading == false){
            binding.progressMain.visibility = View.VISIBLE
            binding.recyclerMain.visibility =View.INVISIBLE
        } else{
            binding.progressMain.visibility = View.VISIBLE
            binding.recyclerMain.visibility = View.INVISIBLE
        }
    }

    private fun showError(error: Throwable?) {
        Log.e("MainActivity","showError : $error")


    }

    override fun onItemClicked(item: KisahResponse) {

    }
}