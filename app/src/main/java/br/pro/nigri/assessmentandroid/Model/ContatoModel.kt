package br.pro.nigri.assessmentandroid.Model

import com.google.firebase.firestore.DocumentId


class ContatoModel (
    var Nome:String?=null,
    var celular:Long?=null,
    var country:String?=null,
    var location:String?=null,
    var carrier:String?=null,
    @DocumentId var id: String? = null

)
