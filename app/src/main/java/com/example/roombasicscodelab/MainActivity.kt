package com.example.roombasicscodelab

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roombasicscodelab.adapters.CityAdapter
import com.example.roombasicscodelab.entities.City
import com.example.roombasicscodelab.viewModel.CityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var cityViewModel: CityViewModel
    private val newWordActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    //recycler view
    val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
    val adapter = CityAdapter(this)
    recyclerView.adapter = adapter
    recyclerView.layoutManager = LinearLayoutManager(this)

    cityViewModel = ViewModelProvider(this).get(CityViewModel::class.java)
    cityViewModel.allCities.observe(this, { cities ->
        cities?.let{adapter.setCities(it)}
    })

    val fab = findViewById<FloatingActionButton>(R.id.fab)
    fab.setOnClickListener{
        val intent = Intent(this@MainActivity, AddCity::class.java)
        startActivityForResult(intent,newWordActivityRequestCode)
    }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK){
            data?.getStringExtra(AddCity.EXTRA_REPLY)?.let {
                val city = City(city = it, capital = "Portugal")
                cityViewModel.insert(city)
            }
        }else{
            Toast.makeText(
                applicationContext,
                "Cidade vazia: não inserida",
                Toast.LENGTH_LONG).show()
        }
    }
}