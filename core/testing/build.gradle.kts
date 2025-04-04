plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.store.core.testing"
}

dependencies {

    api(projects.core.model)
    api(projects.core.data)
    api(projects.core.network)
    api(projects.core.database)
    api(projects.core.datastore)

    implementation(libs.junit)
    implementation(libs.kotlin.coroutines.test)
}