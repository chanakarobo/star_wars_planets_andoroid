### **Special Note**

This project implements modern Android development best practices:

- **Jetpack Components**
  - Built with Compose for declarative UI
  - Uses ViewModels for lifecycle-aware data management

- **Layered Architecture (Google Recommended)**
  - **UI Layer**: Composables and ViewModels
  - **Domain Layer**: Business logic and use cases
  - **Data Layer**: Repositories with local (Room) and remote data sources

- **Dependency Injection**
  - Implemented using Hilt for clean dependency management

- **Offline-First Implementation**
  - Automatic caching to local database on first launch
  - offline data access when network is unavailable

- **Comprehensive Testing**
  - Unit tests for data synchronization logic
  - Verification for data retrieval by ID (in planetDetailScreen)

- **Development Note**
  - I was unable to use the latest Gradle version for this project due to compatibility issues with my development environment that I couldn't resolve within the given timeframe so I used 7.5 instead of the latest one.
