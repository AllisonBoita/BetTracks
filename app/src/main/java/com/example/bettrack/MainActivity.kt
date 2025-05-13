package com.example.bettrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bettrack.ui.theme.BetTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BetTrackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaApostas()
                }
            }
        }
    }
}

@Composable
fun TelaApostas() {
    val apostas = listOf(
        Aposta("Flamengo", "Vasco", 2.10, 10, "GREEN"),
        Aposta("Palmeiras", "Santos", 1.80, 8, "RED")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
            .padding(16.dp)
    ) {
        Text(
            text = "📈 BetTrack",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(apostas) { aposta ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("${aposta.mandante} x ${aposta.visitante}", fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Odd: ${aposta.odd}  |  Stake: ${aposta.stake}u")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "Resultado: ${aposta.resultado}",
                            color = when (aposta.resultado) {
                                "GREEN" -> Color(0xFF2E7D32)
                                "RED" -> Color(0xFFC62828)
                                else -> Color.Gray
                            }
                        )
                    }
                }
            }
        }
    }
}

data class Aposta(
    val mandante: String,
    val visitante: String,
    val odd: Double,
    val stake: Int,
    val resultado: String
)

@Preview(showBackground = true)
@Composable
fun TelaApostasPreview() {
    BetTrackTheme {
        TelaApostas()
    }
}
