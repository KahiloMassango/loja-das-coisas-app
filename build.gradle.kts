import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.android.library) apply false
}

fun BaseExtension.defaultConfig() {

    compileSdkVersion(34)

    defaultConfig {
        minSdk = 28
        targetSdk = 34
        consumerProguardFiles("consumer-rules.pro")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }


    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

fun PluginContainer.applyDefaultConfig(project: Project) {
    whenPluginAdded {
        when (this) {
            is AppPlugin -> {
                project.extensions
                    .getByType<AppExtension>()
                    .apply {
                        defaultConfig()
                    }
            }

            is LibraryPlugin -> {
                project.extensions
                    .getByType<LibraryExtension>()
                    .apply {
                        defaultConfig()
                    }
            }

            is JavaPlugin -> {
                project.extensions.getByType<JavaPluginExtension>()
                    .apply {
                        sourceCompatibility = JavaVersion.VERSION_21
                        targetCompatibility = JavaVersion.VERSION_21
                    }
            }
        }
    }
}

subprojects {
    project.plugins.applyDefaultConfig(project)

}