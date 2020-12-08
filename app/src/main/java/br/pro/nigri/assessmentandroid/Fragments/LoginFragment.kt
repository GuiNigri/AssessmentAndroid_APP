package br.pro.nigri.assessmentandroid.Fragments

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.pro.nigri.assessmentandroid.ApplicationActivity
import br.pro.nigri.assessmentandroid.MainActivity
import br.pro.nigri.assessmentandroid.R
import br.pro.nigri.assessmentandroid.ViewModel.LoginViewModel
import br.pro.nigri.assessmentandroid.ViewModel.UsuarioCRUDViewModel
import br.pro.nigri.assessmentandroid.ViewModelFactory
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*;

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var usuarioCRUDViewModel: UsuarioCRUDViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var callbackManager:CallbackManager
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_login, container, false)
        // Inflate the layout for this fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var loginButton = requireActivity().findViewById<View>(R.id.login_button) as LoginButton

        callbackManager = CallbackManager.Factory.create()

        loginButton.fragment = this

        callbackManager = CallbackManager.Factory.create()

        auth = FirebaseAuth.getInstance()


        loginButton.setReadPermissions("email", "public_profile")

        loginButton.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult?) {
                    handleFacebookAccessToken(loginResult!!.accessToken)
                }

                override fun onCancel() {
                    Log.d(ContentValues.TAG, "facebook:onCancel")
                    Toast.makeText(
                        requireContext(), "Cancelado",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onError(error: FacebookException) {
                    Log.d(ContentValues.TAG, "facebook:onError", error)
                    Toast.makeText(
                        requireContext(), "Erro",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }



    private fun handleFacebookAccessToken(token: AccessToken) {

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    cadastrarUsuarioInfoFacebook()

                    Toast.makeText(
                        requireContext(), "Login efetuado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.w(ContentValues.TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        requireContext(), "Authentication failed. ${task.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null){
            val intent = Intent(requireContext(), ApplicationActivity::class.java)
            startActivity(intent)
        }

    }

    private fun cadastrarUsuarioInfoFacebook(){
        var user = auth.currentUser

        viewModelFactory = ViewModelFactory()
        usuarioCRUDViewModel =
            ViewModelProvider(this, viewModelFactory) // MainActivity
                .get(UsuarioCRUDViewModel::class.java)

        var task = usuarioCRUDViewModel.getUsuario(user!!.uid)
        task.addOnSuccessListener {
            if (!it.exists()){
                usuarioCRUDViewModel.createInfoUsuario(user!!.displayName!!,"facebook",user!!.uid)
            }
        }

        val intent = Intent(requireContext(), ApplicationActivity::class.java)
        startActivity(intent)
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

