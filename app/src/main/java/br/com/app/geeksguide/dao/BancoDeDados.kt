package br.com.app.geeksguide.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.util.Log
import br.com.app.geeksguide.model.Local


@Database(entities = arrayOf(Local::class), version = 1)
abstract class BancoDeDados : RoomDatabase() {

    abstract fun localDAO(): LocalDAO

    companion object {

        var INSTANCE: BancoDeDados? = null

        fun getDatabase(context: Context): BancoDeDados? {

            if (INSTANCE == null) {
                Log.d("DB", "DB")
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                        BancoDeDados::class.java,
                        "locaisdbs")
                        .build()
            }
            return INSTANCE
        }
    }

}
