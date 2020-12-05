package br.pro.nigri.assessmentandroid.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class ListContatoViewModel:ViewModel() {

    var firebaseFirestore = FirebaseFirestore.getInstance()
    var collection = firebaseFirestore.collection("contato")
    var listaContatos = MutableLiveData<List<ContatoViewModel>>()


    fun getContatos() {
        var firebaseAuth = FirebaseAuth.getInstance()
        var user = firebaseAuth.currentUser;

        var task = collection.whereEqualTo("userId",user!!.uid).get()
        task.addOnSuccessListener {
            var contato = it.toObjects(ContatoViewModel::class.java)
            listaContatos.value = contato
        }
        task.addOnFailureListener {

        }

    }





}