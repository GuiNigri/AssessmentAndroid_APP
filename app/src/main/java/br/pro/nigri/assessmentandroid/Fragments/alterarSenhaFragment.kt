package br.pro.nigri.assessmentandroid.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.pro.nigri.assessmentandroid.R
import br.pro.nigri.assessmentandroid.ViewModel.UsuarioCRUDViewModel
import br.pro.nigri.assessmentandroid.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_alterar_senha.*


class alterarSenhaFragment : Fragment() {

    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var usuarioCRUDViewModel: UsuarioCRUDViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alterar_senha, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFactory = ViewModelFactory()
        activity?.let {
            usuarioCRUDViewModel =
                ViewModelProvider(it, viewModelFactory) // MainActivity
                    .get(UsuarioCRUDViewModel::class.java)
        }

        btnAlterarSenha.setOnClickListener {
            var senha = txtNovaSenha.text.toString()
            var reSenha = txtReNovaSenha.text.toString()

            if (senha.compareTo(reSenha) == 0)
            {
                var result = usuarioCRUDViewModel.updatePasswordUserAuth(senha)

                result.addOnSuccessListener {
                    Toast.makeText(requireContext(),"Senha alterada com sucesso!", Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                }
                result.addOnFailureListener {
                    Toast.makeText(requireContext(),"Erro ao alterar senha", Toast.LENGTH_LONG).show()
                }
                result.addOnCanceledListener {
                    Toast.makeText(requireContext(),"cancelado", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(requireContext(),"As senhas não conferem", Toast.LENGTH_LONG).show()
            }
        }
    }

}