package br.pro.nigri.assessmentandroid.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import br.pro.nigri.assessmentandroid.DAO.CelularDAO
import br.pro.nigri.assessmentandroid.DAO.ContatosDAO
import br.pro.nigri.assessmentandroid.Model.CelularModel
import br.pro.nigri.assessmentandroid.Model.ContatoModel

@Database(
    entities = arrayOf(
        ContatoModel::class,
        CelularModel::class
    ),
    version = 1
)

abstract class RoomDatabase: RoomDatabase() {
    abstract fun contatosDAO(): ContatosDAO
    abstract fun celularDAO(): CelularDAO
}