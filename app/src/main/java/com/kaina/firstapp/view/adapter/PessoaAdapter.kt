package com.kaina.firstapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kaina.firstapp.databinding.ListItemPessoaBinding
import com.kaina.firstapp.service.model.Pessoa

class PessoaAdapter (pessoas: List<Pessoa>?, private val clickListener: (Pessoa) -> Unit) : RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder>() {
    private var pessoaList: List<Pessoa> = arrayListOf()

    class PessoaViewHolder(private val binding: ListItemPessoaBinding) : RecyclerView.ViewHolder(binding.root) {
        // Carrega as informações da pessoa na lista
        fun bind(pessoa: Pessoa, clickListener: (Pessoa) -> Unit) {
            binding.tvNome.text = pessoa.nome
            binding.tvIdade.text = pessoa.idade.toString()
            binding.tvFaixaetaria.text = pessoa.faixaEtaria

            if (pessoa.sexo == "Masculino") {
                binding.imgMasc.visibility = View.VISIBLE
                binding.imgFem.visibility = View.GONE
            } else {
                binding.imgFem.visibility = View.VISIBLE
                binding.imgMasc.visibility = View.GONE
            }

            //Configura o click de algum item da lista
            binding.root.setOnClickListener{
                (clickListener(pessoa))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        val listItemPessoaBinding = ListItemPessoaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PessoaViewHolder(listItemPessoaBinding)
    }

    override fun getItemCount(): Int {
        return pessoaList.count()
    }

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {
        holder.bind(pessoaList[position], clickListener)
    }

    fun updatePessoas(list: List<Pessoa>) {
        pessoaList = list
        notifyDataSetChanged()
    }
}
