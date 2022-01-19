# affirm_take_home

### Note: 
There was a major decision before starting to work on the project that was to start doing maintenance (remove deprecated code, update code, etc..) and upgrade the API version to API 32 all these maintenance tasks that I did into the project are in the __App Maintenance & Enhancements__ sections at the end of this file.

# Solution description

The application was implemented following the next paratemers 

- **Workflow schema**: Gitflow (you can see the evolution of the app in the PR section of this repo)
- **Architectural Pattern :**  Clean arquitecture
- **UI - View Design Patterns** MVVM - Model-View-ViewModel
- **View Handling :** Reactive views using view states on sealed interfaces
- **MultiThreading handling :** Coroutines
- **Dependency Injection :** Dagger - HILT
  - Injectors for coroutine dipatchers
  - Injectors for use cases
  - Injectors for Repositories 
- **Network Stack :** Retrofit with suspended functions / kotlinx serialization
- **Jetpack Libraries / Components**:
   - ViewModel
   - LiveData
   - ViewPager2
   - Lifecycle
   - Databinding
   - Hilt
  
- **Layers / folder structure:**

(Every layer was build thinking on feature folder structure, because we'll need to split into several modules if need to scale on different teams)

## UI
![image](https://user-images.githubusercontent.com/456256/150107424-9ba2b00a-0bb4-4349-ba10-1a7447b8607d.png)
  - ui : ui related components
     - feature folder name IE. dashboard 
        - activities
        - viewmodels
        - fragments
        - adapters
        - viewstates
 
 ## Domain
![image](https://user-images.githubusercontent.com/456256/150107644-369fd2e1-195c-4796-a065-ed22e2f1a1c0.png)

  - domain:

## Data
![image](https://user-images.githubusercontent.com/456256/150108917-0988f9a8-624e-4719-a686-4110519f149a.png)

  - data:

## Utils
![image](https://user-images.githubusercontent.com/456256/150109021-3e1363df-7663-4273-9e15-fb970f58df15.png)

  - utils:

## Changelog

### App Maintenance

 - Migrated to gradle 7.3.3 and gradle tools plugin 7.0.2 for latest compatibility
 - Upgraded kotlin version to 1.6.10
 - removed plugin 'kotlin-android-extensions' it is deprecated related with the android 11 migration and errors with kotlinx serialization and parcelize  plugins
upgraded to latest target version 32 
 	- Added Data binding Support into the project

## Enhancements

- Upgrade to api version 32
- Added Support for JAVA 11 
- Changes on Manifest according to api 31 exported tag enforcement
- Added support for dependency injection using Dagger HILT
- Added  network interceptor
- Added Support for Google Jetpack Components
- Added Support for Retrofit
- Added Support for Kotlin Coroutines
