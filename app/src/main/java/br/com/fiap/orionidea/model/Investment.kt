package br.com.fiap.orionidea.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_investment")
data class Investment(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String = "",
    val valueApplied: Double = 0.0,
    val interest: Double = 0.0,
    val type: String = "",
    val endDate: Int = 0,
    @ColumnInfo(name = "is_diary") val dailyLiq: Boolean = false
)