package br.com.fiap.orionidea.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.orionidea.model.Expert
import br.com.fiap.orionidea.model.Investment

@Database(entities = [Investment::class, Expert::class], version = 1)
abstract class InvestmentDb : RoomDatabase() {

    abstract fun investmentDao(): InvestmentDao
    abstract fun expertDao(): ExpertDao

    companion object {

        private lateinit var instance: InvestmentDb

        fun getDatabase(context: Context): InvestmentDb {
            if (!::instance.isInitialized) {
                instance = Room
                    .databaseBuilder(
                        context,
                        InvestmentDb::class.java,
                        "investment_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}