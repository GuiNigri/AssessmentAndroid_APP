package br.pro.nigri.assessmentandroid.DAO

import androidx.room.*
import br.pro.nigri.assessmentandroid.Model.CelularModel

@Dao
interface CelularDAO {
    @Query("SELECT * FROM celularmodel")
    fun all(): Array<CelularModel>
    @Query("SELECT * FROM celularmodel WHERE celularId = :identificador")
    fun show(identificador: Int): Array<CelularModel>
    @Transaction
    @Insert
    fun store(celularmodel: CelularModel)
    @Update
    fun update(celularmodel: CelularModel)
    @Query("DELETE FROM celularmodel WHERE celularId = :identificador")
    fun delete(identificador: Int)
    @Transaction
    @Query("SELECT * FROM celularmodel WHERE contatoUserId = :identificador")
    fun getCelularById(identificador: Int): Array<CelularModel>
}