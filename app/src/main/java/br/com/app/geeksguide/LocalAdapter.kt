package br.com.app.geeksguide

import android.app.Dialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import br.com.app.geeksguide.model.Local
import android.content.ContextWrapper



class LocalAdapter(var context: Context, var locais: List<Local>) : RecyclerView.Adapter<LocalAdapter.LocalViewHolder>() {
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
      //  holder.tvEndereco.text = local.endereco
        holder.tvHorario.text = local.horario


        holder.tvLocal.setOnClickListener {view ->

            (this.context as ListarActivity).mostrarDialog(local)
            true
        }

    }

    class LocalViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvLocal: TextView = v.findViewById(R.id.tvLocal)
       // var tvEndereco: TextView = v.findViewById(R.id.tvEndereco)
        var tvHorario: TextView = v.findViewById(R.id.tvHorario)

        //var tvPlataforma: TextView = v.findViewById(R.id.tvPlataforma)
    }
}

