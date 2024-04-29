package com.kaina.firstapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kaina.firstapp.service.model.Pessoa
import com.kaina.firstapp.service.repository.PessoaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PessoaViewModel (application: Application) : AndroidViewModel(application) {
    private val repository = PessoaRepository(application)
    private val mPessoa = MutableLiveData<Pessoa>()
    val pessoa: LiveData<Pessoa> = mPessoa

    fun insert (pessoa: Pessoa) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertPessoa(pessoa)
        }
    }

    fun getPessoa (id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            mPessoa.postValue(repository.getPessoa(id))
        }
    }

    fun update (pessoa: Pessoa) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(pessoa)
        }
    }

    fun delete (id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(id)
        }
    }
}