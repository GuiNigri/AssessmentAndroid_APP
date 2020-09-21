package br.pro.nigri.assessmentandroid.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.pro.nigri.assessmentandroid.Model.ContatoModel
import br.pro.nigri.assessmentandroid.R
import br.pro.nigri.assessmentandroid.ViewModel.ListContatoViewModel
import br.pro.nigri.assessmentandroid.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_create_contato.*

class createContatoFragment : Fragment() {

    private lateinit var listContatoViewModel: ListContatoViewModel
    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_contato, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSalvarContato.setOnClickListener{



            if (txtNome.text.isEmpty() || txtNumber.text.toString().isEmpty()){
                Toast.makeText(requireContext(),"Todos os campos são obrigatorios", Toast.LENGTH_LONG).show()
            }
            else
            {
                val nomeContatoCadastrar = txtNome.text.toString()
                val celularContato = txtNumber.text.toString().toBigInteger()

                val contatoAdicionado = ContatoModel(nomeContatoCadastrar,celularContato)

                viewModelFactory =
                    ViewModelFactory()
                activity?.let {
                    listContatoViewModel =
                        ViewModelProvider(it, viewModelFactory)
                            .get(ListContatoViewModel::class.java)
                }

                    val lista = listContatoViewModel.contatos.value ?:
                    listOf()

                    listContatoViewModel.contatos.value  = lista + contatoAdicionado

                findNavController().navigate(R.id.listContatosFragment)
            }

        }
    }

}