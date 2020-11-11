package br.pro.nigri.assessmentandroid.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.pro.nigri.assessmentandroid.Model.ContatoModel
import br.pro.nigri.assessmentandroid.R
import br.pro.nigri.assessmentandroid.Room.AppDatabase
import br.pro.nigri.assessmentandroid.ViewModel.ContatoCreateEditViewModel
import br.pro.nigri.assessmentandroid.ViewModel.ListContatoViewModel
import br.pro.nigri.assessmentandroid.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_create_contato.*

class createContatoFragment : Fragment() {

    private lateinit var viewModel: ContatoCreateEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_contato, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ContatoCreateEditViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CadastrarContato()
    }

    private fun CadastrarContato(){
        btnSalvarContato.setOnClickListener{

            if (txtNome.text.isEmpty() || txtNumber.text.toString().isEmpty()){
                Toast.makeText(requireContext(),"Todos os campos são obrigatorios", Toast.LENGTH_LONG).show()
            }
            else
            {
                val nomeContatoCadastrar = txtNome.text.toString()
                val celularContato = txtNumber.text.toString().toLong()

                var db = AppDatabase.getInstance(requireContext().applicationContext)
                viewModel.store(db.contatosDAO(),db.celularDAO(), nomeContatoCadastrar,celularContato)
                findNavController().navigate(R.id.listContatosFragment)
            }

        }
    }

}