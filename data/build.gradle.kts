plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.google.protobuf)
}

android {
    namespace = "com.example.currency.data"
    val compileSdkVersion: Int by rootProject.extra
    val appVersion: String by rootProject.extra
    val appId: String by rootProject.extra
    compileSdk = compileSdkVersion

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        val minSdkVersion: Int by rootProject.extra
        minSdk = minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "APPLICATION_ID", "\"${appId}\"")
        buildConfigField("String", "APP_VERSION", "\"${appVersion}\"")
    }

    compileOptions {
        val javaVersion: JavaVersion by rootProject.extra
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    kotlin {
        val jdk: String by rootProject.extra
        jvmToolchain(jdk.toInt())
    }

    kotlinOptions {
        val jdk: String by rootProject.extra
        jvmTarget = jdk
        freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.coroutines.core)
    implementation(libs.google.gson)
    implementation(libs.google.protobuf)
    implementation(libs.core.ktx)
    implementation(libs.hilt.work)
    implementation(libs.hilt.android)
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.preference.ktx)
    implementation(libs.lifecycle.process)
    implementation(libs.logging.timber)
    implementation(libs.bundles.room)
    kapt(libs.room.compiler)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)
    kapt(libs.autovalue)
    implementation(libs.autovalue.annotations)

    // Logging
    implementation(libs.logging.slf4j)
    implementation(libs.logging.logback)

    testImplementation(testlib.bundles.unit.test)
    testImplementation(testlib.truth.ext)
    testImplementation(testlib.test.core.ktx)
    testImplementation(libs.bundles.unit.test)
    testImplementation(platform(testlib.junit5.bom))
    testImplementation(testlib.bundles.junit5.api)
    testRuntimeOnly(testlib.junit.jupiter.engine)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.7"
    }

    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}
