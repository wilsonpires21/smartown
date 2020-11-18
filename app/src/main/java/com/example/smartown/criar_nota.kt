package com.example.smartown

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.text.TextUtils
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class criar_nota : AppCompatActivity() {

    private lateinit var tituloText: EditText
    private lateinit var subText: EditText
    private lateinit var descText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_nota)

        tituloText = findViewById(R.id.editTextTextPersonName)
        subText = findViewById(R.id.editTextTextPersonName2)
        descText = findViewById(R.id.editTextTextPersonName3)

        val button = findViewById<Button>(R.id.inserir_prob)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(tituloText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(EXTRA_REPLY_TITULO, tituloText.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_SUBTITULO, subText.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_DESCRICAO, descText.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY_TITULO = "com.example.android.titulo"
        const val EXTRA_REPLY_SUBTITULO = "com.example.android.subtitulo"
        const val EXTRA_REPLY_DESCRICAO = "com.example.android.descricao"
    }
}


