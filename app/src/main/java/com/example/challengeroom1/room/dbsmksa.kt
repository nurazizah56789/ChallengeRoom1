package com.example.challengeroom1.room

import android.content.Context
import androidx.room.*


@Database(entities = [Tbsiswa::class], version = 1)
abstract class dbsmksa : RoomDatabase(){
    abstract fun tbsiswaDao():TbsiswaDao

    companion object{

        @Volatile private var instance : dbsmksa? = null
        private val Lock = Any()
        operator fun invoke(context: Context) = instance?: synchronized(Lock){
            instance?: buildDatabase(context).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder (
            context.applicationContext,
            dbsmksa::class.java,
            "smksa.db"
        ).build()



    }
}

