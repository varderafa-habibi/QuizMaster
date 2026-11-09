
package com.example.quizmaster.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Pergunta::class],
    version = 1,
    exportSchema = false
)
abstract class BancoDeDados : RoomDatabase() {

    abstract fun perguntaDao(): PerguntaDao

    companion object {

        @Volatile
        private var INSTANCE: BancoDeDados? = null

        fun getDatabase(context: Context): BancoDeDados {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BancoDeDados::class.java,
                    "quizmaster_database"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}
