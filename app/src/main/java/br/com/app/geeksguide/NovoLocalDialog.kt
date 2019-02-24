package br.com.app.geeksguide

import android.app.AlertDialog
import android.app.Dialog
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


class NovoLocalDialog : DialogFragment() {

    private lateinit var builder: AlertDialog.Builder
    private lateinit var etLocal: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        builder = AlertDialog.Builder(activity)

        val v = activity?.layoutInflater?.inflate(R.layout.novo_local_dialog, null)!!

        etLocal = v.findViewById(R.id.etLocal)

        builder.setView(v)
        builder.setTitle("Novo Local")
        builder.setPositiveButton("Adicionar") { _, _ ->

            val db = BancoDeDados.getDatabase(activity!!.applicationContext)

            val local = Local(null,
                    etLocal.text.toString())
            if (local.nome != "")
                InsertAsyncTask(db!!).execute(local)
        }


        builder.setNegativeButton("Cancelar", null)
        return builder.create()
    }


    private inner class InsertAsyncTask internal constructor(appDatabase: BancoDeDados) : AsyncTask<Local, Void, String>() {
        private val db: BancoDeDados = appDatabase

        override fun doInBackground(vararg params: Local): String {
            db.localDAO().inserir(params[0])
            return ""
        }
    }
}