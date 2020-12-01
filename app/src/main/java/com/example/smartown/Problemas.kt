package com.example.smartown

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smartown.api.EndPoints
import com.example.smartown.api.Location
import com.example.smartown.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Problemas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problemas)
    }

    fun addProb(prob : View){

        val tipo_id = findViewById<EditText>(R.id.campo_tipo).text.toString().toInt()
        val lat = findViewById<EditText>(R.id.campo_lat).text.toString().toFloat()
        val lng = findViewById<EditText>(R.id.campo_lng).text.toString().toFloat()
        val descr = findViewById<EditText>(R.id.campo_descr).text.toString()

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.postProb(tipo_id, lat, lng, descr)

        call.enqueue(object : Callback<Location> {

            override fun onResponse(call: Call<Location>, response: Response<Location>) {

            }

            override fun onFailure(call: Call<Location>, t: Throwable) {

            }
        })

        onBackPressed()

    }
}

