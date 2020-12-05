@file:Suppress("DEPRECATION")

package br.pro.nigri.assessmentandroid.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import br.pro.nigri.assessmentandroid.R
import br.pro.nigri.assessmentandroid.ViewModel.*
import br.pro.nigri.assessmentandroid.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_contato_details.*

class ContatoDetailsFragment : Fragment() {

    private lateinit var contatoCreateEditViewModel: ContatoCreateEditViewModel
    private lateinit var listFavoritosViewModel: ListFavoritosViewModel
    private lateinit var contatoViewModel: ContatoViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    var idContato:String?=null
    private var idFavorito:String?=null
    var statusFavorito:Boolean?=false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contato_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFactory = ViewModelFactory()
        activity?.let {
            contatoCreateEditViewModel =
                ViewModelProvider(it, viewModelFactory) // MainActivity
                    .get(ContatoCreateEditViewModel::class.java)
        }

        getInfoContato()

        btnSalvarEditar.setOnClickListener {
            if (txt_nome_editar.text.isNotEmpty() && txtCelularEditar.text.isNotEmpty()){
                var result = contatoCreateEditViewModel.editContato(txt_nome_editar.text.toString(),txtCelularEditar.text.toString().toLong(),idContato!!)

                result.addOnSuccessListener {
                    Toast.makeText(requireContext(),"Contato Atualizado com Sucesso!",Toast.LENGTH_LONG).show()
                }
                result.addOnFailureListener {
                    Toast.makeText(requireContext(),"Erro ao atualizar o contato: ${it.message}",Toast.LENGTH_LONG).show()
                }
            }

        }

        btnAddFav.setOnClickListener {
            verificarFavoritos()
            if (statusFavorito!!){
                removeFavorito()
            }
            else
            {
                addFavorito()
            }


        }

    }

    private fun getInfoContato(){
        viewModelFactory = ViewModelFactory()
        activity?.let {
            contatoViewModel =
                ViewModelProvider(it, viewModelFactory) // MainActivity
                    .get(ContatoViewModel::class.java)
        }

        idContato = contatoViewModel.id

        var result = contatoCreateEditViewModel.getContatoById(idContato!!)
        result.addOnSuccessListener {
            var contato = it.toObject(ContatoViewModel::class.java)
            txt_nome_editar.setText(contato!!.Nome)
            txtCelularEditar.setText(contato!!.celular.toString())
        }


    }

    private fun verificarFavoritos(){
        viewModelFactory = ViewModelFactory()
        activity?.let {
            listFavoritosViewModel =
                ViewModelProvider(it, viewModelFactory) // MainActivity
                    .get(ListFavoritosViewModel::class.java)
        }

        listFavoritosViewModel.checkFav(idContato!!){

            if (!it.isEmpty){
                var fav = it.documents[0].toObject(FavoritoViewModel::class.java)
                idFavorito = fav!!.id
                statusFavorito = true
            }

        }

    }

    private fun addFavorito(){


        var result = listFavoritosViewModel.addFavoritos(idContato!!)
        result.addOnSuccessListener {
            Toast.makeText(requireContext(),"Contato adicionado aos favoritos com sucesso!",Toast.LENGTH_LONG).show()
        }
        result.addOnFailureListener {
            Toast.makeText(requireContext(),"Erro ao adicionar contato aos favoritos.",Toast.LENGTH_LONG).show()
        }
    }

    private fun removeFavorito(){
        viewModelFactory = ViewModelFactory()
        activity?.let {
            listFavoritosViewModel =
                ViewModelProvider(it, viewModelFactory) // MainActivity
                    .get(ListFavoritosViewModel::class.java)
        }

        var result = listFavoritosViewModel.removeFavorito(idFavorito!!)
        result.addOnSuccessListener {
            Toast.makeText(requireContext(),"Contato removido dos favoritos com sucesso!",Toast.LENGTH_LONG).show()
        }
        result.addOnFailureListener {
            Toast.makeText(requireContext(),"Erro ao remover contato dos favoritos!.",Toast.LENGTH_LONG).show()
        }
    }


}