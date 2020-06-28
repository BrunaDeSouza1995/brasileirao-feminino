package com.poc.bruna.brasileiraofeminino.plugin.rule

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.poc.bruna.brasileiraofeminino.plugin.room.database.BrasileiraoDatabase
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class DatabaseRule : TestRule {
    var database: BrasileiraoDatabase? = null

    override fun apply(base: Statement?, description: Description?): Statement =
        object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                createDatabase()
                try {
                    base?.evaluate()
                } finally {
                    closeDatabase()
                }
            }
        }

    private fun createDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().context

        database = Room.inMemoryDatabaseBuilder(context, BrasileiraoDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    private fun closeDatabase() {
        database?.close()
    }
}
