object Dependencies{

    object Kotlin{
        const val kotlin_std = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    }

    object BuildPlugins {
        const val android_gradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    }

    object Androidx {
        const val core_ktx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    }

    object Google {
        const val material = "com.google.android.material:material:${Versions.material}"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    object OkHttp {
        const val bom = "com.squareup.okhttp3:okhttp-bom:${Versions.okhttp}"
        const val core = "com.squareup.okhttp3:okhttp"
        const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor"
    }

    object Hilt {
        const val android_plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
        const val core = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        const val navigation = "androidx.hilt:hilt-navigation-fragment:${Versions.hilt_navigation}"
    }

}