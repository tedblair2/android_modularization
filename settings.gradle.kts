pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ModsApp"
include(":app")
include(":feature:home")
include(":core:view")
include(":core:network")
include(":core:local")
include(":data:blogRepository")
include(":feature:details")
