apply(from = "${rootProject.projectDir}/../repositories.gradle.kts")

buildscript {
  repositories {
    google()
  }

  dependencies {
    classpath("com.android.tools.build:gradle:3.6.0-beta01")
  }
}

plugins {
  `kotlin-dsl`
}

dependencies {
  implementation("com.android.tools.build:gradle:3.6.0-beta01")
}
