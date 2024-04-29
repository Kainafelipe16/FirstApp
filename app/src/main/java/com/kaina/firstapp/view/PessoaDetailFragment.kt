package com.kaina.firstapp.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kaina.firstapp.databinding.FragmentPessoaDetailBinding
import com.kaina.firstapp.viewmodel.PessoaViewModel
import java.time.LocalDateTime

class PessoaDetailFragment : Fragment() {
    // Chamar a viewmodel para pegar os dados
    private val viewModel: PessoaViewModel by viewModels()

    // Criar o Binding para pegar so elementos da tela
    private var _binding: FragmentPessoaDetailBinding? = null
    private val binding: FragmentPessoaDetailBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Configurar o Binding
        _binding = FragmentPessoaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Chamar a função onViewCreated onde vamos implementar o código
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Pegar o id da pessoa que foi enviado pela AllPessoasFragment
        //Setar a pessoa para ser carregada
        arguments?.let {
            viewModel.getPessoa(it.getInt("pessoaId"))
        }

        //Carregar as informações da pessoa selecionada
        viewModel.pessoa.observe(viewLifecycleOwner) { pessoa ->
            binding.tvNome.text = pessoa.nome
            binding.tvIdade.text = (LocalDateTime.now().year - pessoa.idade).toString()

            binding.tvIdade.text = pessoa.idade.toString()
            binding.tvFaixaetaria.text = pessoa.faixaEtaria

            if (pessoa.sexo == "Masculino") {
                binding.imgMasc.visibility = View.VISIBLE
                binding.imgFem.visibility = View.GONE
            } else {
                binding.imgFem.visibility = View.VISIBLE
                binding.imgMasc.visibility = View.GONE
            }
        }
    }
}