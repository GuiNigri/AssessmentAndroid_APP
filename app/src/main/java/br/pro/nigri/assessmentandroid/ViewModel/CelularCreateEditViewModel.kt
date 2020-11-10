package br.pro.nigri.assessmentandroid.ViewModel

import androidx.lifecycle.ViewModel
import br.pro.nigri.assessmentandroid.DAO.CelularDAO
import br.pro.nigri.assessmentandroid.DAO.ContatosDAO
import br.pro.nigri.assessmentandroid.Model.CelularModel
import br.pro.nigri.assessmentandroid.Model.ContatoModel

class CelularCreateEditViewModel:ViewModel() {
    fun store(
        celularDAO: CelularDAO,
        contatoCelular: Long,
        contatoId:Int)
    {
        var celularModel = CelularModel(contatoCelular,contatoId)
        celularDAO.store(celularModel)
    }

    fun update(
        celularDAO: CelularDAO,
        contatoCelular: Long,
        celularId:Int,
        contatoId:Int)
    {
        var celularModel = CelularModel(contatoCelular,contatoId,celularId)
        celularDAO.update(celularModel)
    }

    fun delete(
        celularDAO: CelularDAO,
        contatoCelular: Long,
        celularId:Int,
        contatoId:Int)
    {
        var celularModel = CelularModel(contatoCelular,contatoId,celularId)
        celularDAO.delete(celularModel)

    }
}