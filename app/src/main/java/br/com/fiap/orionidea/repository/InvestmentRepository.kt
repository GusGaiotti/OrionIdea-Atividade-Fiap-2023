package br.com.fiap.orionidea.repository

import android.content.Context
import br.com.fiap.orionidea.dao.InvestmentDb
import br.com.fiap.orionidea.model.Investment


class InvestimentRepository(context: Context) {

    private val db = InvestmentDb.getDatabase(context).investmentDao()

    fun save(investment: Investment): Long {
        return db.save(investment)
    }

    fun update(investment: Investment): Int {
        return db.update(investment)
    }

    fun delete(investment: Investment): Int {
        return db.delete(investment)
    }

    fun listInvestments(): List<Investment> {
        return db.listInvestiments()
    }

    fun sumValueApplied(): Double {
        return db.sumValueApplied()
    }
}