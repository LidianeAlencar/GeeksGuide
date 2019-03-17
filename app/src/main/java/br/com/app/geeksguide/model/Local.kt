package br.com.app.geeksguide.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

//A anotacao @Entity abaixo indica indica que
// a classe sera uma tabela SQL
@Entity
data class Local (
        //A anotacao PrimaryKey indica que a variável id será chave primária
        //da nossa base dados e ativamos o autoGenerate para que o id
        //seja gerado automaticamente
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        var nome: String? = null
       )