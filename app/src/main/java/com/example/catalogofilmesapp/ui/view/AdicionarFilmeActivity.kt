package com.example.catalogofilmesapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.catalogofilmesapp.R
import com.example.catalogofilmesapp.ui.viewmodel.AdicionarFilmeViewModel
import kotlinx.android.synthetic.main.activity_adicionar_filme.*
import kotlinx.android.synthetic.main.activity_login.*

class AdicionarFilmeActivity : AppCompatActivity() {

    private lateinit var viewModel : AdicionarFilmeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_filme)

        viewModel = ViewModelProvider(this).get(AdicionarFilmeViewModel::class.java)
        listeners()
        observer()
    }

    private fun observer() {
        viewModel.isErrorField.observe(this, Observer { erro ->
            Log.i("AdicionarFilmeActivity",
                "observer-isErrorfield error message=${erro.message} erro campo = ${erro.campo}")
            if (!erro.message.isNullOrEmpty()){
                when(erro.campo){
                    FIELD_TITULO -> etTitulo.error = erro.message
                    FIELD_DESCRICAO -> etDescricao.error = erro.message
                }
            }
        })
        viewModel.toastMessage.observe(this, Observer {message->
            if (!message.isNullOrEmpty()) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                viewModel.toastMessage.postValue("")
            }
        })
        viewModel.isAddFilmeSucess.observe(this, Observer {
            if (it) {
                viewModel.toastMessage.postValue("Cadastro Filme Efetuado")
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }
            viewModel.isAddFilmeSucess.postValue(false)
        })
    }

    companion object{
        const val FIELD_TITULO = 1
        const val FIELD_DESCRICAO = 2
    }
    private fun listeners() {
        etTitulo.setOnEditorActionListener { _, action, _ ->
            viewModel.verifyEditText(etTitulo.text.toString().trim(), FIELD_TITULO,action)
            false
        }

        etDescricao.setOnEditorActionListener { _, action, _ ->
            viewModel.verifyEditText(etDescricao.text.toString().trim(), FIELD_DESCRICAO,action)
            false
        }

        btAdicionarFilme.setOnClickListener {
            viewModel.actionButton(
                etTitulo.text.toString().trim(),
                etDescricao.text.toString().trim())
        }
    }
}