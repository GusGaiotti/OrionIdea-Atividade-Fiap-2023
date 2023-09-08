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
    fun save(expert: Expert): Long

    @Update
    fun update(expert: Expert): Int

    @Delete
    fun delete(expert: Expert): Int

    @Query("SELECT * FROM tbl_expert WHERE id = :id")
    fun findExpertById(id: Int): Expert

    @Query("SELECT * FROM tbl_expert ORDER BY name ASC")
    fun listExperts(): List<Expert>

}