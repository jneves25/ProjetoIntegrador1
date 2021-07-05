package com.example.catalogofilmesapp.ui.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catalogofilmesapp.model.ErroDeCampo
import com.example.catalogofilmesapp.room.Entity.Filme
import com.example.catalogofilmesapp.room.Entity.Usuario
import com.example.catalogofilmesapp.room.database.FilmeDataBase
import com.example.catalogofilmesapp.room.database.UsuarioDataBase
import com.example.catalogofilmesapp.ui.view.AdicionarFilmeActivity
import com.example.catalogofilmesapp.ui.view.CadastroActivity

class AdicionarFilmeViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val repository = FilmeDataBase.getDataBase(mContext).filomeDao()

    //variável Observável para setar mensagem de erro
    var isErrorField = MutableLiveData<ErroDeCampo>()

    //Mensagem erro
    var toastMessage = MutableLiveData<String>()

    var isAddFilmeSucess = MutableLiveData<Boolean>()


    //Função responsável por verificar se os editTexts informados não estão vazios
    fun verifyEditText(text : String,campo :Int, action :Int) : Boolean{
        //Verifica se ação de clique
        Log.i("CadastroViewModel",
                "verifyEditText ->ImeAction -> ${action != EditorInfo.IME_ACTION_NEXT} ")
        if(action != EditorInfo.IME_ACTION_NEXT) return false

        if (text.isNullOrEmpty()){
            Log.i("CadastroViewModel",
                    "verifyEditText ->dentro laço verifica vazio text=$text")
            val error = ErroDeCampo("Informe!",campo)
            isErrorField.postValue(error)
            return false
        }
        return true
    }

    fun actionButton(titulo :String,descricao:String){
        //Validar se os campos foram preenchidos
        val action = EditorInfo.IME_ACTION_NEXT
        if (!verifyEditText(titulo,AdicionarFilmeActivity.FIELD_TITULO,action)) return
        if (!verifyEditText(descricao, AdicionarFilmeActivity.FIELD_DESCRICAO,action)) return

        val filme = Filme(titulo = titulo,descricao = descricao)

        if (repository.addFilme(filme) > 0){
            isAddFilmeSucess.postValue(true)
        }else{
            toastMessage.postValue("Falha Registro Filme")
        }
    }
}