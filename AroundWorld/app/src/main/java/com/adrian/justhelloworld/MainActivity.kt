package com.adrian.justhelloworld

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.justhelloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Country>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCountry.setHasFixedSize(true)
        list.addAll(getListCountry())
        showRecyclerList()

        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvCountry.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvCountry.layoutManager = LinearLayoutManager(this)
        }

        supportActionBar?.title = "Around World !"
    }

    private fun showRecyclerList() {
        binding.rvCountry.layoutManager = LinearLayoutManager(this)
        val listCountryAdapter = ListCountryAdapter(list)
        binding.rvCountry.adapter = listCountryAdapter
    }

    private fun getListCountry(): Collection<Country> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataUniquePhoto = resources.obtainTypedArray(R.array.data_uniquephoto)

        val listCountry = ArrayList<Country>()
        for (i in dataName.indices) {
            val food = Country(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataUniquePhoto.getResourceId(i, -1))
            listCountry.add(food)
        }
        return listCountry
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                binding.rvCountry.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                binding.rvCountry.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.about_page -> {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}