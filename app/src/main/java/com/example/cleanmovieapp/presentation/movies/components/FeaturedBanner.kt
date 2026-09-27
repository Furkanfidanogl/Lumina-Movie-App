package com.example.cleanmovieapp.presentation.movies.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FeaturedBanner(modifier: Modifier = Modifier) {
    val brandPrimary = Color(0xFF4F46E5)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(175.dp)
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(24.dp),
                spotColor = brandPrimary.copy(alpha = 0.3f)
            )
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFF312E81),
                        Color(0xFF4F46E5),
                        Color(0xFF06B6D4)
                    )
                )
            )
            .padding(20.dp)
    ) {

        Column(
            modifier = Modifier.align(Alignment.BottomStart)
        ) {

            Surface(
                color = Color.White.copy(alpha = 0.18f),
                shape = RoundedCornerShape(20.dp),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    Color.White.copy(alpha = 0.3f)
                )
            ) {

                Text(
                    text = "PREMIUM SELECTION",
                    modifier = Modifier.padding(
                        horizontal = 10.dp,
                        vertical = 4.dp
                    ),
                    color = Color.White,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.2.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Discover Top Charts",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = (-0.5).sp
            )

            Text(
                text = "Worldwide box-office trending titles right now",
                color = Color.White.copy(alpha = 0.82f),
                fontSize = 12.sp
            )
        }
    }
}