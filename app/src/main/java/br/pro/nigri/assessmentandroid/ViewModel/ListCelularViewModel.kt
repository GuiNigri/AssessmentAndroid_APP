package br.pro.nigri.assessmentandroid.ViewModel

import androidx.lifecycle.ViewModel
import br.pro.nigri.assessmentandroid.Model.CelularModel
import br.pro.nigri.assessmentandroid.Model.ContatoModel
import br.pro.nigri.assessmentandroid.Room.RoomDatabase

class ListCelularViewModel:ViewModel() {
    fun all(db: RoomDatabase):Array<CelularModel>{
        return  db.celularDAO().all()
    }

    fun show(db:RoomDatabase,id:Int):Array<CelularModel>{
        return db.celularDAO().show(id)
    }
}