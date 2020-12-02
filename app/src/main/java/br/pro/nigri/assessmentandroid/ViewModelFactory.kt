package br.pro.nigri.assessmentandroid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.pro.nigri.assessmentandroid.ViewModel.ListContatoViewModel
import br.pro.nigri.assessmentandroid.ViewModel.LoginViewModel
import br.pro.nigri.assessmentandroid.ViewModel.UsuarioCRUDViewModel

class ViewModelFactory:ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T{

        if (modelClass.isAssignableFrom(ListContatoViewModel::class.java)){
            return ListContatoViewModel() as T
        }

        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel() as T
        }

        if (modelClass.isAssignableFrom(UsuarioCRUDViewModel::class.java)){
            return UsuarioCRUDViewModel() as T
        }


        throw IllegalArgumentException("Classe ViewModel Desconhecida")
    }
}