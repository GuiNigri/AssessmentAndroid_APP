@file:Suppress("DEPRECATION")

package br.pro.nigri.assessmentandroid.Fragments

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.pro.nigri.assessmentandroid.Model.CelularModel
import br.pro.nigri.assessmentandroid.Model.ContatoModel
import br.pro.nigri.assessmentandroid.R
import br.pro.nigri.assessmentandroid.Room.AppDatabase
import br.pro.nigri.assessmentandroid.Room.RoomDatabase
import br.pro.nigri.assessmentandroid.ViewModel.*
import kotlinx.android.synthetic.main.fragment_contato_details.*
import kotlinx.android.synthetic.main.fragment_create_contato.*
import kotlinx.android.synthetic.main.fragment_list_contatos.*

class ContatoDetailsFragment : Fragment() {

    private lateinit var editarViewModel: ContatoCreateEditViewModel
    private lateinit var listCelularViewModel: ListCelularViewModel
    private var idUsuario: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contato_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        editarViewModel = ViewModelProviders.of(this).get(ContatoCreateEditViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var contatoViewModel: ContatoViewModel? = null
        activity?.let {
            contatoViewModel = ViewModelProviders.of(it)
                .get(ContatoViewModel::class.java)
        }

        var contato = contatoViewModel!!.contato

        txt_nome_editar.setText(contato!!.Nome)

        idUsuario = contato!!.contatoId!!


        var db = AppDatabase.getInstance(requireContext().applicationContext)

        popular()

        btnSalvarEditarContato.setOnClickListener{

            if (txt_nome_editar.text.isEmpty()){
                Toast.makeText(requireContext(),"Todos os campos são obrigatorios", Toast.LENGTH_LONG).show()
            }
            else
            {
                val nomeContatoEditar = txt_nome_editar.text.toString()
                editarViewModel.update(db.contatosDAO(),nomeContatoEditar,idUsuario)
                findNavController().navigate(R.id.listContatosFragment)
            }

        }

        btn_deletar_usuario.setOnClickListener{
            editarViewModel.delete(db.contatosDAO(),contato!!.Nome,idUsuario)
            findNavController().navigate(R.id.listContatosFragment)
        }

        btnAddCelular.setOnClickListener{
            var celularViewModel : CelularViewModel? = null
            activity?.let {
                celularViewModel = ViewModelProviders.of(it)
                    .get(CelularViewModel::class.java)
            }

            celularViewModel!!.celular = CelularModel(0,idUsuario)

            findNavController().navigate(R.id.createCelularContatoFragment)
        }
    }

    private fun popular(){


        listCelularViewModel = ViewModelProviders.of(this).get(ListCelularViewModel::class.java)

        val db = AppDatabase.getInstance(requireActivity().applicationContext)
        CelularListaAsync().execute(db)

    }

    inner class CelularListaAsync
        : AsyncTask<RoomDatabase, Unit, Array<CelularModel>>() {

        override fun doInBackground // Segundo Plano
                    (vararg db: RoomDatabase?): Array<CelularModel> {
            return listCelularViewModel.show(db[0]!!, idUsuario)
        }
        // Main Thread
        override fun onPostExecute(result: Array<CelularModel>?) {
            super.onPostExecute(result)
            if (result != null)
                list_numbers.adapter = ArrayAdapter(
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