package br.pro.nigri.assessmentandroid.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.pro.nigri.assessmentandroid.ApplicationActivity
import br.pro.nigri.assessmentandroid.R
import br.pro.nigri.assessmentandroid.ViewModel.LoginViewModel
import br.pro.nigri.assessmentandroid.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        txtBtnCadastrar.setOnClickListener{
            findNavController().navigate(R.id.cadastroFragment)
        }

        btnAcessarLogin.setOnClickListener {

            var email = txtLogin.text.toString()
            var senha = txtSenha.text.toString()

            if (email.isNotEmpty() && senha.isNotEmpty()){
                progressBarLogin.visibility = View.VISIBLE
                viewModelFactory = ViewModelFactory()
                activity?.let {
                    viewModel =
                        ViewModelProvider(it, viewModelFactory) // MainActivity
                            .get(LoginViewModel::class.java)
                }

                var result = viewModel.logar(email, senha)

                result.addOnSuccessListener {
                    startActivity(
                        Intent(activity, ApplicationActivity::class.java)
                    )
                    progressBarLogin.visibility = View.GONE
                }
                result.addOnFailureListener {
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                    progressBarLogin.visibility = View.GONE
                }
            }else{
                Toast.makeText(
                    requireContext(),
                    "Todos os campos devem ser preenchidos",
                    Toast.LENGTH_LONG
                ).show()
            }

        }

        txtBtnEsqueciSenha.setOnClickListener {
            findNavController().navigate(R.id.redefinirSenhaFragment)
        }



    }
}