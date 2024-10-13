plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.adminpanelfoodieworld"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.adminpanelfoodieworld"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures{
        viewBinding =true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation ("com.intuit.ssp:ssp-android:1.1.0")
    implementation ("com.intuit.sdp:sdp-android:1.1.0")

//        // Import the BoM for the Firebase platform
//        implementation(platform("com.google.firebase:firebase-bom:33.4.0"))
//
//        // Add the dependency for the Firebase Authentication library
//        // When using the BoM, you don't specify versions in Firebase library dependencies
//        implementation("com.google.firebase:firebase-auth")
//
//        // Also add the dependency for the Google Play services library and specify its version
//        implementation("com.google.android.gms:play-services-auth:21.2.0")


        implementation ("com.google.android.gms:play-services-auth:19.2.0")




}