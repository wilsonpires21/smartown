package com.example.smartown

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartown.adapter.NotaAdapter
import com.example.smartown.entities.Nota
import com.example.smartown.viewModel.NotaViewModel
import kotlinx.android.synthetic.main.activity_listanotas.*


class lista_notas : AppCompatActivity() {

    private lateinit var notaViewModel: NotaViewModel
    private val newWordActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listanotas)

        val adapter = NotaAdapter(this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        notaViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)
        notaViewModel.allNotas.observe(this, Observer { notas ->

            notas?.let { adapter.setNotas(it) }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.inserir -> {
                val intent = Intent(this@lista_notas, criar_nota::class.java)
                startActivityForResult(intent, newWordActivityRequestCode)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val ptitulo = data?.getStringExtra(criar_nota.EXTRA_REPLY_TITULO)
            val psubtitulo = data?.getStringExtra(criar_nota.EXTRA_REPLY_SUBTITULO)
            val pdescricao = data?.getStringExtra(criar_nota.EXTRA_REPLY_DESCRICAO)

            if (ptitulo != null && psubtitulo != null && pdescricao != null) {
                val nota = Nota(titulo = ptitulo, subtitulo = psubtitulo, descricao = pdescricao)
                notaViewModel.insert(nota)
            }

        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}
