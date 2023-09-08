package br.com.fiap.orionidea.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_expert")
data class Expert(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String = "",
    val city: String = "",
    val telephone: String = ""
)