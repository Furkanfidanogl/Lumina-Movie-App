# 🎬 Lumina Movie App

A modern Android movie discovery app built with **Kotlin** and **Jetpack Compose**, powered by the **TMDB API**.

Lumina lets users browse top-rated movies, search for titles, explore movie details, and visit official movie websites through a clean and modern interface.

---

## ✨ Features

- Browse top-rated movies
- Infinite scroll pagination
- Near-end page prefetching
- Search movies
- View detailed movie information
- Ratings, release dates, posters and backdrops
- Open official movie websites
- Pull-to-refresh
- Loading and error handling
- Animated scroll-to-top button
- Stable Lazy Grid item keys
- Duplicate-safe paginated movie results
- Type-safe navigation
- Route / Screen separation
- Modern Jetpack Compose UI

---

## 🏗 Architecture

Lumina follows **Clean Architecture** with **MVVM**, keeping the UI, business logic, and data layers separated.

Navigation destinations use a **Route / Screen** separation:

```text
NavHost
   ↓
Route
   ├── ViewModel
   ├── UiState
   └── Navigation callbacks
   ↓
Screen
   ↓
Reusable Components
```

The application data flow follows:

```text
Jetpack Compose UI
        ↓
ViewModel
        ↓
Use Case
        ↓
Repository Interface
        ↓
Repository Implementation
        ↓
Retrofit API
        ↓
TMDB
```

API responses are received as **DTOs**, mapped into **Domain Models**, and exposed to the UI through a **StateFlow-based UiState**.

---

## 📜 Pagination

Lumina supports incremental movie loading with TMDB's `page` query parameter.

The first page is loaded when the movie screen opens. As the user scrolls near the end of the grid, the next page is requested automatically and appended to the existing movie list.

```text
Page 1
  ↓
User scrolls near the end
  ↓
LazyGridState is observed with snapshotFlow
  ↓
loadNextPage()
  ↓
Page 2
  ↓
Existing movies + new movies
  ↓
Page 3...
```

Pagination state is managed inside `MoviesViewModel` with:

- `currentPage` — tracks the next page request
- `isLoadingMore` — prevents duplicate pagination requests
- `endReached` — stops pagination when no more results are returned

The UI observes `LazyGridState` with `snapshotFlow` and starts loading the next page before the user reaches the absolute end of the list.

A separate loading indicator is displayed at the bottom of the grid while another page is being fetched.

Paginated results are filtered by movie ID before being added to the UI list, preventing duplicate IDs from breaking stable Lazy Grid keys.

Pagination is disabled while search results are active, so top-rated movie pages are never appended to a search result list.

---

## ⬆️ Scroll to Top

After the user scrolls down the movie grid, an animated floating action button becomes available.

Pressing it uses `LazyGridState.animateScrollToItem(0)` to smoothly return to the beginning of the screen.

The button uses fade and scale animations when appearing and disappearing.

---

## 🛠 Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **Clean Architecture**
- **MVVM**
- **Koin**
- **Retrofit**
- **OkHttp**
- **Gson**
- **Kotlin Coroutines**
- **StateFlow**
- **Navigation Compose**
- **Kotlin Serialization**
- **Coil**
- **TMDB API**

---

## 📂 Project Structure

```text
com.example.cleanmovieapp
│
├── data
│   ├── remote
│   │   ├── dto
│   │   │   ├── MovieDetailDto
│   │   │   └── MoviesDto
│   │   └── MovieApi
│   │
│   └── repository
│       └── MovieRepositoryImpl
│
├── di
│   ├── module
│   └── MyApplication
│
├── domain
│   ├── model
│   │   ├── Movie
│   │   └── MovieDetail
│   │
│   ├── repository
│   │   └── MovieRepository
│   │
│   └── use_case
│       ├── GetMoviesUseCase
│       ├── GetMovieDetailUseCase
│       └── GetMovieSearchUseCase
│
├── presentation
│   ├── movie_detail
│   │   ├── viewmodel
│   │   └── views
│   │
│   ├── movies
│   │   ├── components
│   │   │   ├── FeaturedBanner
│   │   │   └── MovieCard
│   │   ├── viewmodel
│   │   └── views
│   │
│   ├── navigation
│   │   └── AppRoute
│   │
│   ├── ui
│   └── welcome
│       └── views
│
├── util
│   ├── Constants
│   └── Resource
│
└── MainActivity
```

---

## 🔑 API Configuration

Lumina uses the **TMDB API** for movie data.

Add your TMDB access token to the project's `local.properties` file:

```properties
TMDB_ACCESS_TOKEN=YOUR_TMDB_ACCESS_TOKEN
```

The token is excluded from version control through `.gitignore`.

---

## 📱 Screenshots

<p align="center">
  <img src="./screenshots/welcome.png" width="220" />
  <img src="./screenshots/movies.png" width="220" />
</p>

<p align="center">
  <img src="./screenshots/search.png" width="220" />
  <img src="./screenshots/detail.png" width="220" />
</p>
