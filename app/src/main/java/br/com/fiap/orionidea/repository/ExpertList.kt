package br.com.fiap.orionidea.repository

import android.content.Context
import br.com.fiap.orionidea.dao.InvestmentDb
import br.com.fiap.orionidea.model.Expert
import br.com.fiap.orionidea.model.Investment


class ExpertRepository(context: Context) {

    private val db = InvestmentDb.getDatabase(context).expertDao()

    fun save(expert: Expert): Long {
        return db.save(expert)
    }

    fun update(expert: Expert): Int {
        return db.update(expert)
    }

    fun delete(expert: Expert): Int {
        return db.delete(expert)
    }

    fun listExperts(): List<Expert> {
        return db.listExperts()
    }

}

