package br.pro.nigri.assessmentandroid.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.pro.nigri.assessmentandroid.R
import br.pro.nigri.assessmentandroid.ViewModel.ApiViewModel
import br.pro.nigri.assessmentandroid.ViewModel.ContatoCreateEditViewModel
import br.pro.nigri.assessmentandroid.ViewModel.LoginViewModel
import br.pro.nigri.assessmentandroid.ViewModel.UsuarioCRUDViewModel
import br.pro.nigri.assessmentandroid.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_create_contato.*

class createContatoFragment : Fragment() {

    private lateinit var contatoCreateEditViewModel: ContatoCreateEditViewModel
    private lateinit var apiViewModel: ApiViewModel
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

        viewModelFactory = ViewModelFactory()
        activity?.let {
            contatoCreateEditViewModel =
                ViewModelProvider(it, viewModelFactory) // MainActivity
                    .get(ContatoCreateEditViewModel::class.java)

            apiViewModel =
                ViewModelProvider(it, viewModelFactory) // MainActivity
                    .get(ApiViewModel::class.java)
        }

        btnSalvarContato.setOnClickListener {

            if (txtNome.text.isNotEmpty() && txtNumber.text.isNotEmpty()){

                var result = contatoCreateEditViewModel.createContato(txtNome.text.toString(),txtNumber.text.toString().toLong())

                result.addOnSuccessListener {
                    Toast.makeText(requireContext(),"Contato Cadastrado com sucesso!",Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                }

                result.addOnFailureListener {
                    Toast.makeText(requireContext(),"Erro ao cadastrar contato: ${it.message}",Toast.LENGTH_LONG).show()
                }

            }
            else
            {
                Toast.makeText(requireContext(),"Todos os campos devem ser preenchidos.",Toast.LENGTH_LONG).show()
            }


        }



    }


}