package br.pro.nigri.assessmentandroid.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.pro.nigri.assessmentandroid.Model.CelularModel
import br.pro.nigri.assessmentandroid.R
import br.pro.nigri.assessmentandroid.Room.AppDatabase
import br.pro.nigri.assessmentandroid.ViewModel.CelularCreateEditViewModel
import br.pro.nigri.assessmentandroid.ViewModel.CelularViewModel
import br.pro.nigri.assessmentandroid.ViewModel.ContatoCreateEditViewModel
import br.pro.nigri.assessmentandroid.ViewModel.ContatoViewModel
import kotlinx.android.synthetic.main.fragment_contato_details.*
import kotlinx.android.synthetic.main.fragment_create_celular_contato.*
import kotlinx.android.synthetic.main.fragment_create_contato.*

class CreateCelularContatoFragment : Fragment() {

    private lateinit var viewModel: CelularCreateEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_celular_contato, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CelularCreateEditViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var celularViewModel : CelularViewModel? = null
        activity?.let {
            celularViewModel = ViewModelProviders.of(it)
                .get(CelularViewModel::class.java)
        }

        var celularModel = celularViewModel!!.celular!!

        btnSalvarCelular.setOnClickListener {
            if (txtCelularAdd.text.isEmpty()){
                Toast.makeText(requireContext(),"Todos os campos são obrigatorios", Toast.LENGTH_LONG).show()
            }
            else
            {

                val celularContato = txtCelularAdd.text.toString().toLong()

                var db = AppDatabase.getInstance(requireContext().applicationContext)
                viewModel.store(db.celularDAO(),celularContato,celularModel.contatoUserId)
                findNavController().navigate(R.id.listContatosFragment)
            }
        }
    }

}