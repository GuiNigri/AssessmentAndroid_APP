package br.pro.nigri.assessmentandroid.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.pro.nigri.assessmentandroid.ViewModelFactory
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ListFavoritosViewModel:ViewModel() {

    var firebaseFirestore = FirebaseFirestore.getInstance()
    var collection = firebaseFirestore.collection("favorito")
    var firebaseAuth = FirebaseAuth.getInstance()
    var user = firebaseAuth.currentUser;


    fun getFavoritos(actionClick: (QuerySnapshot) -> Unit) {
        var firebaseAuth = FirebaseAuth.getInstance()
        var user = firebaseAuth.currentUser;

        var task = collection.whereEqualTo("userId",user!!.uid).get()
        task.addOnSuccessListener {
            actionClick(it)
        }


    }

    fun addFavoritos(contatoId:String): Task<Void> {

        var document = collection.document()

        var taskFireStore = document.set(
            mapOf(
                "contatoId" to contatoId,
                "userId" to user!!.uid
            )
        )

        return taskFireStore

    }

    fun removeFavorito(idFavorito:String): Task<Void> {

        var document = collection.document(idFavorito)

        var taskFireStore = document.delete()

        return taskFireStore

    }

    fun checkFav(idContato:String, actionClick: (QuerySnapshot) -> Unit){
        var task = collection.whereEqualTo("contatoId", idContato).whereEqualTo("userId",user!!.uid).get()
        task.addOnSuccessListener {
            actionClick(it)
        }

    }


}