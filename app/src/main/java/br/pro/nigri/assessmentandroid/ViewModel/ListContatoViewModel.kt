package br.pro.nigri.assessmentandroid.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.pro.nigri.assessmentandroid.DAO.ContatosDAO
import br.pro.nigri.assessmentandroid.Model.ContatoModel
import br.pro.nigri.assessmentandroid.Room.RoomDatabase

class ListContatoViewModel:ViewModel() {
    fun all(db: RoomDatabase):Array<ContatoModel>{
        return  db.contatosDAO().all()
    }

}