package com.example.cleanmovieapp.presentation.movie_detail.views

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.cleanmovieapp.presentation.movie_detail.viewmodel.MovieDetailUiState
import com.example.cleanmovieapp.presentation.movie_detail.viewmodel.MovieDetailViewModel
import com.example.cleanmovieapp.util.Constants
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailScreen(
    modifier: Modifier, id: Int, onNavigateBack: () -> Unit, uiState: MovieDetailUiState , getDetail : (Int) -> Unit
) {

    val movieDetail = uiState.movieDetail

    val context = LocalContext.current

    LaunchedEffect(id) {
        getDetail(id)
    }

    val screenBg = Color(0xFFFAFBFF)
    val brandPrimary = Color(0xFF4F46E5)
    val brandSecondary = Color(0xFF06B6D4)
    val textMain = Color(0xFF0F172A)
    val textMuted = Color(0xFF64748B)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = screenBg
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                // BACKDROP
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {

                    AsyncImage(
                        model = movieDetail?.backdropPath?.let {
                            Constants.IMAGE_BASE_URL + it
                        },
                        contentDescription = movieDetail?.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    // Cinematic fade
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color(0x330F172A),
                                        screenBg
                                    ),
                                    startY = 200f
                                )
                            )
                    )

                    // BACK BUTTON
                    Surface(
                        modifier = Modifier
                            .statusBarsPadding()
                            .clickable {
                                onNavigateBack()
                            }
                            .padding(
                                start = 18.dp,
                                top = 10.dp
                            )
                            .size(44.dp),
                        shape = CircleShape,
                        color = Color.White.copy(alpha = 0.92f),
                        shadowElevation = 8.dp
                    ) {

                        Box(
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = textMain,
                                modifier = Modifier.size(21.dp)
                            )
                        }
                    }

                    // LUMINA BADGE
                    Surface(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .statusBarsPadding()
                            .padding(
                                end = 18.dp,
                                top = 10.dp
                            ),
                        color = Color.White.copy(alpha = 0.92f),
                        shape = RoundedCornerShape(14.dp),
                        shadowElevation = 8.dp
                    ) {

                        Row(
                            modifier = Modifier.padding(
                                horizontal = 12.dp,
                                vertical = 9.dp
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement =
                                Arrangement.spacedBy(6.dp)
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(7.dp)
                                    .clip(CircleShape)
                                    .background(brandSecondary)
                            )

                            Text(
                                text = "LUMINA",
                                color = textMain,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = 1.2.sp
                            )
                        }
                    }
                }

                // MAIN CONTENT
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .offset(y = (-58).dp)
                ) {

                    // POSTER + TITLE
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom
                    ) {

                        AsyncImage(
                            model = movieDetail?.posterPath?.let {
                                Constants.IMAGE_BASE_URL + it
                            },
                            contentDescription = movieDetail?.title,
                            modifier = Modifier
                                .width(128.dp)
                                .height(190.dp)
                                .shadow(
                                    elevation = 16.dp,
                                    shape = RoundedCornerShape(22.dp),
                                    spotColor = brandPrimary.copy(
                                        alpha = 0.25f
                                    )
                                )
                                .clip(
                                    RoundedCornerShape(22.dp)
                                ),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(
                            modifier = Modifier.width(16.dp)
                        )

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(bottom = 6.dp)
                        ) {

                            Surface(
                                color = Color(0xFFEEF2FF),
                                shape = RoundedCornerShape(50.dp)
                            ) {

                                Text(
                                    text = "NOW TRENDING",
                                    modifier = Modifier.padding(
                                        horizontal = 10.dp,
                                        vertical = 5.dp
                                    ),
                                    color = brandPrimary,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    letterSpacing = 1.sp
                                )
                            }

                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )

                            Text(
                                text = movieDetail?.title.orEmpty(),
                                color = textMain,
                                fontSize = 25.sp,
                                lineHeight = 29.sp,
                                fontWeight = FontWeight.Black,
                                letterSpacing = (-0.5).sp
                            )

                            Spacer(
                                modifier = Modifier.height(7.dp)
                            )

                            Text(
                                text = movieDetail?.tagline.orEmpty(),
                                color = textMuted,
                                fontSize = 13.sp,
                                lineHeight = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(28.dp)
                    )

                    // STATS
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement =
                            Arrangement.spacedBy(10.dp)
                    ) {

                        DetailStatCard(
                            modifier = Modifier.weight(1f),
                            icon = {

                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = Color(0xFFF59E0B),
                                    modifier = Modifier.size(18.dp)
                                )
                            },
                            title = movieDetail
                                ?.voteAverage
                                ?.let {
                                    "%.1f".format(it)
                                } ?: "-",
                            subtitle = "Rating"
                        )

                        DetailStatCard(
                            modifier = Modifier.weight(1f),
                            icon = {

                                Icon(
                                    imageVector = Icons.Default.Timer,
                                    contentDescription = null,
                                    tint = brandPrimary,
                                    modifier = Modifier.size(18.dp)
                                )
                            },
                            title = movieDetail
                                ?.runtime
                                ?.let {
                                    "$it min"
                                } ?: "-",
                            subtitle = "Runtime"
                        )

                        DetailStatCard(
                            modifier = Modifier.weight(1f),
                            icon = {

                                Text(
                                    text = if (
                                        movieDetail?.adult == true
                                    ) {
                                        "18+"
                                    } else {
                                        "13+"
                                    },
                                    color = brandSecondary,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Black
                                )
                            },
                            title = if (
                                movieDetail?.adult == true
                            ) {
                                "Adult"
                            } else {
                                "General"
                            },
                            subtitle = "Rating"
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(28.dp)
                    )

                    // ABOUT
                    Text(
                        text = "About the movie",
                        color = textMain,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "Everything you need to know before watching.",
                        color = textMuted,
                        fontSize = 12.sp
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        shape = RoundedCornerShape(22.dp),
                        border =
                            androidx.compose.foundation.BorderStroke(
                                width = 1.dp,
                                color = Color(0xFFF1F5F9)
                            ),
                        shadowElevation = 3.dp
                    ) {

                        Column(
                            modifier = Modifier.padding(18.dp)
                        ) {

                            Text(
                                text = "TAGLINE",
                                color = brandPrimary,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = 1.4.sp
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Text(
                                text = movieDetail
                                    ?.tagline
                                    ?.takeIf {
                                        it.isNotBlank()
                                    }
                                    ?.let {
                                        "\"$it\""
                                    }
                                    ?: "No tagline available.",
                                color = textMain,
                                fontSize = 18.sp,
                                lineHeight = 25.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier.height(18.dp)
                            )

                            HorizontalDivider(
                                color = Color(0xFFF1F5F9)
                            )

                            Spacer(
                                modifier = Modifier.height(18.dp)
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement =
                                    Arrangement.SpaceBetween,
                                verticalAlignment =
                                    Alignment.CenterVertically
                            ) {

                                Column {

                                    Text(
                                        text = "CONTENT RATING",
                                        color = textMuted,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = 1.sp
                                    )

                                    Spacer(
                                        modifier =
                                            Modifier.height(4.dp)
                                    )

                                    Text(
                                        text = if (
                                            movieDetail?.adult == true
                                        ) {
                                            "18+ Audience"
                                        } else {
                                            "13+ Audience"
                                        },
                                        color = textMain,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                Surface(
                                    color = if (
                                        movieDetail?.adult == true
                                    ) {
                                        Color(0xFFFEF2F2)
                                    } else {
                                        Color(0xFFECFDF5)
                                    },
                                    shape = RoundedCornerShape(10.dp)
                                ) {

                                    Text(
                                        text = if (
                                            movieDetail?.adult == true
                                        ) {
                                            "MATURE"
                                        } else {
                                            "GENERAL"
                                        },
                                        modifier = Modifier.padding(
                                            horizontal = 10.dp,
                                            vertical = 6.dp
                                        ),
                                        color = if (
                                            movieDetail?.adult == true
                                        ) {
                                            Color(0xFFDC2626)
                                        } else {
                                            Color(0xFF059669)
                                        },
                                        fontSize = 10.sp,
                                        fontWeight =
                                            FontWeight.ExtraBold
                                    )
                                }
                            }
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(18.dp)
                    )

                    // OFFICIAL WEBSITE
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp)
                            .shadow(
                                elevation = 10.dp,
                                shape =
                                    RoundedCornerShape(18.dp),
                                spotColor =
                                    brandPrimary.copy(
                                        alpha = 0.20f
                                    )
                            ),
                        color = Color.Transparent,
                        shape = RoundedCornerShape(18.dp),
                        onClick = { openTheBrowser(context = context, url = movieDetail?.homepage) }
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(
                                            brandPrimary,
                                            Color(0xFF6366F1),
                                            brandSecondary
                                        )
                                    )
                                )
                                .padding(horizontal = 18.dp)
                        ) {

                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment =
                                    Alignment.CenterVertically,
                                horizontalArrangement =
                                    Arrangement.SpaceBetween
                            ) {

                                Row(
                                    verticalAlignment =
                                        Alignment.CenterVertically
                                ) {

                                    Surface(
                                        modifier =
                                            Modifier.size(34.dp),
                                        color =
                                            Color.White.copy(
                                                alpha = 0.17f
                                            ),
                                        shape =
                                            RoundedCornerShape(10.dp)
                                    ) {

                                        Box(
                                            contentAlignment =
                                                Alignment.Center
                                        ) {

                                            Icon(
                                                imageVector =
                                                    Icons.Default.Language,
                                                contentDescription = null,
                                                tint = Color.White,
                                                modifier =
                                                    Modifier.size(18.dp)
                                            )
                                        }
                                    }

                                    Spacer(
                                        modifier =
                                            Modifier.width(12.dp)
                                    )

                                    Column {

                                        Text(
                                            text =
                                                "Official Website",
                                            color = Color.White,
                                            fontSize = 14.sp,
                                            fontWeight =
                                                FontWeight.Bold
                                        )

                                        Text(
                                            text = if (
                                                movieDetail
                                                    ?.homepage
                                                    .isNullOrBlank()
                                            ) {
                                                "Website unavailable"
                                            } else {
                                                "Visit movie homepage"
                                            },
                                            color =
                                                Color.White.copy(
                                                    alpha = 0.72f
                                                ),
                                            fontSize = 10.sp
                                        )
                                    }
                                }

                                Text(
                                    text = "↗",
                                    color = Color.White,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(28.dp)
                    )

                    Text(
                        text = "LUMINA • CINEMA STUDIO",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color(0xFFCBD5E1),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.7.sp
                    )

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )
                }
            }

            // LOADING OVERLAY
            if (uiState.isLoading) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            screenBg.copy(alpha = 0.96f)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 42.dp),
                        color = Color.White,
                        shape = RoundedCornerShape(24.dp),
                        shadowElevation = 12.dp
                    ) {

                        Column(
                            modifier = Modifier.padding(
                                horizontal = 24.dp,
                                vertical = 28.dp
                            ),
                            horizontalAlignment =
                                Alignment.CenterHorizontally
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(
                                        RoundedCornerShape(15.dp)
                                    )
                                    .background(
                                        Brush.linearGradient(
                                            listOf(
                                                brandPrimary,
                                                brandSecondary
                                            )
                                        )
                                    ),
                                contentAlignment =
                                    Alignment.Center
                            ) {

                                Text(
                                    text = "L",
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Black
                                )
                            }

                            Spacer(
                                modifier = Modifier.height(14.dp)
                            )

                            Text(
                                text = "LUMINA",
                                color = textMain,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 2.sp
                            )

                            Spacer(
                                modifier = Modifier.height(6.dp)
                            )

                            Text(
                                text = "Preparing your movie",
                                color = textMuted,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )

                            Spacer(
                                modifier = Modifier.height(22.dp)
                            )

                            LinearProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp)
                                    .clip(
                                        RoundedCornerShape(50.dp)
                                    ),
                                color = brandPrimary,
                                trackColor = Color(0xFFEEF2FF)
                            )

                            Spacer(
                                modifier = Modifier.height(14.dp)
                            )

                            Text(
                                text = "Loading movie details...",
                                color = Color(0xFF94A3B8),
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailStatCard(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    title: String,
    subtitle: String
) {

    Surface(
        modifier = modifier,
        color = Color.White,
        shape = RoundedCornerShape(18.dp),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = Color(0xFFF1F5F9)
        ),
        shadowElevation = 3.dp
    ) {

        Column(
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 14.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            icon()

            Spacer(
                modifier = Modifier.height(7.dp)
            )

            Text(
                text = title,
                color = Color(0xFF0F172A),
                fontSize = 14.sp,
                fontWeight = FontWeight.Black
            )

            Text(
                text = subtitle,
                color = Color(0xFF94A3B8),
                fontSize = 9.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}


fun openTheBrowser(context: Context, url: String?) {
    if (url.isNullOrBlank()) return

    val intent = Intent(
        Intent.ACTION_VIEW,
        android.net.Uri.parse(url)
    )

    context.startActivity(intent)
}