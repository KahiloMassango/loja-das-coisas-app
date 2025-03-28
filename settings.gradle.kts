pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
    }
}

rootProject.name = "store"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:model")
include(":core:ui")
include(":core:database")
include(":core:network")
include(":core:data")

include(":features:home")
include(":features:discover")
include(":features:cart")
include(":features:userprofile")
include(":features:authentication")
include(":features:productdetail")
include(":features:checkout")
include(":features:search")
include(":features:store")
include(":features:newaddress")
include(":features:shop")
include(":core:datastore")
include(":features:orders")
include(":core:testing")
