package com.example.smartown.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.smartown.dao.NotaDao
import com.example.smartown.entities.Nota
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Nota::class), version = 5, exportSchema = false)
public abstract class NotaDB : RoomDatabase() {

    abstract fun notaDao(): NotaDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var notaDao = database.notaDao()

                    notaDao.deleteAll()

                    // adicionar notas

                    var nota = Nota(1, "Nota 1", "Problema", "Alcatrão levantado")
                    notaDao.insert(nota)
                    nota = Nota(2, "Nota 2", "Transito", "Não passar em x horas")
                    notaDao.insert(nota)
                    nota = Nota(3, "Nota 3", "Barulho", "Fazer queixa")
                    notaDao.insert(nota)

                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NotaDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NotaDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotaDB::class.java,
                    "notas_database"
                )
                    //estratégia de destruição
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}