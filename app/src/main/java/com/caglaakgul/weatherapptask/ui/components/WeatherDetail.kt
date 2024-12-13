package com.caglaakgul.weatherapptask.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
fun WeatherDetail(cityWeather: CityWeather) {
    val weatherIconUrl = "https:${cityWeather.iconUrl}"
    val imagePainter = rememberImagePainter(weatherIconUrl)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = imagePainter,
            contentDescription = "Weather Icon",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 8.dp)
        )

        Text(
            text = cityWeather.cityName,
            style = TextStyle(
                fontSize = 30.sp,
                color = Color(0xFF2C2C2C),
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${cityWeather.temperature}°",
            style = TextStyle(
                fontSize = 70.sp,
                color = Color(0xFF2C2C2C),
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF2F2F2))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Humidity", color = Color(0xFFC4C4C4))
                    Text(text = "${cityWeather.humidity}%", color = Color(0xFF9A9A9A))
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "UV", color = Color(0xFFC4C4C4))
                    Text(text = "${cityWeather.uvIndex}", color = Color(0xFF9A9A9A))
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Feels Like", color = Color(0xFFC4C4C4))
                    Text(text = "${cityWeather.feelsLike}", color = Color(0xFF9A9A9A))
                }
            }
        }

    }
}