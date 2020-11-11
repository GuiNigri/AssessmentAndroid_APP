@file:Suppress("DEPRECATION")

package br.pro.nigri.assessmentandroid.Fragments

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.pro.nigri.assessmentandroid.Model.ContatoModel
import br.pro.nigri.assessmentandroid.R
import br.pro.nigri.assessmentandroid.Room.AppDatabase
import br.pro.nigri.assessmentandroid.Room.RoomDatabase
import br.pro.nigri.assessmentandroid.ViewModel.ContatoViewModel
import br.pro.nigri.assessmentandroid.ViewModel.ListContatoViewModel
import br.pro.nigri.assessmentandroid.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_contato_details.*
import kotlinx.android.synthetic.main.fragment_list_contatos.*


class ListContatosFragment : Fragment() {

    private lateinit var listContatoViewModel: ListContatoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_contatos, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        popular()
        btnCreateContato.setOnClickListener{
            findNavController().navigate(R.id.createContatoFragment)
        }



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contatos_list.setOnItemClickListener{parent,view,i, id ->
            val selectedItem = parent.getItemAtPosition(i) as ContatoModel

            var contatoViewModel : ContatoViewModel? = null
            activity?.let {
                contatoViewModel = ViewModelProviders.of(it)
                    .get(ContatoViewModel::class.java)
            }

            contatoViewModel!!.contato = ContatoModel(selectedItem.Nome,selectedItem.contatoId)

            findNavController().navigate(R.id.contatoDetailsFragment)
        }

    }



    private fun popular(){


        listContatoViewModel = ViewModelProviders.of(this).get(ListContatoViewModel::class.java)

        val db = AppDatabase.getInstance(requireActivity().applicationContext)
        ContatoListaAsync().execute(db)

    }

    inner class ContatoListaAsync
        : AsyncTask<RoomDatabase, Unit, Array<ContatoModel>>() {

        override fun doInBackground // Segundo Plano
                    (vararg db: RoomDatabase?): Array<ContatoModel> {
            return listContatoViewModel.all(db[0]!!)
        }
        // Main Thread
        override fun onPostExecute(result: Array<ContatoModel>?) {
            super.onPostExecute(result)
            if (result != null)
                contatos_list.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    result
                )
            else
                Toast.makeText(
                    requireContext(),
                    "Consulta não realizada.",
                    Toast.LENGTH_LONG
                ).show()
        }
    }
}

