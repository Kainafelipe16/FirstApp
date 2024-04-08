package com.kaina.firstapp.service.repository

import android.content.Context
import com.kaina.firstapp.service.model.Pessoa
import com.kaina.firstapp.service.repository.local.FirstAppDataBase

class PessoaRepository(context: Context) {
    private val firstAppDb = FirstAppDataBase.getDataBase(context).pessoaDAO()

    suspend fun insertPessoa(pessoa: Pessoa) {
        firstAppDb.insert(pessoa)
    }

    suspend fun getPessoa(id: Int) {
        firstAppDb.getPessoa(id)
    }

    suspend fun getAll() {
        firstAppDb.getAll()
    }

    suspend fun update(pessoa: Pessoa) {
        firstAppDb.update(pessoa)
    }

    suspend fun delete(id: Int) {
        firstAppDb.delete(id)
    }
}