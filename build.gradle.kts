import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("com.android.library") version "4.1.0-alpha04"
  id("de.mannodermaus.android-junit5") version "1.6.0.1-SNAPSHOT"

  kotlin("android") version "1.3.70"
  kotlin("kapt") version "1.3.70"
}

android {
  compileSdkVersion(29)

  defaultConfig {
    compileOptions {
      setSourceCompatibility(JavaVersion.VERSION_1_8)
      setTargetCompatibility(JavaVersion.VERSION_1_8)
    }

    minSdkVersion(21)
    targetSdkVersion(29)
  }

  sourceSets {
    get("main").apply {
      java.srcDirs("src/main/kotlin")
    }

    get("test").apply {
      java.srcDirs("src/test/kotlin")
    }
  }
}

dependencies {
  implementation("androidx.fragment:fragment-ktx:1.2.4")
  implementation("androidx.lifecycle:lifecycle-common-java8:2.2.0")
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
  implementation("androidx.lifecycle:lifecycle-runtime:2.2.0")
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
  implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.71")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.5")

  testImplementation("com.github.ashdavies.lifecycle-testing:jupiter:0.5.3")
  testImplementation("com.github.ashdavies.lifecycle-testing:testing:0.5.3")
  testImplementation("com.github.ashdavies.lifecycle-testing:truth:0.5.3")
  testImplementation("com.google.truth:truth:1.0.1")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.4")
  testImplementation("org.junit.jupiter:junit-jupiter:5.6.1")
}

repositories {
  gradlePluginPortal()
  mavenCentral()
  google()

  maven("https://jitpack.io") {
    content {
      includeGroup("com.github.ashdavies.lifecycle-testing")
    }
  }
}

tasks.configureEach<Javadoc> {
  enabled = false
}

tasks.configureEach<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}

tasks.configureEach<Test> {
  testLogging.events("PASSED", "FAILED", "SKIPPED")
}
