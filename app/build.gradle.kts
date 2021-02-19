plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(Dependencies.Androidx.core_ktx)
    implementation(Dependencies.Androidx.appcompat)
    implementation(Dependencies.Androidx.constraint_layout)
    implementation(Dependencies.Androidx.navigation_ui_ktx)
    implementation(Dependencies.Androidx.navigation_fragment_ktx)
    implementation(Dependencies.Google.material)
    implementation(Dependencies.Kotlin.kotlin_std)
    implementation(Dependencies.Retrofit.core)
    implementation(Dependencies.Retrofit.converter_gson)
    implementation(platform(Dependencies.OkHttp.bom))
    implementation(Dependencies.OkHttp.core)
    implementation(Dependencies.OkHttp.logging_interceptor)
    implementation(Dependencies.Hilt.core)
    implementation(Dependencies.Hilt.navigation)
    kapt(Dependencies.Hilt.compiler)
}