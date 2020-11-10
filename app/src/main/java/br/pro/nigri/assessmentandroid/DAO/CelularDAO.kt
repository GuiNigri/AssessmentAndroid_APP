package br.pro.nigri.assessmentandroid.DAO

import androidx.room.*
import br.pro.nigri.assessmentandroid.Model.CelularModel
import br.pro.nigri.assessmentandroid.Model.ContatoComCelulares
import br.pro.nigri.assessmentandroid.Model.ContatoModel

@Dao
interface CelularDAO {
    @Query("SELECT * FROM celularmodel")
    fun all(): Array<CelularModel>
    @Query("SELECT * FROM celularmodel WHERE celularId = :identificador")
    fun show(identificador: Int): Array<CelularModel>
    @Insert
    fun store(celularmodel: CelularModel)
    @Update
    fun update(celularmodel: CelularModel)
    @Delete
    fun delete(celularmodel: CelularModel)
}