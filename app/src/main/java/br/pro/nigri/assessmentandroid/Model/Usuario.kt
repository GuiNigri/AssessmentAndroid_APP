package br.pro.nigri.assessmentandroid.Model

import com.google.firebase.firestore.DocumentId

class Usuario  (
    var nome:String? = null,
    @DocumentId var id: String? = null
)