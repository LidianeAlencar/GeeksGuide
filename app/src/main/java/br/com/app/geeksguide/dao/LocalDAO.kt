package br.com.app.geeksguide.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import br.com.app.geeksguide.model.Local

@Dao
interface LocalDAO {

    @Insert
    fun inserir(local: Local)

    @Query("SELECT * FROM Local")
    fun lerLocais(): LiveData<List<Local>>

    @Query("SELECT * FROM Local WHERE id = :id")
    fun buscarPor(id: Int): Local

    @Update
    fun atualizar(local: Local)

    @Delete
    fun apagar(local: Local)
}
