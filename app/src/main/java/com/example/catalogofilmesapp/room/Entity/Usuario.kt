package com.example.catalogofilmesapp.room.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tabela_usuario")
class Usuario (
    @PrimaryKey(autoGenerate = true)
    val id :Int =0,
    val nome:String ="",
    val email:String ="",
    val senha:String ="")