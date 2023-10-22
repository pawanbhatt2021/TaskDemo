plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.example.taskdemo"
    compileSdk = 34
    viewBinding{
        enable=true
    }
    dataBinding{
        enable=true
    }
    defaultConfig {
        applicationId = "com.example.taskdemo"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    val roomVersion = "2.5.2"
    val lifecycleVersion = "2.6.2"
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("com.google.android.gms:play-services-maps:18.1.0@aar")
    implementation("com.google.android.gms:play-services-auth-api-phone:18.0.1")

    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    //viewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.activity:activity-ktx:1.8.0")
    // Retrofit for making network requests
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Moshi for JSON parsing
    implementation("com.squareup.moshi:moshi:1.15.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    //responsive
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")
    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    //Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-compiler:2.48.1")
    //Circular image
    implementation("de.hdodenhof:circleimageview:3.1.0")
    //navigation
    implementation("com.github.ismaeldivita:chip-navigation-bar:1.4.0")

    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")

    //Room
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion") // To use Kotlin Symbol Processing (KSP)
    testImplementation("androidx.room:room-testing:$roomVersion") // optional - Test helpers
    implementation("androidx.room:room-ktx:$roomVersion") // optional - Kotlin Extensions and Coroutines support for Room

    //Lifecycle Components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion") // ViewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion") // LiveData
}
kapt {
    correctErrorTypes=true
}