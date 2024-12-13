package com.caglaakgul.weatherapptask.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.caglaakgul.weatherapptask.domain.state.CityWeather
import com.caglaakgul.weatherapptask.ui.theme.Poppins

@Composable
fun CityWeatherCard(
    cityWeather: CityWeather,
    onCitySelected: (String) -> Unit
) {
    val weatherIconUrl = "https:${cityWeather.iconUrl}"
    val imagePainter = rememberImagePainter(weatherIconUrl)

    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF2F2F2))
                .clickable { onCitySelected(cityWeather.cityName) }
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = cityWeather.cityName,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color(0xFF2C2C2C),
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${cityWeather.temperature}°",
                        style = TextStyle(
                            fontSize = 60.sp,
                            color = Color(0xFF2C2C2C),
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }

                Image(
                    painter = imagePainter,
                    contentDescription = "Weather Icon",
                    modifier = Modifier
                        .size(width = 80.dp, height = 60.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}