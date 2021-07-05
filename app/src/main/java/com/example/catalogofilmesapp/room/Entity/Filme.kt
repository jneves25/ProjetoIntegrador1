package com.example.catalogofilmesapp.room.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabela_filmes")
class Filme (
    @PrimaryKey(autoGenerate = true)
    val id :Int =0 ,
    val titulo :String = "",
    val descricao :String = "",
    )