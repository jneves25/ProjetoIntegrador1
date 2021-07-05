package com.example.catalogofilmesapp.room.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.catalogofilmesapp.room.Entity.Usuario


//Contem metodos de acesso ao banco de dados

@Dao
interface UsuarioDAO {

    @Insert
    fun addUsuario(user : Usuario) :Long

    @Query("SELECT id FROM tabela_usuario WHERE email =:email AND senha =:senha")
    fun getUsuario(email:String,senha:String) :Long

    @Query("SELECT * FROM tabela_usuario ORDER BY id = :id")
    fun readAllData(id:Int): Usuario
}