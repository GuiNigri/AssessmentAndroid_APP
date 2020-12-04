package br.pro.nigri.assessmentandroid.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class ListContatoViewModel:ViewModel() {

    var firebaseFirestore = FirebaseFirestore.getInstance()
    var collection = firebaseFirestore.collection("contato")
    var listaContatos = MutableLiveData<List<ContatoViewModel>>()


    fun getContatos() {
        var task = collection.get()
        task.addOnSuccessListener {
            var contato = it.toObjects(ContatoViewModel::class.java)
            listaContatos.value = contato
        }
        task.addOnFailureListener {

        }

    }





}