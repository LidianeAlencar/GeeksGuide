package br.com.app.geeksguide

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import br.com.app.geeksguide.dao.BancoDeDados
import br.com.app.geeksguide.model.Local

class ListarViewModel(application: Application) : AndroidViewModel(application) {

    private val bd: BancoDeDados = BancoDeDados.getDatabase(application.applicationContext)!!

    lateinit var locais: LiveData<List<Local>>

    init {
        carregarDados()
    }

    private fun carregarDados() {
        //Carregar os dados da nossa Base de dados e armazenar no LiveData
        locais = bd.localDAO().lerLocais()
    }
}
