package com.example.catalogofilmesapp.ui.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catalogofilmesapp.model.ErroDeCampo
import com.example.catalogofilmesapp.room.Entity.Usuario
import com.example.catalogofilmesapp.room.database.UsuarioDataBase
import com.example.catalogofilmesapp.ui.view.CadastroActivity

class CadastroViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val repository = UsuarioDataBase.getDataBase(mContext).usuarioDao()

    //variável Observável para setar mensagem de erro
    var isErrorField = MutableLiveData<ErroDeCampo>()

    //Mensagem erro
    var toastMessage = MutableLiveData<String>()


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

    fun actionButton(name :String,email:String,senha:String){
        //Validar se os campos foram preenchidos
        val action = EditorInfo.IME_ACTION_NEXT
        if (!verifyEditText(name, CadastroActivity.FIELD_NOME,action)) return
        if (!verifyEditText(email, CadastroActivity.FIELD_LOGIN,action)) return
        if (!verifyEditText(senha, CadastroActivity.FIELD_SENHA,action)) return
        val user = Usuario(nome = name,email = email,senha = senha)

        Log.i("CadastroViewModel", "retorno add= ${repository.addUsuario(user) > 0}")
        if (repository.addUsuario(user) > 0){toastMessage.postValue("Cadastro Efetuado")}
    }
}