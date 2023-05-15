import com.android.utils.Environment
import org.gradle.initialization.Environment.Properties
import org.gradle.internal.impldep.org.apache.ivy.util.PropertiesFile
import org.jetbrains.kotlin.gradle.utils.loadPropertyFromResources
import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}




android {

    val apikeyProperties = loadProperties("local.properties")

    buildFeatures {
        buildConfig = true
    }

    namespace = "com.paulo.mymovie"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.paulo.mymovie"
        minSdk = 25
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "API_KEY", apikeyProperties["api"] as String)
        buildConfigField("String", "BASE_URL", apikeyProperties["base_url"] as String)
        buildConfigField("String", "BASE_IMAGE", apikeyProperties["base_image"] as String)
        buildConfigField("String", "W400H660", apikeyProperties["w440_and_h660_face"] as String)
        buildConfigField("String", "TOKEN", apikeyProperties["token"] as String)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation(platform("androidx.compose:compose-bom:2023.05.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    //DI
    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.test:core-ktx:1.5.0")
    implementation("com.google.firebase:firebase-dynamic-links-ktx:21.1.0")
    kapt("com.google.dagger:hilt-compiler:2.44")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //SPLASH
    implementation("androidx.core:core-splashscreen:1.0.0-beta01")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.6.0-beta01")

    // Pager and Indicators - Accompanist
    implementation("com.google.accompanist:accompanist-pager:0.27.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.27.0")

    // DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //SYSTEM UI
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.31.1-alpha")

    //COIL
    implementation("io.coil-kt:coil-compose:2.3.0")

    //CONSTRAINT
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha03")

    //RETROFIT
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //ROOM
    implementation ("androidx.room:room-runtime:2.5.0")
    implementation ("androidx.room:room-ktx:2.5.0")
    annotationProcessor ("androidx.room:room-compiler:2.5.0")
    kapt ("androidx.room:room-compiler:2.5.0")

    //PLAYER
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0")

    //VECTOR
    implementation( "io.coil-kt:coil-compose:2.1.0")
    implementation("io.coil-kt:coil-gif:2.1.0")

    //TESTS
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.05.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    testImplementation("com.squareup.okhttp3:mockwebserver:4.10.0")

    testImplementation("org.mockito:mockito-core:3.6.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

    androidTestImplementation("com.google.truth:truth:1.0.1")
    androidTestImplementation("android.arch.core:core-testing:1.0.0")
}