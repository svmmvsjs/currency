plugins {
    id("kotlin")
    id("com.android.lint")
    id("kotlin-kapt")
}

val service = project.extensions.getByType<JavaToolchainService>()

val customLauncher = service.launcherFor {
    val jdk: String by rootProject.extra
    languageVersion.set(JavaLanguageVersion.of(jdk.toInt()))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        val jdk: String by rootProject.extra
        jvmTarget = jdk
        freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
    }
    kotlinJavaToolchain.toolchain.use(customLauncher)
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(libs.javax.inject)
    implementation(libs.coroutines.core)

    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.core)

    testImplementation(libs.junit)
    testImplementation(testlib.bundles.unit.test)
    testImplementation(libs.bundles.unit.test)
    testImplementation(platform(testlib.junit5.bom))
    testImplementation(testlib.bundles.junit5.api)
    testRuntimeOnly(testlib.junit.jupiter.engine)
}
