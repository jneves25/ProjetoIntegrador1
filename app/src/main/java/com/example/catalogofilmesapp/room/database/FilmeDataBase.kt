package com.example.catalogofilmesapp.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.catalogofilmesapp.room.Dao.FilmeDAO
import com.example.catalogofilmesapp.room.Entity.Filme


@Database(entities = [Filme::class],version = 1,exportSchema = false)
abstract class FilmeDataBase :RoomDatabase() {

        abstract fun filomeDao() : FilmeDAO

        //Usando padrão de projeto SINGLETON para obter apenas uma instancia dao Banco de Dados
        companion object {
            private lateinit var INSTANCE: FilmeDataBase

            fun getDataBase(context: Context): FilmeDataBase {
                if (!Companion::INSTANCE.isInitialized) {
                    INSTANCE =
                        Room.databaseBuilder(context, FilmeDataBase::class.java, "filmeBD")
                            .allowMainThreadQueries()
                            .build()
                }
                return INSTANCE
            }
        }
}