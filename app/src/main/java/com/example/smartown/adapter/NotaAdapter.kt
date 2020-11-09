package com.example.smartown.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartown.R
import com.example.smartown.entities.Nota

class NotaAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notas = emptyList<Nota>() // Cached copy of cities

    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloItemView: TextView = itemView.findViewById(R.id.titulo)
        val subItemView: TextView = itemView.findViewById(R.id.subtitulo)
        val descItemView: TextView = itemView.findViewById(R.id.descricao)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerline, parent, false)
        return NotaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val current = notas[position]
        holder.tituloItemView.text = current.id.toString() + " - " + current.titulo
        holder.subItemView.text = current.subtitulo
        holder.descItemView.text = current.descricao
    }

    internal fun setNotas(notas: List<Nota>) {
        this.notas = notas
        notifyDataSetChanged()
    }

    override fun getItemCount() = notas.size
}