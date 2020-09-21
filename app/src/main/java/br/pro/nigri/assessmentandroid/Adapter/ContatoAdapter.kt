package br.pro.nigri.assessmentandroid.Adapter

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.pro.nigri.assessmentandroid.DetailsContatoActivity
import br.pro.nigri.assessmentandroid.MainActivity
import br.pro.nigri.assessmentandroid.Model.ContatoModel
import br.pro.nigri.assessmentandroid.R
import br.pro.nigri.assessmentandroid.ViewModel.ListContatoViewModel
import br.pro.nigri.assessmentandroid.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.contatos_card.view.*


class ContatoAdapter(var context: Context,var listaContatos:List<ContatoModel> = listOf()): RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder>(){

    private lateinit var viewModelFactory: ViewModelFactory

    class ContatoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val nomeText: TextView = itemView.findViewById(R.id.txtNomeContato)
        val editBtn: ImageButton = itemView.btnEditar
        val ligarBtn: ImageButton = itemView.btnLigar

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
        val card = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.contatos_card, parent, false)

        return ContatoViewHolder(card)
    }

    override fun getItemCount() = listaContatos.size


    override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {

        holder.nomeText.text = listaContatos[position].Nome

        val contato = listaContatos[position]

        holder.editBtn.setOnClickListener {
            val intentDetails = Intent(context,DetailsContatoActivity::class.java)

            val contatoDetails = ContatoModel(contato.Nome,contato.Celular)

            intentDetails.putExtra("contatoDetails", contatoDetails)

            context.startActivity(intentDetails)
        }

        holder.ligarBtn.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${contato.Celular}")
            }

            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        }

    }


    fun atualizarDados(contatos: List<ContatoModel>)
    {
        listaContatos = contatos

        notifyDataSetChanged()
    }
}