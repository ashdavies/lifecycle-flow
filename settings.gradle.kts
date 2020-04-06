pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
    google()

    maven("https://oss.sonatype.org/content/repositories/snapshots") {
      content {
        includeModule("de.mannodermaus.gradle.plugins", "android-junit5")
      }
    }
  }

  resolutionStrategy {
    eachPlugin {
      if (requested.id.id == "org.jetbrains.kotlin.jvm") {
        useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
      }
      if (requested.id.id == "com.android.library") {
        useModule("com.android.tools.build:gradle:${requested.version}")
      }
      if (requested.id.id == "de.mannodermaus.android-junit5") {
        useModule("de.mannodermaus.gradle.plugins:android-junit5:${requested.version}")
      }
    }
  }
}
