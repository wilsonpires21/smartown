package com.example.smartown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartown.adapter.LineAdapter
import com.example.smartown.dataclasses.Notas
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{

        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.inserir -> {
                val intent = Intent(this, criar_nota::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
    private lateinit var myList: ArrayList<Notas>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myList = ArrayList<Notas>()

        for (i in 0 until 500) {
            myList.add(Notas("Titulo $i", "Subtitulo", "Descricao $i"))
        }

        recycler_view.adapter = LineAdapter(myList)
        recycler_view.layoutManager = LinearLayoutManager(this)

    }


    fun insert(view: View) {
        myList.add(0, Notas("Country XXX", "Olá", "Capital XXX"))
        recycler_view.adapter?.notifyDataSetChanged()

    }

}