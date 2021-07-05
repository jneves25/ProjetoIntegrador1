package com.example.catalogofilmesapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.catalogofilmesapp.ui.viewmodel.CadastroViewModel
import com.example.catalogofilmesapp.R
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_cadastro.etLogin
import kotlinx.android.synthetic.main.activity_cadastro.etSenha

class CadastroActivity : AppCompatActivity() {

    private lateinit var viewModel: CadastroViewModel

    companion object{
        const val FIELD_NOME = 1
        const val FIELD_LOGIN = 2
        const val FIELD_SENHA = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        viewModel = ViewModelProvider(this).get(CadastroViewModel::class.java)

        listeners()
        observer()
        prepareActionBar()
    }

    fun prepareActionBar() {
        //Personaliza a barra de ferramentas
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = "Menu Inicial"
        supportActionBar?.elevation = 0f
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    //Funções observavéis da viewModel
    private fun observer() {
        viewModel.isErrorField.observe(this, Observer { erro ->
            Log.i("CadastroActivity",
                    "observer-isErrorfield error message=${erro.message} erro campo = ${erro.campo}")
            if (!erro.message.isNullOrEmpty()){
                when(erro.campo){
                    FIELD_NOME -> etNome.error = erro.message
                    FIELD_LOGIN -> etLogin.error = erro.message
                    FIELD_SENHA -> etLogin.error = erro.message
                }
            }
        })

        viewModel.toastMessage.observe(this, Observer { messageToast ->
            if (!messageToast.isNullOrEmpty()) {
                Toast.makeText(this, messageToast, Toast.LENGTH_SHORT).show()
                viewModel.toastMessage.postValue("")
            }
        })
    }

    //Função responsável por armazenar os métodos "escutadores".
    private fun listeners() {
        etNome.setOnEditorActionListener { _, action, _->
            viewModel.verifyEditText(etNome.text.toString().trim(), FIELD_NOME,action)
            false
        }

        etLogin.setOnEditorActionListener { _, action, _->
            viewModel.verifyEditText(etLogin.text.toString().trim(), FIELD_LOGIN,action)
            false
        }
        etSenha.setOnEditorActionListener { _, action, _->
            viewModel.verifyEditText(etSenha.text.toString().trim(), FIELD_SENHA,action)
            false
        }

       btCadastrar.setOnClickListener {
           viewModel.actionButton(
               etNome.text.toString().trim(),
               etLogin.text.toString().trim(),
               etSenha.text.toString().trim())
       }
    }
}