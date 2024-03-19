package com.kaina.firstapp.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.kaina.firstapp.R
import com.kaina.firstapp.databinding.ActivityMainBinding
import com.kaina.firstapp.databinding.FragmentCalculoBinding
import java.time.LocalDateTime

class CalculoFragment : Fragment() {
    private var _binding: FragmentCalculoBinding? = null
    private val binding: FragmentCalculoBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCalculoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEnviar.setOnClickListener{
            var nome = binding.edtNome.editableText.toString()

            binding.tvNome.text = "Nome: $nome"

            var anoNascimento = binding.edtNascimento.editableText.toString()
            val anoAtual = LocalDateTime.now().year
            var idade = 2024 - anoNascimento.toInt()

            binding.tvIdade.text = "Idade: $idade"
        }
    }
}

//binding.btnEnviar.setOnClickListener {
//      val nome = binding.edtNome.editableText.toString()
//      binding.tvNome.text = "Nome: $nome"

//    val anoNasc = binding.edtNascimento.editableText.toString()
//    val anoAtual = LocalDateTime.now().year
//    val idade = anoAtual - anoNasc.toInt()
//      binding.tvIdade.text = "Idade: $idade"
//    }