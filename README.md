# ClimateNow

A modern Android weather application built using Clean Architecture, MVVM, Jetpack Compose, Hilt, Retrofit and Coroutines.

---

## Features

- Weather list screen
- Weather details screen
- Dynamic weather condition icons
- Parallel API calls using Coroutines
- StateFlow based UI state handling
- Modular architecture
- Dependency Injection using Hilt
- Retrofit API integration
- Jetpack Compose UI

---

## Tech Stack

- Kotlin
- Jetpack Compose
- MVVM Architecture
- Clean Architecture
- Multi-module Architecture
- Hilt
- Retrofit
- Coroutines
- StateFlow
- Open Meteo API

---

## Modules

### app
Contains:
- Navigation
- MainActivity
- App level setup

### domain
Contains:
- UseCases
- Repository interfaces
- Business logic

### data
Contains:
- Repository implementation
- DTOs
- Retrofit APIs
- Mappers

### core
Contains:
- Common utilities
- Network setup
- UI theme
- Reusable components

---

## Architecture

The project follows:
- Clean Architecture
- MVVM Pattern
- Single Responsibility Principle
- SOLID Principles

---

## API Used

Open Meteo APIs:
- Geocoding API
- Forecast API

---

## Screenshots

### Home Screen

![Home Screen](screenshots/home_screen.png)

### Details Screen

![Details Screen](screenshots/details_screen.png)

---

## Future Enhancements

- Pagination
- Offline caching
- Search functionality
- Unit testing
- Dark mode
- Better animations
- More detailed weather information
- Few other optimizations

---

## Author

Rital Naik