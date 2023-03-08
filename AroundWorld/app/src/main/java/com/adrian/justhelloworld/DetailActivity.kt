package com.adrian.justhelloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adrian.justhelloworld.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val dataCountry = intent.getParcelableExtra("key_country") as? Country

        if (dataCountry != null) {
            binding.titleDetail.text = dataCountry.name
            binding.descriptionDetail.text = dataCountry.description
            binding.imageDetail.setImageResource(dataCountry.photo)
            binding.uniqueimageDetail.setImageResource(dataCountry.uniquephoto)
        }

        binding.actionShare.setOnClickListener {
            val message = "Unique Around : ${dataCountry?.name}"
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_TEXT, message)
            shareIntent.type = "text/plain"

            val shareIntentChooser = Intent.createChooser(shareIntent, "Share via")
            startActivity(shareIntentChooser)
        }
    }
}