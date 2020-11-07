package com.example.smartown.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.smartown.db.NotaDB
import com.example.smartown.db.NotaRepository
import com.example.smartown.entities.Nota
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : NotaRepository

    val allNotas: LiveData<List<Nota>>

    init {
        val notaDao = NotaDB.getDatabase(application, viewModelScope).notaDao()
        repository = NotaRepository(notaDao)
        allNotas = repository.allNotas
    }
    fun insert(nota: Nota) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(nota)
    }
}