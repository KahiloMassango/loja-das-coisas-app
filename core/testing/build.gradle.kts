plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.example.store.core.testing"
}

dependencies {

    implementation(projects.core.model)
    implementation(projects.core.data)
    implementation(libs.kotlin.coroutines.test)
}