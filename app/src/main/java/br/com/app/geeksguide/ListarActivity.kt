package br.com.app.geeksguide

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import br.com.app.geeksguide.model.Local
import kotlinx.android.synthetic.main.activity_listar.*
import kotlinx.android.synthetic.main.content_lista.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import com.google.firebase.iid.FirebaseInstanceId


class ListarActivity : AppCompatActivity() {

    private var adapter: LocalAdapter? = null

    private var locais: List<Local> = listOf()

    private lateinit var listarViewModel: ListarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)

        fab.setOnClickListener {
            mostrarDialog(Local(null, null, null, null ))
        }

        mostrarDados()

        rvLocais.layoutManager = LinearLayoutManager(this)
        adapter = LocalAdapter(this, locais)
        rvLocais.adapter = adapter

        val token = FirebaseInstanceId.getInstance().token
        Log.i("###TOKEN", token)
    }


    private fun mostrarDados() {
        //of()?—?indica a activity ou Fragment em que o ViewModel será utilizado
        //get()?—?indica o ViewModel que será utilizado.
        listarViewModel = ViewModelProviders.of(this)
                .get(ListarViewModel::class.java)

        listarViewModel.locais
                .observe(this, Observer<List<Local>> { locais ->
                    adapter?.setList(locais!!)
                    rvLocais.adapter?.notifyDataSetChanged()
                })
    }

    public fun mostrarDialog(local: Local) {
        val dialog = NovoLocalDialog()
        dialog.setLocal(local)

        dialog.show(supportFragmentManager, "CriarLocal")
    }
}