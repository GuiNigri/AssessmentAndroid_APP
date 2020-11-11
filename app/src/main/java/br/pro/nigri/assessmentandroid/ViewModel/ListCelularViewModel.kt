package br.pro.nigri.assessmentandroid.ViewModel

import androidx.lifecycle.ViewModel
import br.pro.nigri.assessmentandroid.Model.CelularModel
import br.pro.nigri.assessmentandroid.Model.ContatoModel
import br.pro.nigri.assessmentandroid.Room.RoomDatabase

class ListCelularViewModel:ViewModel() {
    fun getCelularById(db:RoomDatabase,id:Int):Array<CelularModel>{
        return db.celularDAO().getCelularById(id)
    }
}