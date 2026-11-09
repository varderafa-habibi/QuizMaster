
package com.example.quizmaster.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PerguntaDao {

    @Query("SELECT * FROM perguntas ORDER BY id DESC")
    fun listarPerguntas(): Flow<List<Pergunta>>

    @Insert
    suspend fun inserir(pergunta: Pergunta)

    @Delete
    suspend fun excluir(pergunta: Pergunta)
}
