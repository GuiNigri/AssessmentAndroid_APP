package br.pro.nigri.assessmentandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.pro.nigri.assessmentandroid.Model.ContatoModel
import kotlinx.android.synthetic.main.activity_details_contato.*

class DetailsContatoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_contato)

        val contatoDetailsView = intent.getSerializableExtra("contatoDetails") as ContatoModel

        txtNomeDetails.text = "Nome: ${contatoDetailsView.Nome}"
        txtCelularDetails.text = "Celular: ${contatoDetailsView.Celular}"
    }
}