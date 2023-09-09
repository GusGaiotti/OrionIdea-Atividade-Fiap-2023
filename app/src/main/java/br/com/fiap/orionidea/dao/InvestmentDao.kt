package br.com.fiap.orionidea.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.orionidea.model.Investment

@Dao
interface InvestmentDao {

    @Insert
    fun save(investment: Investment): Long

    @Update
    fun update(investment: Investment): Int

    @Delete
    fun delete(investment: Investment): Int

    @Query("SELECT * FROM tbl_investment WHERE id = :id")
    fun findInvestimentoById(id: Int): Investment

    @Query("SELECT * FROM tbl_investment ORDER BY name ASC")
    fun listInvestiments(): List<Investment>

    @Query("SELECT SUM(valueApplied) FROM tbl_investment")
    fun sumValueApplied(): Double
}