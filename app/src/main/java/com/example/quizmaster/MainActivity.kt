
package com.example.myapplicationapp01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.quizmaster.ui.navigation.Navegacao
import com.example.quizmaster.ui.theme.QuizMasterTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QuizMasterTheme {
                Navegacao()
            }
        }
    }
}
