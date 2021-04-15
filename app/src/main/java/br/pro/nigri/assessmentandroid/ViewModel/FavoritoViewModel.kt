package br.pro.nigri.assessmentandroid.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentId

class FavoritoViewModel(
    var contatoId:String?=null,
    var userId:String?=null,
    @DocumentId var id:String?=null
):ViewModel()