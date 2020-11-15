package com.example.smartown

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.smartown.api.EndPoints
import com.example.smartown.api.OutputPost
import com.example.smartown.api.ServiceBuilder
import com.example.smartown.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //vai para as notas
        var botao_notas: Button? = null
        botao_notas = findViewById(R.id.button_irnotas)
        botao_notas?.setOnClickListener {
            val intent = Intent(this@MainActivity, lista_notas::class.java)
            startActivity(intent)

        }
        //faz login ( vai para página do mapa)
        var botao_mapa: Button? = null
        botao_mapa = findViewById(R.id.button_login)
        botao_mapa?.setOnClickListener {

            val username = findViewById<EditText>(R.id.campo_user)
            val password = findViewById<EditText>(R.id.campo_pass)

            val request = ServiceBuilder.buildService(EndPoints::class.java)
            val call = request.postLogin(username.text.toString(), password.text.toString())

            call.enqueue(object : Callback<OutputPost> {
                override fun onResponse(call: Call<OutputPost>, response: Response<OutputPost>) {

                    Log.e("Sucesso", response.toString())
                    if (response.isSuccessful) {


                        val intent = Intent(this@MainActivity, Mapa::class.java)
                        startActivity(intent)


                    }
                }

                override fun onFailure(call: Call<OutputPost>, t: Throwable) {
                    Log.e("Error", t.toString())
                    username.error = "Erro ao efetuar login!"
                    password.error = "Erro ao efetuar login!"

                }
            })

        }
    }
}