package br.com.app.geeksguide

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.support.v4.app.DialogFragment
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import br.com.app.geeksguide.dao.BancoDeDados
import br.com.app.geeksguide.model.Local


class NovoLocalDialog() : DialogFragment() {

    private lateinit var builder: AlertDialog.Builder
    private lateinit var etLocal: EditText
    private lateinit var local: Local

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        builder = AlertDialog.Builder(activity)

        val v = activity?.layoutInflater?.inflate(R.layout.novo_local_dialog, null)!!

        etLocal = v.findViewById(R.id.etLocal)

        etLocal.setText(local.nome)

        var title = ""
        var titlePositive = ""
        if (local?.id != null) {
            title = "Editar Local"
            titlePositive = "Alterar"
        } else {
            title = "Adicionar Local"
            titlePositive = "Adicionar"
        }

        builder.setView(v)
        builder.setTitle(title)
        builder.setPositiveButton(titlePositive) { _, _ ->

            val db = BancoDeDados.getDatabase(activity!!.applicationContext)

            local.nome = etLocal.text.toString()
            //Log.d("#Local", local.id.toString())
            Log.d("#Local", local.nome)

            if (local.nome != "")
                InsertAsyncTask(db!!).execute(local)
        }


        builder.setNegativeButton("Cancelar", null)
        return builder.create()
    }


    private inner class InsertAsyncTask internal constructor(appDatabase: BancoDeDados) : AsyncTask<Local, Void, String>() {
        private val db: BancoDeDados = appDatabase

        override fun doInBackground(vararg params: Local): String {
            if(params[0]?.id != null ) {
                db.localDAO().atualizar(params[0])
            } else {
                db.localDAO().inserir(params[0])
            }

            return ""
        }
    }

    public fun setLocal(local: Local) {
        this.local = local
    }
}