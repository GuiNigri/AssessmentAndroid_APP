package br.pro.nigri.assessmentandroid.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import br.pro.nigri.assessmentandroid.R
import br.pro.nigri.assessmentandroid.ViewModel.ContatoViewModel

class ContatoAdapter(
    var listaContatos: List<ContatoViewModel> = listOf(),
    val actionClick: (String) -> Unit
): RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder>() {

    class ContatoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val nomeContato: TextView = itemView.findViewById(R.id.txtNomeContato)
        val cardContato:ConstraintLayout = itemView.findViewById(R.id.contatoCard)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
        val card = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.contato_card, parent, false)

        return ContatoViewHolder(card)
    }

    override fun getItemCount() = listaContatos.size


    override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {

        holder.nomeContato.text = listaContatos[position].Nome

        holder.cardContato.setOnClickListener {
            actionClick(listaContatos[position].id!!)
        }

    }

    fun atualizarDados(contatos: List<ContatoViewModel>)
    {
        listaContatos = contatos

        notifyDataSetChanged()
    }

}