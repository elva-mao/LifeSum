<h1 align="center">LifeSum</h1>

<p align="center">  
🗡️ LifeSum is a demo to display nutrition info of food items, you can refresh a food item info by shaking your smartphone. It demonstrates modern Android development with Hilt, Coroutines, Flow, Jetpack (ViewModel), and Material Design based on MVVM architecture.
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 23
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- Jetpack
  - Lifecycle - Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel - Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - DataBinding - Binds UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
 
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository Pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.

## Architecture
LifeSum demo is based on the MVVM architecture and the Repository pattern.

## Open API
LifeSum demo using the [LifeSumAPI](https://api.lifesum.com) for constructing RESTful API.<br>

## todo
- polish ui
- data persistence
- more pages
