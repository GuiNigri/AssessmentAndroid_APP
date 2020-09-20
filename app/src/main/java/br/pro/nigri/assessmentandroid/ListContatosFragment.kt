package br.pro.nigri.assessmentandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.pro.nigri.assessmentandroid.Adapter.ContatoAdapter
import br.pro.nigri.assessmentandroid.ViewModel.ListContatoViewModel
import kotlinx.android.synthetic.main.fragment_count_contatos.*
import kotlinx.android.synthetic.main.fragment_list_contatos.*

class ListContatosFragment : Fragment() {

    private lateinit var listContatoViewModel: ListContatoViewModel
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
        btnCreateContato.setOnClickListener{
            findNavController().navigate(R.id.createContatoFragment)
        }

    }

    private fun configurarRecyclerView() {
        contatos_list.layoutManager =
            LinearLayoutManager(activity)
        contatos_list.adapter = ContatoAdapter()
    }

    private fun popular(){
        viewModelFactory = ViewModelFactory()
        activity?.let {
            listContatoViewModel =
                ViewModelProvider(it, viewModelFactory) // MainActivity
                    .get(ListContatoViewModel::class.java)
        }

        listContatoViewModel.contatos.observe(viewLifecycleOwner, Observer {lista-> if (lista != null){
        }
            if (lista != null){
                // recupera o adapter da RecyclerView
                val adapter = contatos_list.adapter

                if (adapter is ContatoAdapter){
                    adapter.atualizarDados(lista)

                    txtCountContatos.text = "Total de contatos: ${adapter.itemCount}"
                }
            }
        })
    }
}