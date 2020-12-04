package br.pro.nigri.assessmentandroid.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.pro.nigri.assessmentandroid.Adapter.ContatoAdapter
import br.pro.nigri.assessmentandroid.R
import br.pro.nigri.assessmentandroid.ViewModel.ContatoViewModel
import br.pro.nigri.assessmentandroid.ViewModel.ListContatoViewModel
import br.pro.nigri.assessmentandroid.ViewModel.ListFavoritosViewModel
import br.pro.nigri.assessmentandroid.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_list_contatos.*
import kotlinx.android.synthetic.main.fragment_list_favoritos.*

class ListFavoritosFragment : Fragment() {

    private lateinit var listFavoritosViewModel: ListFavoritosViewModel
    private lateinit var contatoViewModel: ContatoViewModel
    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_favoritos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarRecyclerView()
        popular()
    }

    private fun configurarRecyclerView() {
        list_favoritos.layoutManager =
            LinearLayoutManager(activity)
        list_favoritos.adapter = ContatoAdapter(){ nome: String,celular:Long, id: String ->

            viewModelFactory = ViewModelFactory()
            activity?.let {
                contatoViewModel =
                    ViewModelProvider(it, viewModelFactory) // MainActivity
                        .get(ContatoViewModel::class.java)
            }

            contatoViewModel.Nome = nome
            contatoViewModel.celular = celular
            contatoViewModel.id = id

            findNavController().navigate(R.id.contatoDetailsFragment)
        }
    }

    private fun popular(){

        viewModelFactory = ViewModelFactory()
        activity?.let {
            listFavoritosViewModel =
                ViewModelProvider(it, viewModelFactory) // MainActivity
                    .get(ListFavoritosViewModel::class.java)
        }

        listFavoritosViewModel.getFavoritos()

        listFavoritosViewModel.listaFavoritos.observe(viewLifecycleOwner, Observer {lista->
            if (lista != null){
                // recupera o adapter da RecyclerView
                val adapter = list_favoritos.adapter

                if (adapter is ContatoAdapter){
                    adapter.atualizarDados(lista)

                }
            }


        })


    }

}