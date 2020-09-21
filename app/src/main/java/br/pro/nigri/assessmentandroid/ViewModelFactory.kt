package br.pro.nigri.assessmentandroid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.pro.nigri.assessmentandroid.ViewModel.ListContatoViewModel

class ViewModelFactory:ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T{

        if (modelClass.isAssignableFrom(ListContatoViewModel::class.java)){
            return ListContatoViewModel() as T
        }


        throw IllegalArgumentException("Classe ViewModel Desconhecida")
    }
}