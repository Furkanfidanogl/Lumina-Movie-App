package com.example.cleanmovieapp.presentation.welcome.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToMovies: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFAFBFF))
    ) {
        // --- AMBIENT AURA LAYER ---
        Box(
            modifier = Modifier
                .offset(x = (-40).dp, y = (-50).dp)
                .size(340.dp)
                .blur(110.dp)
                .background(
                    Brush.radialGradient(
                        listOf(
                            Color(0xFF6366F1).copy(alpha = 0.18f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = 100.dp, y = (-20).dp)
                .size(380.dp)
                .blur(120.dp)
                .background(
                    Brush.radialGradient(
                        listOf(
                            Color(0xFF06B6D4).copy(alpha = 0.16f),
                            Color(0xFFA855F7).copy(alpha = 0.10f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = (-30).dp, y = 50.dp)
                .size(300.dp)
                .blur(100.dp)
                .background(
                    Brush.radialGradient(
                        listOf(
                            Color(0xFF4F46E5).copy(alpha = 0.12f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        // --- ANA EKRAN İÇERİĞİ ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .shadow(12.dp, RoundedCornerShape(12.dp), spotColor = Color(0xFF4F46E5).copy(alpha = 0.35f))
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                Brush.linearGradient(
                                    listOf(Color(0xFF4F46E5), Color(0xFF06B6D4))
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Column {
                        Text(
                            text = "LUMINA",
                            color = Color(0xFF0F172A),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 2.5.sp
                        )
                        Text(
                            text = "CINEMA STUDIO",
                            color = Color(0xFF64748B),
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    }
                }

                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(30.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    shadowElevation = 4.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF10B981))
                        )
                        Text(
                            text = "v2.4 READY",
                            color = Color(0xFF334155),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(0.08f))

            // 3D Hissiyatlı Katmanlı Kart Stage'i (Hero Viewport)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(310.dp),
                contentAlignment = Alignment.Center
            ) {
                // Arka Katman (Hafif Açılı Sol Kart)
                Box(
                    modifier = Modifier
                        .size(190.dp, 250.dp)
                        .offset(x = (-42).dp, y = 6.dp)
                        .rotate(-8f)
                        .clip(RoundedCornerShape(26.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    Color(0xFFEDE9FE),
                                    Color(0xFFE0E7FF)
                                )
                            )
                        )
                        .border(1.dp, Color.White.copy(alpha = 0.9f), RoundedCornerShape(26.dp))
                )

                // Arka Katman (Hafif Açılı Sağ Kart)
                Box(
                    modifier = Modifier
                        .size(190.dp, 250.dp)
                        .offset(x = 42.dp, y = 6.dp)
                        .rotate(8f)
                        .clip(RoundedCornerShape(26.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    Color(0xFFCFFAFE),
                                    Color(0xFFE0F2FE)
                                )
                            )
                        )
                        .border(1.dp, Color.White.copy(alpha = 0.9f), RoundedCornerShape(26.dp))
                )

                // Merkez Odak Kartı (Hero Front Poster)
                Surface(
                    modifier = Modifier
                        .size(210.dp, 275.dp)
                        .shadow(
                            elevation = 28.dp,
                            shape = RoundedCornerShape(28.dp),
                            spotColor = Color(0xFF4F46E5).copy(alpha = 0.22f),
                            ambientColor = Color(0xFF0F172A).copy(alpha = 0.08f)
                        ),
                    color = Color.White,
                    shape = RoundedCornerShape(28.dp),
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, Color.White)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Color(0xFFF8FAFC),
                                        Color(0xFFEEF2FF)
                                    )
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(68.dp)
                                    .shadow(16.dp, CircleShape, spotColor = Color(0xFF4F46E5).copy(alpha = 0.30f))
                                    .clip(CircleShape)
                                    .background(
                                        Brush.linearGradient(
                                            listOf(Color(0xFF4F46E5), Color(0xFF06B6D4))
                                        )
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AutoAwesome,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(32.dp)
                                )
                            }

                            Text(
                                text = "CINEMATIC UI",
                                color = Color(0xFF1E293B),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 2.sp
                            )

                            Text(
                                text = "ULTRA HDR PREVIEW",
                                color = Color(0xFF6366F1),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        }
                    }
                }

                // Floating Glass Chip 1 (Sol Üst)
                GlassFloatingChip(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 10.dp, y = 20.dp),
                    icon = Icons.Default.Star,
                    iconColor = Color(0xFFF59E0B),
                    title = "9.4",
                    subtitle = "IMDb Choice"
                )

                // Floating Glass Chip 2 (Sağ Alt)
                GlassFloatingChip(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-8).dp, y = (-12).dp),
                    icon = Icons.Default.TrendingUp,
                    iconColor = Color(0xFF10B981),
                    title = "+1.4M",
                    subtitle = "Streamed"
                )
            }

            Spacer(modifier = Modifier.weight(0.12f))

            // Sub-pill Header
            Surface(
                color = Color(0xFFEEF2FF),
                shape = RoundedCornerShape(24.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE0E7FF))
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Bolt,
                        contentDescription = null,
                        tint = Color(0xFF4F46E5),
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = "THE NEW ENTERTAINMENT ERA",
                        color = Color(0xFF4F46E5),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.2.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Gradient Typography
            Text(
                text = "Experience Cinema\nWithout Limits.",
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                lineHeight = 46.sp,
                textAlign = TextAlign.Center,
                letterSpacing = (-1).sp,
                style = androidx.compose.ui.text.TextStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF0F172A),
                            Color(0xFF1E293B),
                            Color(0xFF334155)
                        )
                    )
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Unrivaled access to box-office hits, cult classics, and crystal-clear soundscapes right at your fingertips.",
                color = Color(0xFF64748B),
                fontSize = 15.sp,
                lineHeight = 23.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.weight(0.18f))

            // Pro Action Button
            Button(
                onClick = onNavigateToMovies,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp)
                    .shadow(
                        elevation = 20.dp,
                        shape = RoundedCornerShape(22.dp),
                        spotColor = Color(0xFF4F46E5).copy(alpha = 0.45f)
                    )
                    .clip(RoundedCornerShape(22.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                Color(0xFF4F46E5),
                                Color(0xFF6366F1),
                                Color(0xFF06B6D4)
                            )
                        )
                    ),
                shape = RoundedCornerShape(22.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Start Watching Now",
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.3.sp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.20f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(26.dp))
        }
    }
}

@Composable
private fun GlassFloatingChip(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconColor: Color,
    title: String,
    subtitle: String
) {
    Surface(
        modifier = modifier
            .shadow(
                elevation = 14.dp,
                shape = RoundedCornerShape(18.dp),
                spotColor = Color(0xFF0F172A).copy(alpha = 0.08f)
            ),
        color = Color.White.copy(alpha = 0.94f),
        shape = RoundedCornerShape(18.dp),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, Color.White)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(18.dp)
                )
            }
            Column {
                Text(
                    text = title,
                    color = Color(0xFF0F172A),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = subtitle,
                    color = Color(0xFF94A3B8),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}