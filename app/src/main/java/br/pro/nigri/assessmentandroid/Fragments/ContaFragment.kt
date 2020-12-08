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
import br.pro.nigri.assessmentandroid.MainActivity
import br.pro.nigri.assessmentandroid.Model.Usuario
import br.pro.nigri.assessmentandroid.R
import br.pro.nigri.assessmentandroid.ViewModel.UsuarioCRUDViewModel
import br.pro.nigri.assessmentandroid.ViewModelFactory
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_conta.*


class ContaFragment : Fragment() {

    private var firebaseUser: FirebaseUser? = null
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var usuarioCRUDViewModel: UsuarioCRUDViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModelFactory = ViewModelFactory()
        activity?.let {
            usuarioCRUDViewModel =
                ViewModelProvider(it, viewModelFactory) // MainActivity
                    .get(UsuarioCRUDViewModel::class.java)
        }

        var firebaseAuth = FirebaseAuth.getInstance()

        firebaseUser = firebaseAuth.currentUser

        if (firebaseUser != null) {
            var result = usuarioCRUDViewModel.getUsuario(firebaseUser!!.uid)

            result.addOnSuccessListener {
                var user = it.toObject(Usuario::class.java)
                txtNomeConta.setText(user!!.nome)
                txtEmailConta.setText(firebaseUser!!.email)

            }
            result.addOnFailureListener {
                Toast.makeText(context,"Erro ao recuperar informações do usuario", Toast.LENGTH_LONG).show()
            }


        }

        btnEditarConta.setOnClickListener {
            var email = txtEmailConta.text.toString()
            var nomeUsuario = txtNomeConta.text.toString()

            if (email != firebaseUser!!.email){
                var result = usuarioCRUDViewModel.updateEmailUserAuth(email)

                result.addOnSuccessListener {
                    txtEmailConta.setText(email)
                    Toast.makeText(requireContext(),"Email alterado com sucesso!", Toast.LENGTH_LONG).show()
                }
                result.addOnFailureListener{
                    Toast.makeText(requireContext(),"Erro ao alterar Email do usuario", Toast.LENGTH_LONG).show()
                }
            }

            var result = usuarioCRUDViewModel.updateUserInfo(nomeUsuario)

            result.addOnSuccessListener {
                txtNomeConta.setText(nomeUsuario)
                Toast.makeText(requireContext(),"Nome alterado com sucesso!", Toast.LENGTH_LONG).show()
            }
            result.addOnFailureListener{
                Toast.makeText(requireContext(),"Erro ao alterar nome do usuario", Toast.LENGTH_LONG).show()
            }

        }

        txtBtnAlterarSenha.setOnClickListener {
            findNavController().navigate(R.id.alterarSenhaFragment)
        }
        btnDeslogarConta.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(
                Intent(requireContext(), MainActivity::class.java)
            )
        }
    }


}