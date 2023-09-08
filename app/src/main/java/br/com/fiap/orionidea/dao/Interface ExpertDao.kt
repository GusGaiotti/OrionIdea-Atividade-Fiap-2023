package br.com.fiap.orionidea.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.orionidea.model.Expert

@Dao
interface ExpertDao {

    @Insert
    fun salvar(expert: Expert): Long

    @Update
    fun atualizar(expert: Expert): Int

    @Delete
    fun excluir(expert: Expert): Int

    @Query("SELECT * FROM tbl_expert WHERE id = :id")
    fun buscarExpertPeloId(id: Int): Expert

    @Query("SELECT * FROM tbl_expert ORDER BY name ASC")
    fun listarExperts(): List<Expert>

}