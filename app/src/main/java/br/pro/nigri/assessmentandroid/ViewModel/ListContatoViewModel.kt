package br.pro.nigri.assessmentandroid.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.pro.nigri.assessmentandroid.Model.ContatoModel
import java.math.BigInteger

class ListContatoViewModel:ViewModel() {

    val contatos : MutableLiveData<List<ContatoModel>> by lazy {
        MutableLiveData<List<ContatoModel>>()
    }

}