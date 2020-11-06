package com.example.smartown.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartown.R
import com.example.smartown.dataclasses.Notas
import kotlinx.android.synthetic.main.recyclerline.view.*
import kotlinx.android.synthetic.main.recyclerline.view.titulo


class LineAdapter(val list: ArrayList<Notas>):RecyclerView.Adapter<LineViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder {

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerline, parent, false);
        return LineViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {
        val currentPlace = list[position]

        holder.titulo.text = currentPlace.titulo
        holder.subtitulo.text = currentPlace.subtitulo
        holder.descricao.text = currentPlace.descricao.toString()
    }

}

class LineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val titulo = itemView.titulo
    val subtitulo = itemView.subtitulo
    val descricao = itemView.descricao

}

