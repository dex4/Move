plugins {
    kotlin("kapt")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
}

@Suppress("UnstableApiUsage")
android {
    namespace = "com.pose.move"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.pose.move"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.compose.material3:material3:1.0.0")

    implementation("androidx.core:core-splashscreen:1.0.0")

    val lifecycleVersion = "2.5.1"
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha03")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    val composeVersion = "1.3.0"
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")

    val navVersion = "2.5.3"
    implementation("androidx.navigation:navigation-compose:$navVersion")

    val accompanistVersion = "0.27.0"
    implementation("com.google.accompanist:accompanist-pager:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-pager-indicators:$accompanistVersion")

    val hiltVersion = "2.44"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:retrofit-mock:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

    val okHttpVersion = "4.10.0"
    implementation("com.squareup.okhttp3:okhttp:$okHttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHttpVersion")

    val moshiVersion = "1.14.0"
    implementation("com.squareup.moshi:moshi:$moshiVersion")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")
}

kapt {
    correctErrorTypes = true
}