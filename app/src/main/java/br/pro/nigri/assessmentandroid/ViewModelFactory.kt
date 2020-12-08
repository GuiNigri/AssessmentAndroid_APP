package br.pro.nigri.assessmentandroid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.pro.nigri.assessmentandroid.ViewModel.*

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
        if (modelClass.isAssignableFrom(ContatoCreateEditViewModel::class.java)){
            return ContatoCreateEditViewModel() as T
        }
        if (modelClass.isAssignableFrom(ContatoViewModel::class.java)){
            return ContatoViewModel() as T
        }
        if (modelClass.isAssignableFrom(ListFavoritosViewModel::class.java)){
            return ListFavoritosViewModel() as T
        }
        if (modelClass.isAssignableFrom(ApiViewModel::class.java)){
            return ApiViewModel() as T
        }


        throw IllegalArgumentException("Classe ViewModel Desconhecida")
    }
}