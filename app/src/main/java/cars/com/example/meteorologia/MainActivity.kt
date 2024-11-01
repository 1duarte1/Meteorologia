package cars.com.example.meteorologia1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import com.example.meteorologia1.R
import cars.com.example.meteorologia.ui.theme.Meteorologia1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Meteorologia1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        // Conteúdo principal em cima da imagem de fundo
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            TopBar()
                            TitleBar(name = "Duarte", date = formatData(Date()), location = "Arruda dos Vinhos")
                            WeatherInfoSection(
                                temperature = "23º",
                                description = "Limpo",
                                imageResId = R.drawable.sun
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Next7DaysForecast()
                        }
                    }
                }
            }
        }
    }
}

// Função para formatar a data
fun formatData(data: Date): String {
    val format = SimpleDateFormat("dd MMMM, EEEE yyyy", Locale("pt", "PT"))
    return format.format(data)
}

// TopBar Composable
@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.search),
            contentDescription = "Pesquisa",
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(color = Color.LightGray)
                .padding(8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.foto),
            contentDescription = "Foto",
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(24.dp))
        )
    }
}

// TitleBar Composable
@Composable
fun TitleBar(name: String, date: String, location: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            Text(
                text = "Olá ",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = name,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = date,
            fontSize = 16.sp,
            color = Color.Gray
        )
        Text(
            text = location,
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

// Composable para exibir as informações do tempo
@Composable
fun WeatherInfoSection(temperature: String, description: String, imageResId: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Container com fundo branco e circular
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.White)
                .padding(1.dp)
                .size(300.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Imagem do tempo",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = temperature,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = description,
                    fontSize = 20.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

// Previsão dos próximos 7 dias
@Composable
fun Next7DaysForecast() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp)
    ) {
        Text(
            text = "Próximos 7 dias",
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            DayForecastCard(day = "Seg", temp = "25º", description = "Limpo", imageResId = R.drawable.sun)
            DayForecastCard(day = "Ter", temp = "22º", description = "Limpo", imageResId = R.drawable.sun)
            DayForecastCard(day = "Qua", temp = "15º", description = "Chuva", imageResId = R.drawable.snowy)
            DayForecastCard(day = "Qui", temp = "18º", description = "Tempestade", imageResId = R.drawable.storm)
            DayForecastCard(day = "Sex", temp = "15º", description = "Tempestade", imageResId = R.drawable.storm)
            DayForecastCard(day = "Sab", temp = "21º", description = "Tempestade", imageResId = R.drawable.storm)
            DayForecastCard(day = "Dom", temp = "17º", description = "Tempestade", imageResId = R.drawable.storm)
        }
    }
}

// Previsão de um dia
@Composable
fun DayForecastCard(day: String, temp: String, description: String, imageResId: Int) {
    Box(
        modifier = Modifier
            .padding(end = 16.dp)
            .width(80.dp)
            .height(130.dp)
            .background(color = Color(0xFFEEEEEE), shape = CircleShape)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Ícone do tempo",
                modifier = Modifier.size(40.dp)
            )
            Text(text = day, fontWeight = FontWeight.Bold)
            Text(text = temp, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = description, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

// Preview para TopBar
@Composable
fun TopBarPreview() {
    Meteorologia1Theme {
        TopBar()
    }
}

@Composable
fun TitleBarPreview() {
    Meteorologia1Theme {
        TitleBar(name = "Duarte", date = formatData(Date()), location = "Arruda dos Vinhos")
    }
}

// Informações do tempo
@Composable
fun WeatherInfoPreview() {
    Meteorologia1Theme {
        WeatherInfoSection(
            temperature = "23º",
            description = "Limpo",
            imageResId = R.drawable.sun
        )
    }
}

// Preview da página principal
@Preview(showBackground = true)
@Composable
fun Page() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagem de fundo com 20% de transparência
        Image(
            painter = painterResource(id = R.drawable._86449326_37c2256216_b),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.2f) // Define 20% de opacidade
        )

        // Conteúdo principal
        Column(modifier = Modifier.fillMaxSize()) {
            TopBarPreview()
            TitleBarPreview()
            WeatherInfoPreview()
            Next7DaysForecast()
        }
    }
}
