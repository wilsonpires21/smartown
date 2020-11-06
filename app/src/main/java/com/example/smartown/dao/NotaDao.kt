package com.example.smartown.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smartown.entities.Nota



interface NotaDao {

    @Query("SELECT * FROM nota_table ORDER BY titulo ASC")
    fun getAlphabetizedNotes(): LiveData<List<Nota>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(nota: Nota)

    @Query("DELETE FROM nota_table")
    suspend fun deleteAll()
}