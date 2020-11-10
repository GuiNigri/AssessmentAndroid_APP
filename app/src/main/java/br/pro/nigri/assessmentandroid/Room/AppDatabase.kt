package br.pro.nigri.assessmentandroid.Room

import android.content.Context
import androidx.room.Room


object AppDatabase {
    private var db: RoomDatabase? = null
    fun getInstance(context: Context): RoomDatabase {
        if (db == null){
            db = Room
                .databaseBuilder(
                    context,
                    RoomDatabase::class.java,
                    "dbApp"
                )
                .allowMainThreadQueries()
                .build()
        }
        return db as RoomDatabase
    }
}