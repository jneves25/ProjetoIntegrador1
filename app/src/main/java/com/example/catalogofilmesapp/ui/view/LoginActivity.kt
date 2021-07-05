package com.example.catalogofilmesapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.catalogofilmesapp.ui.viewmodel.LoginViewModel
import com.example.catalogofilmesapp.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.etLogin
import kotlinx.android.synthetic.main.activity_login.etSenha

/**
 * Foi acordado o uso da arquitetura MMVM para a realização desse projeto,
 * por ter uma estrutura melhor para lidar com a persistência de dados
 */

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    companion object{
        const val FIELD_LOGIN = 1
        const val FIELD_SENHA = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Instanciando a viewModel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        listeners()
        observer()
    }

    //Funções observavéis da viewModel
    private fun observer() {
        viewModel.isErrorField.observe(this, Observer { erro ->
            Log.i("LoginActivity",
                    "observer-isErrorfield error message=${erro.message} erro campo = ${erro.campo}")
            if (!erro.message.isNullOrEmpty()){
                when(erro.campo){
                    FIELD_LOGIN -> etLogin.error = erro.message
                    FIELD_SENHA -> etLogin.error = erro.message
                }
            }
        })

        viewModel.toastMessage.observe(this, Observer { message ->
            if (!message.isNullOrEmpty()) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                viewModel.toastMessage.postValue("")
            }
        })

        viewModel.isLoginSucess.observe(this, Observer {
            if (it) {
               val intent = Intent(this,MenuActivity::class.java)
                startActivity(intent)
            }else{
                viewModel.toastMessage.postValue("Falha Login")
            }
        })
    }

    private fun listeners() {
        //Faz captura do texto digitado no Campo Login
        etLogin.setOnEditorActionListener { _, action,_ ->
            viewModel.verifyEditText(etLogin.text.toString().trim(), FIELD_LOGIN,action)
            false
        }

        //Faz captura do texto digitado no Campo Senha
        etSenha.setOnEditorActionListener { _, action,_ ->
            viewModel.verifyEditText(etSenha.text.toString().trim(), FIELD_SENHA,action)
            false
        }

        tvCadastrarSe.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener{
            viewModel.actionButton(
                etLogin.text.toString().trim(),
                etSenha.text.toString().trim())
        }
    }
}