package com.example.catalogofilmesapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catalogofilmesapp.R
import com.example.catalogofilmesapp.room.Entity.Filme
import com.example.catalogofilmesapp.ui.viewmodel.MenuViewModel
import java.lang.Exception


class MenuActitvityAdapter(context: Context ,
                           private val viewModel: MenuViewModel)
    : RecyclerView.Adapter<MenuActitvityAdapter.ViewHolder>() {

    var dataSet = mutableListOf<Filme>()

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var tvTitulo : TextView
        var tvDescricao : TextView

        init{
            tvTitulo = itemView.findViewById(R.id.tvTitulo)
            tvDescricao = itemView.findViewById(R.id.tvDescrição)
        }
    }

    //Infla o layout do ItemFilme criado
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_filme, parent, false)
        return ViewHolder(view)
    }

    //Faz a ligação dos dados com o seu respectivo view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitulo.text = dataSet[position].titulo
        holder.tvDescricao.text = dataSet[position].descricao

        holder.itemView.setOnClickListener {
            try {
                if(viewModel.removeFilme(dataSet[position])){
                    dataSet.removeAt(position)
                    notifyItemRemoved(position)
                }
            }catch (e :Exception){
                Log.e("error",e.message.toString())
            }

        }
    }

    //Retorna numero de itens que serão exibidos
    override fun getItemCount() = dataSet.size
}