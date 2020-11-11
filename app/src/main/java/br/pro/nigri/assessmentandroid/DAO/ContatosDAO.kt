package br.pro.nigri.assessmentandroid.DAO

import androidx.room.*
import br.pro.nigri.assessmentandroid.Model.ContatoModel

@Dao
interface ContatosDAO {
    @Query("SELECT * FROM contatomodel")
    fun all(): Array<ContatoModel>
    @Query("SELECT * FROM contatomodel WHERE contatoId = :identificador")
    fun show(identificador: Int): Array<ContatoModel>
    @Transaction
    @Insert fun store(contatoModel: ContatoModel): Long
    @Update fun update(contatoModel: ContatoModel)
    @Query("DELETE FROM contatomodel WHERE contatoId = :identificador")
   fun delete(identificador: Int)
}