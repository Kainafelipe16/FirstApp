package com.kaina.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaina.firstapp.R
import com.kaina.firstapp.databinding.FragmentAllPessoasBinding
import com.kaina.firstapp.view.adapter.PessoaAdapter
import com.kaina.firstapp.viewmodel.AllPessoasViewModel

class AllPessoasFragment : Fragment() {
    private val viewModel: AllPessoasViewModel by viewModels()
    private lateinit var adapter: PessoaAdapter
    private var _binding: FragmentAllPessoasBinding? = null
    private val binding: FragmentAllPessoasBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Configurar o Binding
        _binding = FragmentAllPessoasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Quando clicar em algum item da lista
        adapter = PessoaAdapter(viewModel.pessoaList.value) {
            pessoa ->
        }

        //Configura o Recycler
        var recycler = binding.rvPessoas
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        //Observa para adicionar um item na lista quando for adicionado
        viewModel.pessoaList.observe(viewLifecycleOwner) {
            adapter.updatePessoas(it)
        }

        // Navegar para a tela de cadastro de pessoas
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.pessoaFragment)
        }

        //Carrega as pessoas e popula a lista
        viewModel.load()
    }
}