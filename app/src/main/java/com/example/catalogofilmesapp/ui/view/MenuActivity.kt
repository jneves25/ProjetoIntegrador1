package com.example.catalogofilmesapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catalogofilmesapp.R
import com.example.catalogofilmesapp.room.Dao.FilmeDAO
import com.example.catalogofilmesapp.room.database.FilmeDataBase
import com.example.catalogofilmesapp.ui.adapter.MenuActitvityAdapter
import com.example.catalogofilmesapp.ui.viewmodel.MenuViewModel
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    private lateinit var adapter: MenuActitvityAdapter
    private lateinit var viewModel: MenuViewModel

    private lateinit var repository :FilmeDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        repository = FilmeDataBase.getDataBase(this).filomeDao()
        listeners()
        prepareRecyclerView()
    }

//    override fun onResume() {
//        adapter.addDataSet(repository.getAllFilmes())
//        super.onResume()
//    }

    private fun listeners() {
        fabMenu.setOnClickListener {
            val intent= Intent(this,AdicionarFilmeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun prepareRecyclerView() {
        adapter = MenuActitvityAdapter(this,viewModel)
        adapter.dataSet.addAll(repository.getAllFilmes())
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }

}