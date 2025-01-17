@file:Suppress("DEPRECATION")

package br.pro.nigri.assessmentandroid.Fragments

import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.pro.nigri.assessmentandroid.R
import br.pro.nigri.assessmentandroid.ViewModel.*
import br.pro.nigri.assessmentandroid.ViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_contato_details.*

class ContatoDetailsFragment : Fragment() {

    private lateinit var contatoCreateEditViewModel: ContatoCreateEditViewModel
    private lateinit var apiViewModel: ApiViewModel
    private lateinit var listFavoritosViewModel: ListFavoritosViewModel
    private lateinit var contatoViewModel: ContatoViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    var idContato:String?=null
    private var idFavorito:String?=null
    private var statusFavorito:Boolean?=null

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

            contatoViewModel =
                ViewModelProvider(it, viewModelFactory) // MainActivity
                    .get(ContatoViewModel::class.java)

            apiViewModel =
                ViewModelProvider(it, viewModelFactory) // MainActivity
                    .get(ApiViewModel::class.java)
        }


        contatoCreateEditViewModel.detailsContato.observe(viewLifecycleOwner, Observer {

            progressBarDetails.visibility = View.VISIBLE
            apiViewModel.chamarApi(it.celular.toString(),requireContext())

            apiViewModel.detailsNumber.observe(viewLifecycleOwner, Observer { api ->
                if (api.valid!!){
                    txtOperadora.text = "Operadora: ${api.carrier}"
                    txtPais.text = "País: ${api.country_name}"
                    txtCidade.text = "Local: ${api.location}"
                }
                else{
                    txtOperadora.text = "Operadora: Número Inválido"
                    txtPais.text = "País: Número Inválido"
                    txtCidade.text = "Local: Número Inválido"
                }


                txtOperadora.visibility = View.VISIBLE
                txtPais.visibility = View.VISIBLE
                txtCidade.visibility = View.VISIBLE
                progressBarDetails.visibility = View.GONE
            })

            idContato = it.id
            verificarFavoritos()
            txt_nome_editar.setText(it.Nome)
            txtCelularEditar.setText(it.celular.toString())

            btnAddFav.setOnClickListener {

                if (statusFavorito!!){
                    removeFavorito()
                }
                else
                {
                    addFavorito()
                }
            }

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

            btnExcluirContato.setOnClickListener { action ->
                var result = contatoCreateEditViewModel.deleteContato(it.id!!)

                result.addOnSuccessListener { action ->
                    var result = contatoCreateEditViewModel.getFavoritos(it.id!!)


                        result.addOnSuccessListener { querySnapshot ->
                            if (!result.result!!.isEmpty) {
                                var favoritos =
                                    querySnapshot.documents[0].toObject(FavoritoViewModel::class.java)

                                var result = contatoCreateEditViewModel.deleteFavoritos(favoritos!!)
                                result.addOnSuccessListener {
                                    Toast.makeText(
                                        requireContext(),
                                        "Contato Excluido com sucesso!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    findNavController().popBackStack()
                                }
                            }
                            result.addOnFailureListener {
                                Toast.makeText(requireContext(),"Erro ao excluir contato!", Toast.LENGTH_LONG).show()
                            }
                        }

                }

            }
        })
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
            }else{
                statusFavorito = false
            }

        }

    }

    private fun addFavorito(){


        var result = listFavoritosViewModel.addFavoritos(idContato!!)
        result.addOnSuccessListener {
            Toast.makeText(requireContext(),"Contato adicionado aos favoritos com sucesso!",Toast.LENGTH_LONG).show()
            verificarFavoritos()
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
            verificarFavoritos()
        }
        result.addOnFailureListener {
            Toast.makeText(requireContext(),"Erro ao remover contato dos favoritos!.",Toast.LENGTH_LONG).show()
        }
    }



}