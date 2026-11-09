
package com.example.quizmaster.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "perguntas")
data class Pergunta(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val texto: String
)
