package br.pro.nigri.assessmentandroid.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.pro.nigri.assessmentandroid.Model.ContatoModel
import br.pro.nigri.assessmentandroid.Model.Usuario
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class ContatoCreateEditViewModel:ViewModel() {

    var firebaseFirestore = FirebaseFirestore.getInstance()
    var collection = firebaseFirestore.collection("contato")
    var listaFavoritos = MutableLiveData<List<ContatoViewModel>>()
    var contatosFavoritos: MutableList<ContatoViewModel>? = ArrayList()
    var detailsContato = MutableLiveData<ContatoViewModel>()
    var collectionFavorito = firebaseFirestore.collection("favorito")
    var firebaseAuth = FirebaseAuth.getInstance()
    var user = firebaseAuth.currentUser;

    fun createContato(nomeContato:String, celular:Long): Task<Void> {

        var document = collection.document()


        var taskFireStore = document.set(
            mapOf(
                "nome" to nomeContato,
                "celular" to celular,
                "userId" to user!!.uid
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
        task.addOnSuccessListener {
            var contato = it.toObject(ContatoViewModel::class.java)
            detailsContato.value = contato
        }

        return task
    }

    fun converterFavToContact(favoritos:List<FavoritoViewModel>){
        contatosFavoritos!!.clear()
        favoritos.forEach {
            var result = getContatoById(it.contatoId!!)
            result.addOnSuccessListener {
                var contato = it.toObject(ContatoViewModel::class.java)
                contatosFavoritos!!.add(contato!!)
                listaFavoritos.value = contatosFavoritos
            }

        }
    }

    fun deleteContato(id:String):Task<Void>{
        var document = collection.document(id)
        var task = document.delete()

        return task


    }

    fun getFavoritos(id:String):Task<QuerySnapshot>{

        var result = collectionFavorito.whereEqualTo("contatoId", id).whereEqualTo("userId",user!!.uid).get()

        return result
    }

    fun deleteFavoritos(favoritoViewModel:FavoritoViewModel):Task<Void>{
        var result = collectionFavorito.document(favoritoViewModel.id!!).delete()

        return result
    }



}