package com.example.smartown.db

import androidx.lifecycle.LiveData
import com.example.smartown.dao.NotaDao
import com.example.smartown.dataclasses.Notas
import com.example.smartown.entities.Nota

class NotaRepository(private val notaDao: NotaDao) {

    val allNotas: LiveData<List<Nota>> =  notaDao.getAlphabetizedNotes()
    suspend fun insert (nota: Nota){
        notaDao.insert(nota)
    }
}