package br.pro.nigri.assessmentandroid.ViewModel

import androidx.lifecycle.ViewModel
import br.pro.nigri.assessmentandroid.Model.Usuario
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class UsuarioCRUDViewModel: ViewModel() {

    var firebaseFirestore = FirebaseFirestore.getInstance()
    var collection = firebaseFirestore.collection("usuario")

    fun createAuthUsuario(email: String, senha:String): Task<AuthResult> {

        var firebaseAuth = FirebaseAuth.getInstance()
        var task = firebaseAuth
            .createUserWithEmailAndPassword(email, senha)



        return task
    }

    fun createInfoUsuario(nomeUsuario:String,provedor:String,id: String): Task<Void> {

        var document = collection.document(id)

        var taskFireStore = document.set(
            mapOf(
                "nome" to nomeUsuario,
                "provedor" to provedor
            )
        )

        return taskFireStore
    }

    fun updateEmailUserAuth(email: String): Task<Void> {
        var firebaseAuth = FirebaseAuth.getInstance()
        var user = firebaseAuth.currentUser;
        var task = user!!.updateEmail(email)

        return task
    }

    fun updatePasswordUserAuth(senha: String): Task<Void> {
        var firebaseAuth = FirebaseAuth.getInstance()
        var user = firebaseAuth.currentUser;
        var task = user!!.updatePassword(senha)

        return task
    }

    fun updateUserInfo(nome: String): Task<Void> {
        var usuario = Usuario(nome)

        var task = collection.document(FirebaseAuth.getInstance().currentUser!!.uid).set(usuario)

        return task

    }

    fun redefinirSenha(email: String): Task<Void> {

        var firebaseAuth = FirebaseAuth.getInstance()
        var task = firebaseAuth.sendPasswordResetEmail(email)

        return task

    }

    fun getUsuario(id:String): Task<DocumentSnapshot> {

        var document = collection.document(id)
        var task = document.get()

        return task

    }
}