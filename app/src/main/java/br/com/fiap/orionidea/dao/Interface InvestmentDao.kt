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
    fun salvar(investment: Investment): Long

    @Update
    fun atualizar(investment: Investment): Int

    @Delete
    fun excluir(investment: Investment): Int

    @Query("SELECT * FROM tbl_investment WHERE id = :id")
    fun buscarInvestimentoPeloId(id: Int): Investment

    @Query("SELECT * FROM tbl_investment ORDER BY name ASC")
    fun listarInvestimentos(): List<Investment>

}