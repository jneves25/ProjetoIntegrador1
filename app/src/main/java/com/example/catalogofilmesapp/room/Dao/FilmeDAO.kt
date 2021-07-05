package com.example.catalogofilmesapp.room.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.catalogofilmesapp.room.Entity.Filme

@Dao
interface FilmeDAO {

    @Insert
    fun addFilme(filme : Filme):Long

    @Query("SELECT * FROM tabela_filmes")
    fun getAllFilmes():List<Filme>

    @Delete
    fun removeFilme(filme : Filme)
}