package com.example.catalogofilmesapp.room.database

import android.content.Context
import com.example.catalogofilmesapp.room.Entity.Filme
import com.example.catalogofilmesapp.room.Entity.Usuario

class FilmeRepository private constructor(context: Context) {

    //Acesso ao banco de dados
    private val mDataBase = FilmeDataBase.getDataBase(context).filomeDao()

    //Carrega Todos Filmes
    fun getFilmes():List<Filme>{
        return mDataBase.getAllFilmes()
    }

    //salva filme
    fun save(filme:Filme):Boolean{
        return mDataBase.addFilme(filme) > 0
    }

    //Deleta um filme
    fun deleteFilme(filme :Filme){
        return mDataBase.removeFilme(filme)
    }

}