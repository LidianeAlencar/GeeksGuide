package br.com.app.geeksguide

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.app.geeksguide.model.Local

class LocalAdapter(var locais: List<Local>) : RecyclerView.Adapter<LocalAdapter.LocalViewHolder>() {
    override fun getItemCount(): Int {
        return locais.size
    }

    fun setList(locais: List<Local>) {
        this.locais = locais
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_local, parent, false)
        return LocalViewHolder(v)
    }

    override fun onBindViewHolder(holder: LocalViewHolder, i: Int) {
        val local = locais[i]
        holder.tvLocal.text = local.nome
    }

    class LocalViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvLocal: TextView = v.findViewById(R.id.tvLocal)
        //var tvPlataforma: TextView = v.findViewById(R.id.tvPlataforma)
    }
}

