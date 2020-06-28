package com.poc.bruna.brasileiraofeminino.plugin.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.poc.bruna.brasileiraofeminino.plugin.model.entity.GameEntity
import com.poc.bruna.brasileiraofeminino.plugin.room.dao.GameDao

@Database(entities = [GameEntity::class], version = 1)
abstract class BrasileiraoDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        private const val DATABASE = "brasileirao_database"

        @Volatile
        private var INSTANCE: BrasileiraoDatabase? = null

        fun getInstanceDatabase(context: Context): BrasileiraoDatabase {
            return INSTANCE ?: synchronized(this) {
                buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): BrasileiraoDatabase {
            return Room.databaseBuilder(context, BrasileiraoDatabase::class.java, DATABASE).build()
        }
    }
}