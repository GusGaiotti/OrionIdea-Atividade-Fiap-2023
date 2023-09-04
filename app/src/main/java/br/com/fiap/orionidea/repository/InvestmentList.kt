package br.com.fiap.orionidea.repository

import android.content.Context
import br.com.fiap.orionidea.dao.InvestmentDb
import br.com.fiap.orionidea.model.Investment


class InvestimentoRepository(context: Context) {

    private val db = InvestmentDb.getDatabase(context).investmentDao()

    fun save(investment: Investment): Long {
        return db.salvar(investment)
    }

    fun update  (investment: Investment): Int {
        return db.atualizar(investment)
    }

    fun delete(investment: Investment): Int {
        return db.excluir(investment)
    }

    fun listInvestments(): List<Investment> {
        return db.listarInvestimentos()
    }

}

