package br.pro.nigri.assessmentandroid.Model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.io.Serializable
import java.math.BigInteger

@Entity
class ContatoModel (
    var Nome:String,
    @PrimaryKey var contatoId: Int? = null

){
    override fun toString(): String {
        return "$Nome"
    }
}
