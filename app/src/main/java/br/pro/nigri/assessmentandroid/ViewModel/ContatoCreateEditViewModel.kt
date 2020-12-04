package br.pro.nigri.assessmentandroid.ViewModel

import androidx.lifecycle.ViewModel
import br.pro.nigri.assessmentandroid.Model.ContatoModel
import br.pro.nigri.assessmentandroid.Model.Usuario
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class ContatoCreateEditViewModel:ViewModel() {

    var firebaseFirestore = FirebaseFirestore.getInstance()
    var collection = firebaseFirestore.collection("contato")

    fun createContato(nomeContato:String, celular:Long): Task<Void> {

        var document = collection.document()

        var taskFireStore = document.set(
            mapOf(
                "nome" to nomeContato,
                "celular" to celular
            )
        )

        return taskFireStore
    }

    fun editContato(nomeContato:String, celular:Long, id:String): Task<Void>{

        var contato = ContatoModel(nomeContato,celular)

        var task = collection.document(id).set(contato)

        return task
    }

    fun getContatoById(id:String): Task<DocumentSnapshot> {
        var document = collection.document(id)
        var task = document.get()

        return task
    }
}