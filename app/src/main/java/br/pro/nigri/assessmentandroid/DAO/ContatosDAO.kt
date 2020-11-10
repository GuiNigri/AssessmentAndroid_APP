package br.pro.nigri.assessmentandroid.DAO

import androidx.room.*
import br.pro.nigri.assessmentandroid.Model.ContatoComCelulares
import br.pro.nigri.assessmentandroid.Model.ContatoModel

@Dao
interface ContatosDAO {
    @Query("SELECT * FROM contatomodel")
    fun all(): Array<ContatoModel>
    @Query("SELECT * FROM contatomodel WHERE contatoId = :identificador")
    fun show(identificador: Int): Array<ContatoModel>
    @Transaction
    @Query("SELECT * FROM contatomodel")
    fun getContatoComCelulares(): List<ContatoComCelulares>
    @Insert fun store(contatoModel: ContatoModel): Long
    @Update fun update(contatoModel: ContatoModel)
    @Delete fun delete(contatoModel: ContatoModel)
}