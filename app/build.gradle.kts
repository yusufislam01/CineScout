plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.yusufislamaltunkaynak.cinescout"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.yusufislamaltunkaynak.cinescout"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(platform("androidx.compose:compose-bom:2025.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")  // Material3 bileşenleri
    implementation("androidx.activity:activity-compose:1.10.1")  // Compose için Activity
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5") // ViewModel – Compose entegrasyonu
    implementation("androidx.navigation:navigation-compose:2.8.9")  // Navigation Compose (2.8.9, Mart 2025’te yayınlandı:contentReference[oaicite:1]{index=1})
    // Retrofit 3.0 (Mayıs 2025’te yayınlandı):contentReference[oaicite:2]{index=2}
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")

    // Room 2.7.2 (Haziran 2025, en son kararlı sürüm):contentReference[oaicite:3]{index=3}
    implementation("androidx.room:room-runtime:2.7.2")
    ksp("androidx.room:room-compiler:2.7.2")
    implementation("androidx.room:room-ktx:2.7.2")

    // Hilt (Dagger Hilt) 2.57.1 (Ağustos 2025):contentReference[oaicite:4]{index=4}
    implementation("com.google.dagger:hilt-android:2.57.1")
    ksp("com.google.dagger:hilt-android-compiler:2.57.1")

    // Hilt Navigation Compose (v1.2.0)
    implementation(libs.androidx.hilt.navigation.compose)

    // Coroutines (en son 1.10.2, Nisan 2025 itibariyle):contentReference[oaicite:5]{index=5}
    implementation(libs.kotlinx.coroutines.android)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


}