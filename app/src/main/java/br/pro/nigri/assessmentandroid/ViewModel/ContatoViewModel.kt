package br.pro.nigri.assessmentandroid.ViewModel

import androidx.lifecycle.ViewModel
import br.pro.nigri.assessmentandroid.Model.ContatoModel
import com.google.firebase.firestore.DocumentId

class ContatoViewModel(
    var Nome:String?=null,
    var celular:Long?=null,
    var userId:String?=null,
    @DocumentId var id:String?=null
):ViewModel()

