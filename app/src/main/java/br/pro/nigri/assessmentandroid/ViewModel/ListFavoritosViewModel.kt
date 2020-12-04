package br.pro.nigri.assessmentandroid.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class ListFavoritosViewModel:ViewModel() {

    var firebaseFirestore = FirebaseFirestore.getInstance()
    var collection = firebaseFirestore.collection("favorito")
    var listaFavoritos = MutableLiveData<List<ContatoViewModel>>()

    fun getFavoritos() {
        var task = collection.get()
        task.addOnSuccessListener {
            var contato = it.toObjects(ContatoViewModel::class.java)
            listaFavoritos.value = contato
        }
        task.addOnFailureListener {

        }

    }
}