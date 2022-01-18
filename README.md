# affirm_take_home

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
