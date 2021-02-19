buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Dependencies.BuildPlugins.android_gradle)
        classpath(Dependencies.BuildPlugins.kotlin_gradle_plugin)
        classpath(Dependencies.Hilt.android_plugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}