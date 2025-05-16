import org.gradle.kotlin.dsl.implementation

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kapt)
    alias(libs.plugins.ktLint)
    alias(libs.plugins.scabbard)
}

// required for scabbard https://github.com/arunkumar9t2/scabbard/issues/92#issuecomment-901107022
configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "com.github.kittinunf.result" && requested.name == "result" && requested.version == "3.0.0") {
            useVersion("3.0.1")
            because("Transitive dependency of Scabbard, currently not available on mavenCentral()")
        }
    }
}

scabbard {
    enabled = true
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(libs.dagger)
    implementation(libs.okHttp)
    kapt(libs.dagger.compiler)
}
