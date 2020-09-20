package br.pro.nigri.assessmentandroid.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.pro.nigri.assessmentandroid.Model.ContatoModel

class ListContatoViewModel:ViewModel() {

    val contatos : MutableLiveData<List<ContatoModel>> by lazy {
        MutableLiveData<List<ContatoModel>>()
    }
}