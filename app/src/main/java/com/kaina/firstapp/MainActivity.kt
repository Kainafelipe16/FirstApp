package com.kaina.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kaina.firstapp.databinding.ActivityMainBinding
import com.kaina.firstapp.databinding.TelaLinearBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnviar.setOnClickListener{
            var email = binding.edtEmail.editableText.toString()
            if (email.contains("@") && email.substringAfter("@").contains(".com"))
            {
              binding.tvEmail.text = "E-mail: ${email}"
            } else {
                binding.tvEmail.text = "O E-mail digitado está incorreto!"
            }

            var tel = binding.edtTelefone.editableText.toString()
            if (tel.length == 11) {
                binding.tvTelefone.text = "Telefone: ${tel}"
            } else {
                binding.tvTelefone.text = "O telefone está incorreto!"
            }
        }
    }
}