package br.pro.nigri.assessmentandroid.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.pro.nigri.assessmentandroid.Model.ContatoModel
import br.pro.nigri.assessmentandroid.R
import kotlinx.android.synthetic.main.contatos_card.view.*

class ContatoAdapter(var listaContatos:List<ContatoModel> = listOf()):
    RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder>(){



    class ContatoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val nomeText: TextView = itemView.findViewById(R.id.txtNomeContato)

        val celularText: TextView = itemView.findViewById(R.id.txtCelularContato)

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
        holder.celularText.text = listaContatos[position].Celular.toString()
    }

    fun atualizarDados(contatos: List<ContatoModel>)
    {
        listaContatos = contatos

        notifyDataSetChanged()
    }
}