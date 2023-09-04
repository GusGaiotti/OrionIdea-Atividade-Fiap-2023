package br.com.fiap.orionidea.repository

import android.content.Context
import br.com.fiap.orionidea.dao.InvestmentDb
import br.com.fiap.orionidea.model.Investment


class InvestimentoRepository(context: Context) {

    private val db = InvestmentDb.getDatabase(context).investmentDao()

    fun salvar(investment: Investment): Long {
        return db.salvar(investment)
    }

    fun atualizar(investment: Investment): Int {
        return db.atualizar(investment)
    }

    fun excluir(investment: Investment): Int {
        return db.excluir(investment)
    }

    fun listarInvestimentos(): List<Investment> {
        return db.listarInvestimentos()
    }

    fun buscarInvestimentoPeloId(id: Int): Investment {
        return db.buscarInvestimentoPeloId(id)
    }

}

