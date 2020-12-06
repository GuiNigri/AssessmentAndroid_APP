@file:Suppress("DEPRECATION")

package br.pro.nigri.assessmentandroid.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.pro.nigri.assessmentandroid.Adapter.ContatoAdapter
import br.pro.nigri.assessmentandroid.R
import br.pro.nigri.assessmentandroid.ViewModel.ContatoCreateEditViewModel
import br.pro.nigri.assessmentandroid.ViewModel.ContatoViewModel
import br.pro.nigri.assessmentandroid.ViewModel.ListContatoViewModel
import br.pro.nigri.assessmentandroid.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_list_contatos.*


class ListContatosFragment : Fragment() {

    private lateinit var listContatoViewModel: ListContatoViewModel
    private lateinit var contatoCreateEditViewModel: ContatoCreateEditViewModel
    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_contatos, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            configurarRecyclerView()
            popular()
        }

    private fun configurarRecyclerView() {
        contatos_list.layoutManager =
            LinearLayoutManager(activity)
        contatos_list.adapter = ContatoAdapter(){

            viewModelFactory = ViewModelFactory()
            activity?.let {
                contatoCreateEditViewModel =
                    ViewModelProvider(it, viewModelFactory) // MainActivity
                        .get(ContatoCreateEditViewModel::class.java)
            }

            contatoCreateEditViewModel.getContatoById(it)

            findNavController().navigate(R.id.contatoDetailsFragment)

        }
    }

    private fun popular(){

        viewModelFactory = ViewModelFactory()
        activity?.let {
            listContatoViewModel =
                ViewModelProvider(it, viewModelFactory) // MainActivity
                    .get(ListContatoViewModel::class.java)
        }

        listContatoViewModel.getContatos()

        listContatoViewModel.listaContatos.observe(viewLifecycleOwner, Observer {lista->
            if (lista != null){
                // recupera o adapter da RecyclerView
                val adapter = contatos_list.adapter

                if (adapter is ContatoAdapter){
                    adapter.atualizarDados(lista)

                }
            }


         })


        }

    }


