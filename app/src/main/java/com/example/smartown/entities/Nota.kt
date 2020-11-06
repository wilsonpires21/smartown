package com.example.smartown.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="nota_table")

class Nota (
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "titulo") val titulo: String,
    @ColumnInfo(name = "subtitulo") val subtitulo: String,
    @ColumnInfo(name = "descricao") val descricao: String
)
