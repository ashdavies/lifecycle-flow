repositories {
  google() {
    content {
      includeGroupByRegex("androidx.*")
      includeGroupByRegex("com\\.android.*")
      includeGroupByRegex("com\\.google.*")
      includeGroupByRegex("zipflinger.*")
    }
  }

  maven("https://jcenter.bintray.com") {
    content {
      includeGroup("de.mannodermaus.gradle.plugins")
      includeGroup("org.jetbrains.trove4j")

      excludeGroupByRegex("androidx.*")
      excludeGroupByRegex("com\\.android.*")
      excludeGroupByRegex("com\\.google.*")
    }
  }

  maven("https://jitpack.io") {
    content {
      includeGroupByRegex("com\\.github\\.ashdavies.*")
    }
  }

  maven("https://repo.maven.apache.org/maven2")
}
