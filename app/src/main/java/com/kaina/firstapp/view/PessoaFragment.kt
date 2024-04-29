package com.kaina.firstapp.view

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kaina.firstapp.databinding.FragmentPessoaBinding
import com.kaina.firstapp.service.model.Pessoa
import com.kaina.firstapp.viewmodel.PessoaViewModel
import java.time.LocalDateTime

class PessoaFragment : Fragment() {
    private val viewModel: PessoaViewModel by viewModels()
    private var _binding: FragmentPessoaBinding? = null
    private val binding: FragmentPessoaBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPessoaBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Carregar a pessoa, caso tenha selecionado
        arguments?.let {
            viewModel.getPessoa(it.getInt("pessoaId"))
        }

        binding.btnEnviar.setOnClickListener {
            var nome = binding.edtNome.editableText.toString()
            var anoNascimento = binding.edtNascimento.editableText.toString()
            var sexo = ""

            if (nome != "" && anoNascimento != "" && binding.rbMasc.isChecked || binding.rbFem.isChecked) {
                if (binding.rbMasc.isChecked) {
                    sexo = "Masculino"
                } else {
                    sexo = "Feminino"
                }

                var idade = 2024 - anoNascimento.toInt()
                var faixaEtaria = ""

                if (idade <= 12) {
                    faixaEtaria = "Infantil"
                } else if (idade <= 17) {
                    faixaEtaria = "Adolescente"
                } else if (idade <= 64) {
                    faixaEtaria = "Adulto"
                } else if (idade <= 110) {
                    faixaEtaria = "Idosos"
                } else {
                    Toast.makeText(requireContext(), "Você está MORTO !!", Toast.LENGTH_SHORT).show()
                }

                val pessoa = Pessoa(
                    nome = nome,
                    idade = idade,
                    sexo = sexo,
                    faixaEtaria = faixaEtaria
                )

                viewModel.pessoa.value?.let { 
                    pessoa.id = it.id
                    viewModel.update(pessoa)
                } ?: run {
                    viewModel.insert(pessoa)
                }

                binding.edtNome.editableText.clear()
                binding.edtNascimento.editableText.clear()
                findNavController().navigateUp()
            }
            else {
                Toast.makeText(requireContext(), "Por favor, preencha os campos em branco !", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnExcluir.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Excluir pessoa!")
                .setMessage("Você deseja realmente excluir esta pessoa?")
                .setPositiveButton("Sim") { _,_ ->
                    viewModel.delete(viewModel.pessoa.value?.id ?: 0)
                    findNavController().navigateUp()
                }
                .setNegativeButton("Não") { _,_ -> }
                .show()
        }

        viewModel.pessoa.observe(viewLifecycleOwner) { pessoa ->
            binding.edtNome.setText(pessoa.nome)
            binding.edtNascimento.setText((LocalDateTime.now().year - pessoa.idade).toString())

            if (pessoa.sexo == "Masculino") {
                binding.rbMasc.isChecked = true
            } else {
                binding.rbFem.isChecked = true
            }

            binding.btnExcluir.visibility = View.VISIBLE
        }
    }
}