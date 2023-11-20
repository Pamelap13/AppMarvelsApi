package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.getAll.Result

class ListaAdapter(val lista: List<Result>, val callback: Callback) : RecyclerView.Adapter<ListaViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_layout,parent,false)
        return ListaViewHolder(view,callback)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ListaViewHolder, position: Int) {
        holder.bind(lista[position])
    }


}