# US Election Info App

This app demonstrates the following views and techniques:

* [Retrofit](https://square.github.io/retrofit/) to make api calls to an HTTP web service.
* [Moshi](https://github.com/square/moshi) which handles the deserialization of the returned JSON to Kotlin data objects.
* [Glide](https://bumptech.github.io/glide/) to load and cache images by URL.
* [Room](https://developer.android.com/training/data-storage/room) for local database storage.

It leverages the following components from the Jetpack library:

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [Data Binding](https://developer.android.com/topic/libraries/data-binding/) with binding adapters
* [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) with the SafeArgs plugin for parameter passing between fragments

## Requirements
- Android Studio Flamingo or later
- You need add your **API_KEY** in [CivicsHttpClient.kt](https://github.com/vinchamp77/Android_NanoDegree_USElectionInfo/blob/master/app/src/main/java/com/androidcafe/uselectioninfo/remote/CivicsApiInstance.kt) in order to run the code sucessfully.

## How to Get the API Key?


## Tech Stack
- Android View
- MVVM Architecture
- Motion Layout
- Retrofit
- Glide
- Room Database
- Location
- Coroutine & Live Data

