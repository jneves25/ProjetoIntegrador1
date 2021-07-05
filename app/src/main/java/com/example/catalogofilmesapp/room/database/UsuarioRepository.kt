package com.example.catalogofilmesapp.room.database

import android.content.Context
import com.example.catalogofilmesapp.room.Entity.Usuario

class UsuarioRepository private constructor(context: Context) {

    //Acesso ao banco de dados
    private val mDataBase = UsuarioDataBase.getDataBase(context).usuarioDao()

    //Carrega Usuario
    fun get(id:Int):Usuario{
        return mDataBase.readAllData(id)
    }

    //salva usuario
    fun save(usuario: Usuario):Boolean{
        return mDataBase.addUsuario(usuario) > 0
    }


}