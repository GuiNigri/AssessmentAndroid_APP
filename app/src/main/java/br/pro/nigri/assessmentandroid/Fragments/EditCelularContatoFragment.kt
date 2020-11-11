package br.pro.nigri.assessmentandroid.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.pro.nigri.assessmentandroid.Model.CelularModel
import br.pro.nigri.assessmentandroid.R
import br.pro.nigri.assessmentandroid.Room.AppDatabase
import br.pro.nigri.assessmentandroid.Room.RoomDatabase
import br.pro.nigri.assessmentandroid.ViewModel.CelularCreateEditViewModel
import br.pro.nigri.assessmentandroid.ViewModel.CelularViewModel
import br.pro.nigri.assessmentandroid.ViewModel.ContatoCreateEditViewModel
import kotlinx.android.synthetic.main.fragment_edit_celular_contato.*

class EditCelularContatoFragment : Fragment() {

    private lateinit var celularEditViewModel: CelularCreateEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_celular_contato, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        celularEditViewModel = ViewModelProviders.of(this).get(CelularCreateEditViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var celularViewModel : CelularViewModel? = null
        activity?.let {
            celularViewModel = ViewModelProviders.of(it)
                .get(CelularViewModel::class.java)
        }

        var celularModel = celularViewModel!!.celular

        txtEditCelular.setText(celularModel!!.celular.toString())

        var db = AppDatabase.getInstance(requireContext().applicationContext)

        ExcluirCelular(db,celularModel)
        EditarCelular(db,celularModel)
    }

    private fun ExcluirCelular(db: RoomDatabase, celularModel:CelularModel){
        btnExcluirCelular.setOnClickListener {
            celularEditViewModel.delete(db.celularDAO(),celularModel.celularId!!)
            findNavController().navigate(R.id.contatoDetailsFragment)
        }
    }

    private fun EditarCelular(db: RoomDatabase, celularModel:CelularModel){
        btnSalvarEditarCelular.setOnClickListener{

            var celularAtualizado = txtEditCelular.text.toString().toLong()

            celularEditViewModel.update(db.celularDAO(),celularAtualizado,celularModel.celularId!!,celularModel.contatoUserId)

            findNavController().navigate(R.id.contatoDetailsFragment)
        }
    }

}