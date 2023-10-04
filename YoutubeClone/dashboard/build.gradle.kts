plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.youtube.dashboard"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(project(":common:common_utils"))
    implementation(Deps.core)
    implementation(Deps.appCompat)
    implementation(Deps.androidMaterial)
    implementation(Deps.constraintLayout)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.gsonConvertor)
    implementation(Coroutines.coroutineCore)
    implementation(Coroutines.coroutineAndroid)
    implementation(CoroutinesLifecycleScope.lifecycleViewModel)
    implementation(CoroutinesLifecycleScope.lifecycleLiveData)
    implementation(CoroutinesLifecycleScope.lifeCycleRuntime)
    implementation(ViewModelDelegate.viewModelDeligate)



    implementation(DaggerHilt.hilt)
    kapt(DaggerHilt.hiltAndroidCompiler)
    kapt(DaggerHilt.hiltCompiler)



    testImplementation(TestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.espresso)
}