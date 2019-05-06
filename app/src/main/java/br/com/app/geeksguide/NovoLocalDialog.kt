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
import android.content.DialogInterface
import kotlinx.android.synthetic.main.novo_local_dialog.*


class NovoLocalDialog() : DialogFragment() {

    private lateinit var builder: AlertDialog.Builder
    private lateinit var etLocal: EditText
    private lateinit var etEndereco: EditText
    private lateinit var etHorario: EditText
    private lateinit var local: Local

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        builder = AlertDialog.Builder(activity)

        val v = activity?.layoutInflater?.inflate(R.layout.novo_local_dialog, null)!!

        etLocal = v.findViewById(R.id.etLocal)
        etEndereco = v.findViewById(R.id.etEndereco)
        etHorario = v.findViewById(R.id.etHorario)


        etLocal.setText(local.nome)
        etEndereco.setText(local.endereco)
        etHorario.setText(local.horario)


        var title = ""
        var titlePositive = ""
        if (local?.id != null) {
            title = getString(R.string.editPlaces)
            titlePositive = getString(R.string.edit)

            builder.setNeutralButton(getString(R.string.delete)) { _, _ ->
                val db = BancoDeDados.getDatabase(activity!!.applicationContext)
                DeleteAsyncTask(db!!).execute(local)
            }
        } else {
            title = getString(R.string.addNewLocal)
            titlePositive = getString(R.string.add)
        }

        builder.setView(v)
        builder.setTitle(title)
        builder.setPositiveButton(titlePositive) { _, _ ->

            val db = BancoDeDados.getDatabase(activity!!.applicationContext)

            local.nome = etLocal.text.toString()
            local.endereco = etEndereco.text.toString()
            local.horario = etHorario.text.toString()

            //Log.d("#Local", local.id.toString())
            Log.d("#Local", local.nome)

            if (local.nome != "")
                InsertUpdateAsyncTask(db!!).execute(local)
        }


        builder.setNegativeButton(getString(R.string.cancel), null)


        return builder.create()
    }


    private inner class InsertUpdateAsyncTask internal constructor(appDatabase: BancoDeDados) : AsyncTask<Local, Void, String>() {
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

    private inner class DeleteAsyncTask internal constructor(appDatabase: BancoDeDados) : AsyncTask<Local, Void, String>() {
        private val db: BancoDeDados = appDatabase

        override fun doInBackground(vararg params: Local): String {
            db.localDAO().apagar(params[0])

            return ""
        }
    }

    public fun setLocal(local: Local) {
        this.local = local
    }
}