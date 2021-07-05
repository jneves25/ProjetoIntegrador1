package com.example.catalogofilmesapp.room.database

import android.content.Context
import androidx.room.*
import com.example.catalogofilmesapp.room.Dao.UsuarioDAO
import com.example.catalogofilmesapp.room.Entity.Usuario

/**
 * Classe responsavél por fornecer instancia do Banco de Dados para a aplicação
 */
@Database(entities = [Usuario::class], version = 1, exportSchema = false)
abstract class UsuarioDataBase : RoomDatabase() {

    abstract fun usuarioDao() : UsuarioDAO

    //Usando padrão de projeto SINGLETON para obter apenas uma instancia dao Banco de Dados
    companion object {
        private lateinit var INSTANCE: UsuarioDataBase
        fun getDataBase(context: Context): UsuarioDataBase {
            if (!Companion::INSTANCE.isInitialized) {
                INSTANCE =
                    Room.databaseBuilder(context, UsuarioDataBase::class.java, "usuarioBD")
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE
        }
    }
}