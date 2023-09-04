package br.com.fiap.orionidea.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_investment")
data class Investment(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String = "",
    val valorAplicado: String = "",
    val taxaDeJuros: Int = 0,
    val tipo: String = "",
    val anoVencimento: Int = 0,
    @ColumnInfo(name = "is_diary") val liqDia: Boolean = false
)