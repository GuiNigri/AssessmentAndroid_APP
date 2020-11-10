package br.pro.nigri.assessmentandroid.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.math.BigInteger

@Entity
class CelularModel (
    val celular:Long,
    val contatoUserId: Int,
    @PrimaryKey val celularId:Int? = null
)
{
    override fun toString(): String {
        return "$celular"
    }
}
