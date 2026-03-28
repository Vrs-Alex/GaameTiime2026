plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.apollo)
}

android {
    namespace = "com.vrsalex.gaametiime"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.vrsalex.gaametiime"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }


}

apollo {
    service("service") {
        packageName.set("com.vrsalex.gaametiime")
        generateKotlinModels.set(true)

        introspection {
            endpointUrl.set("https://api.github.com/graphql")
            schemaFile.set(file("src/main/graphql/com/vrsalex/gaametiime/schema.graphqls"))
            headers.put("Authorization", "Bearer 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")
        }
    }
}

dependencies {

    implementation(files("libs/uikit-release.aar"))

    // Datastore & database
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Network
    implementation(libs.apollo.runtime)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.auth)
    implementation(libs.ktor.client.websockets)
    implementation(libs.ktor.client.logging)

    // DI
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Other
    implementation(libs.kotlinx.serialization.json)

    // Compose UI
    implementation(libs.coil.compose)
    implementation(libs.coil.network) // Нужно для работы с URL
    implementation(libs.coil.svg)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.vico.compose)
    implementation(libs.vico.compose.m3)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    testImplementation(libs.junit)
    // Ассерты и Моки
    testImplementation(libs.truth)
    testImplementation(libs.mockk)
    // Корутины и Flow
    testImplementation(libs.coroutines.test)
    testImplementation(libs.turbine)
    // Сетевые моки
    testImplementation(libs.ktor.client.mock)
    testImplementation(libs.apollo.testing.support)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}