package br.pro.nigri.assessmentandroid.Model

import com.google.firebase.firestore.DocumentId


class ContatoModel (
    var Nome:String?=null,
    var celular:Long?=null,
    @DocumentId var id: String? = null

)
