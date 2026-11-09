
package com.example.quizmaster.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.example.quizmaster.data.local.BancoDeDados
import com.example.quizmaster.data.local.Pergunta
import kotlinx.coroutines.launch

@Composable
fun TelaGerenciarPerguntas(
    voltar: () -> Unit
) {
    val context = LocalContext.current

    val banco = remember {
        BancoDeDados.getDatabase(context)
    }

    val dao = banco.perguntaDao()

    val perguntas by dao.listarPerguntas()
        .collectAsState(initial = emptyList())

    val scope = rememberCoroutineScope()

    var mostrarCadastro by remember {
        mutableStateOf(false)
    }

    var textoPergunta by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Gerenciar Perguntas",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                mostrarCadastro = !mostrarCadastro
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar Pergunta")
        }

        if (mostrarCadastro) {

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = textoPergunta,
                onValueChange = {
                    textoPergunta = it
                },
                label = {
                    Text("Digite a pergunta")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {

                    if (textoPergunta.isNotBlank()) {

                        scope.launch {

                            dao.inserir(
                                Pergunta(
                                    texto = textoPergunta.trim()
                                )
                            )

                            textoPergunta = ""
                            mostrarCadastro = false
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar Pergunta")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Perguntas cadastradas",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(
                items = perguntas,
                key = { it.id }
            ) { pergunta ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(text = pergunta.texto)

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        TextButton(
                            onClick = {

                                scope.launch {
                                    dao.excluir(pergunta)
                                }
                            }
                        ) {
                            Text("Excluir")
                        }
                    }
                }
            }
        }

        OutlinedButton(
            onClick = voltar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }
    }
}
