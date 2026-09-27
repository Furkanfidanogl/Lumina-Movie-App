package com.example.cleanmovieapp.presentation.movies.views

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.cleanmovieapp.domain.model.Movie
import com.example.cleanmovieapp.presentation.movies.components.FeaturedBanner
import com.example.cleanmovieapp.presentation.movies.components.MovieCard
import com.example.cleanmovieapp.presentation.movies.viewmodel.MoviesViewModel
import com.example.cleanmovieapp.util.Constants
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(modifier: Modifier = Modifier, onMovieClick: (Int) -> Unit, viewModel: MoviesViewModel = koinViewModel()) {
    val uiState by viewModel.moviesUiState.collectAsState()

    var searchInput by rememberSaveable { mutableStateOf(uiState.searchQuery) }

    var isRefreshing by rememberSaveable { mutableStateOf(false) }

    /*
     * Pull refresh bittikten sonra
     * refresh indicator kapanır.
     */
    LaunchedEffect(uiState.isLoading) {
        if (!uiState.isLoading) {
            isRefreshing = false
        }
    }

    val focusManager = LocalFocusManager.current

    val screenBg = Color(0xFFFAFBFF)
    val brandPrimary = Color(0xFF4F46E5)
    val brandSecondary = Color(0xFF06B6D4)
    val textMain = Color(0xFF0F172A)
    val textMuted = Color(0xFF64748B)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = screenBg,
        topBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = screenBg.copy(alpha = 0.95f),
                shadowElevation = 0.dp
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 14.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "LUMINA",
                                color = textMain,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 2.sp
                            )
                        }

                        Text(
                            text = "Curated collections & charts",
                            color = textMuted,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .shadow(
                                elevation = 8.dp,
                                shape = RoundedCornerShape(12.dp),
                                spotColor = brandPrimary.copy(
                                    alpha = 0.25f
                                )
                            )
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        brandPrimary,
                                        brandSecondary
                                    )
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .pointerInput(Unit) {
                    detectTapGestures {
                        focusManager.clearFocus()
                    }
                }
        ) {

            PullToRefreshBox(
                /*
                 * ARTIK uiState.isLoading DEĞİL.
                 * Sadece gerçekten pull refresh yapıldığında true.
                 */
                isRefreshing = isRefreshing,

                onRefresh = {
                    isRefreshing = true

                    if (searchInput.isBlank()) {
                        viewModel.getMovies()
                    } else {
                        viewModel.getSearchMovies(searchInput)
                    }
                },

                modifier = Modifier
                    .fillMaxSize()
            ) {

                when {
                    uiState.isLoading && uiState.movies.isEmpty() && searchInput.isBlank() -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = brandPrimary,
                                strokeWidth = 3.dp
                            )
                        }
                    }

                    !uiState.errorMessage.isNullOrBlank() -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            Surface(
                                color = Color.White,
                                shape = RoundedCornerShape(20.dp),
                                shadowElevation = 4.dp,
                                border =
                                    androidx.compose.foundation.BorderStroke(
                                        width = 1.dp,
                                        color = Color(0xFFFEE2E2)
                                    )
                            ) {
                                Text(
                                    text = uiState.errorMessage ?: "Unknown error occurred",
                                    color = MaterialTheme.colorScheme.error,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(20.dp)
                                )
                            }
                        }
                    }

                    else -> {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(
                                start = 18.dp,
                                end = 18.dp,
                                top = 8.dp,
                                bottom = 28.dp
                            ),
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            /*
                             * 1. SEARCH BAR
                             */
                            item(
                                span = {
                                    GridItemSpan(2)
                                },
                                contentType = "search"
                            ) {

                                TextField(
                                    value = searchInput,
                                    onValueChange = {
                                        searchInput = it
                                        if (it.isBlank()) {
                                            viewModel.getMovies()
                                        } else {
                                            viewModel.getSearchMovies(it)
                                        }
                                    },

                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(56.dp)
                                        .shadow(
                                            elevation = 6.dp,
                                            shape = RoundedCornerShape(16.dp),
                                            spotColor = Color(0xFF0F172A).copy(alpha = 0.05f)
                                        ),

                                    placeholder = {
                                        Text(
                                            text = "Search movies...",
                                            color = Color(0xFF94A3B8),
                                            fontSize = 14.sp
                                        )
                                    },

                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            contentDescription = "Search",
                                            tint = brandPrimary
                                        )
                                    },

                                    singleLine = true,
                                    shape = RoundedCornerShape(16.dp),

                                    colors =
                                        TextFieldDefaults.colors(
                                            focusedContainerColor = Color.White,
                                            unfocusedContainerColor = Color.White,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                            cursorColor = brandPrimary
                                        )
                                )
                            }

                            /*
                             * 2. HERO
                             */
                            item(span = { GridItemSpan(2) }, contentType = "hero") {
                                FeaturedBanner()
                            }

                            /*
                             * 3. SECTION TITLE
                             */
                            item(span = { GridItemSpan(2) }, contentType = "contentType3") {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 8.dp,
                                            bottom = 2.dp
                                        ),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Column {
                                        Text(
                                            text = if (searchInput.isBlank()) {
                                                    "Popular Movies"
                                                } else {
                                                    "Search Results"
                                                },
                                            color = textMain,
                                            fontSize = 19.sp,
                                            fontWeight = FontWeight.Black,
                                            letterSpacing = (-0.3).sp
                                        )

                                        Text(
                                            text = if (searchInput.isBlank()) {
                                                    "Ranked by global community"
                                                } else {
                                                    "Results for \"$searchInput\""
                                                },
                                            color = textMuted,
                                            fontSize = 11.sp
                                        )
                                    }

                                    Surface(
                                        color = Color(0xFFEEF2FF),
                                        shape = RoundedCornerShape(12.dp)
                                    ) {

                                        Text(
                                            text = "${uiState.movies.size} Titles",
                                            color = brandPrimary,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier =
                                                Modifier.padding(
                                                    horizontal = 10.dp,
                                                    vertical = 5.dp
                                                )
                                        )
                                    }
                                }
                            }

                            /*
                             * 4. MOVIES
                             */
                            items(
                                items = uiState.movies,
                                key = { movie ->
                                    movie.id
                                }
                            ) { movie ->
                                MovieCard(
                                    movie = movie,
                                    onMovieClick = onMovieClick
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}