package br.pro.nigri.assessmentandroid.ViewModel

import androidx.lifecycle.ViewModel
import br.pro.nigri.assessmentandroid.DAO.CelularDAO
import br.pro.nigri.assessmentandroid.DAO.ContatosDAO
import br.pro.nigri.assessmentandroid.Model.CelularModel
import br.pro.nigri.assessmentandroid.Model.ContatoModel
import java.math.BigInteger

class ContatoCreateEditViewModel:ViewModel() {
    fun store(
        contatosDAO: ContatosDAO,
        celularDAO: CelularDAO,
        contatoNome: String,
    contatoCelular: Long)
    {
        var contato = ContatoModel(contatoNome)
        var id = contatosDAO.store(contato).toInt()

        var celularModel = CelularModel(contatoCelular,id)
        celularDAO.store(celularModel)
    }

    fun update(
        contatosDAO: ContatosDAO,
        contatoNome: String,
        contatoId:Int){
        var contato = ContatoModel(contatoNome,contatoId)
        contatosDAO.update(contato)
    }

    fun delete(
        contatosDAO: ContatosDAO,
        contatoNome: String,
        contatoId:Int){
        var contato = ContatoModel(contatoNome,contatoId)
        contatosDAO.delete(contato)
    }
}