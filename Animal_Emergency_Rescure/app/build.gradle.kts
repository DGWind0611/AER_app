plugins {
    id("com.android.application")
    id("com.google.gms.google-services") // Google services plugin
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") // Secrets plugin for API keys
}

android {
    namespace = "com.fcu.android.animal_emergency_rescure"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fcu.android.animal_emergency_rescure"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        vectorDrawables.useSupportLibrary = true
    }
}

dependencies {
    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    // Add the dependencies for Firebase products you want to use
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-database")
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")
    // google-maps
    implementation("com.google.android.gms:play-services-maps:18.0.2")
    implementation("com.android.volley:volley:1.2.1")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("com.google.android.gms:play-services-places:17.0.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
