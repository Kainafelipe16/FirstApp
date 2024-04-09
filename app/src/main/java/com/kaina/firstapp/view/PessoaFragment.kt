package com.kaina.firstapp.view

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

        binding.btnEnviar.setOnClickListener {
            var nome = binding.edtNome.editableText.toString()
            var anoNascimento = binding.edtNascimento.editableText.toString()

            if (nome != "" && anoNascimento != "") {
                binding.tvNome.text = "Nome: $nome"

                val anoAtual = LocalDateTime.now().year
                var idade = 2024 - anoNascimento.toInt()

                binding.tvIdade.text = "Idade: $idade"

                viewModel.insert(
                    Pessoa(
                        nome = nome,
                        idade = idade
                    )
                )

                binding.edtNome.editableText.clear()
                binding.edtNascimento.editableText.clear()
                findNavController().navigateUp()
            }
            else {
                Toast.makeText(requireContext(), "Por favor, preencha os campos em branco !", Toast.LENGTH_LONG).show()
            }
        }
    }
}