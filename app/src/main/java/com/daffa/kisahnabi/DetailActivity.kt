package com.daffa.kisahnabi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.daffa.kisahnabi.data.KisahResponse
import com.daffa.kisahnabi.databinding.ActivityDetailBinding

private var _binding : ActivityDetailBinding? = null
private val binding get() = _binding as ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<KisahResponse>(EXTRA_DATA)
        binding.apply {
            Glide.with(this@DetailActivity).load(data?.imageUrl).into(detailImage)
            detailNama.text = data?.name
            detailTempat.text = data?.tmp
            detailUsia.text = "Usia :" + data?.usia
            detailDesk.text = data?.description
        }
    }

    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}