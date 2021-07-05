package com.example.catalogofilmesapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.catalogofilmesapp.room.Entity.Filme
import com.example.catalogofilmesapp.room.database.FilmeDataBase

class MenuViewModel (application: Application) :AndroidViewModel(application) {

    private var mContext = application.applicationContext
    private val repository = FilmeDataBase.getDataBase(mContext).filomeDao()


    fun removeFilme(filme :Filme):Boolean{
        repository.removeFilme(filme)
        return true
    }

}