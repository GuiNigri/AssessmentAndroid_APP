package br.pro.nigri.assessmentandroid.Model

import androidx.room.Embedded
import androidx.room.Relation

data  class ContatoComCelulares(
    @Embedded val contato: ContatoModel,
    @Relation(
        parentColumn = "contatoId",
        entityColumn = "contatoUserId"
    )
    val contatos: List<CelularModel>
)