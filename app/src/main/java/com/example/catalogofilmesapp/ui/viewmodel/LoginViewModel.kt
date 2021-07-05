package com.example.catalogofilmesapp.ui.viewmodel

import android.app.Application
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catalogofilmesapp.model.ErroDeCampo
import com.example.catalogofilmesapp.room.Entity.Usuario
import com.example.catalogofilmesapp.room.database.FilmeDataBase
import com.example.catalogofilmesapp.room.database.UsuarioDataBase
import com.example.catalogofilmesapp.ui.view.CadastroActivity

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private var mContext = application.applicationContext
    private val repository = UsuarioDataBase.getDataBase(mContext).usuarioDao()

    //variável Observável para setar mensagem de erro
    var isErrorField = MutableLiveData<ErroDeCampo>()

    //Mensagem erro
    var toastMessage = MutableLiveData<String>()

    var isLoginSucess = MutableLiveData<Boolean>()

    //Função responsável por verificar se os editTexts informados não estão vazios
    fun verifyEditText(text : String,campo :Int,action :Int) : Boolean{
        //Verifica se ação de clique
        if(action != EditorInfo.IME_ACTION_NEXT) return false

        if (text.isNullOrEmpty()){
            val error = ErroDeCampo("Informe!",campo)
            isErrorField.postValue(error)
        }
        return true
    }

    fun actionButton(email:String,senha:String){
        //Validar se os campos foram preenchidos
        val action = EditorInfo.IME_ACTION_NEXT
        if (!verifyEditText(email, CadastroActivity.FIELD_LOGIN,action)) return
        if (!verifyEditText(senha, CadastroActivity.FIELD_SENHA,action)) return

        if (repository.getUsuario(email,senha) > 0){
            Log.i("LoginViewModel","id = ${repository.getUsuario(email,senha)}")
            isLoginSucess.postValue(true)
        }else{
            toastMessage.postValue("Login Falhou")
        }
    }

}