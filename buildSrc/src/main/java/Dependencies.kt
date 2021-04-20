object Versions {
    val kotlin = "1.3.72"
    val compile_sdk = 30
    val target_sdk = 30
    val min_sdk_version = 21
    val build_tools_version = "30.0.2"
    val android_build_tools = "4.0.1"
    val version_code = 1
    val version_name = "1.0"
    val core_ktx = "1.3.2"

    val navigation = "2.3.3"

    // test libs
    val junit = "4.12"
    val junit_ext = "1.1.1"
    val espresso_core = "3.2.0"

    // Network
    val retrofit = "2.9.0"
    val okHttp = "3.12.1"
    val moshiVersion = "1.11.0"
    val logginInterceptor = "4.7.2"
    val retrofitMoshi = "2.6.2"


    val coroutines = "1.3.7"
    val constraint_layout = "2.0.4"
    val androidx_app_compact = "1.2.0"
    val hilt_version = "2.31.2-alpha"
    val hilt_jetpack_version = "1.0.0-alpha02"
    val lifecycle_version = "2.2.0"
    val fragment_ktx = "1.2.5"

    val vector_drawable = "1.1.0"

    val recycler_view = "1.1.0"
    val material_version = "1.2.1"

    val chart = "v3.1.0-alpha"

    val lifecycle_ext = "2.1.1"
    val room_version = "2.2.5"
    val legacy_support = "1.0.0"
    val glide_version = "4.11.0"
    val mockito_version = "2.27.0"
    val androidx_core_test = "2.1.0"
    val fragment_version = "1.3.0-alpha06"
    val dex_maker_version = "2.12.1"
}

object Deps {

    val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx}"
    val material_design = "com.google.android.material:material:${Versions.material_version}"
    val recycler_view = "androidx.recyclerview:recyclerview:${Versions.recycler_view}"
    val lifecycle_extensions = "android.arch.lifecycle:extensions:${Versions.lifecycle_ext}"

    // Glide
    val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"
    val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide_version}"

    // retrofit
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofitMoshi}"
    val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logginInterceptor}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"

    // moshi
    val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshiVersion}"
    val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshiVersion}"

    val mpAndroid_chart = "com.github.PhilJay:MPAndroidChart:${Versions.chart}"

    val room = "androidx.room:room-runtime:${Versions.room_version}"
    val room_ktx = "androidx.room:room-ktx:${Versions.room_version}"
    val room_compiler = "androidx.room:room-compiler:${Versions.room_version}"

    val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val android_build_tools = "com.android.tools.build:gradle:${Versions.android_build_tools}"
    val androidx_core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    val androidx_legacy_support = "androidx.legacy:legacy-support-v4:${Versions.legacy_support}"
    val constraint_layout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    val androidx_app_compact = "androidx.appcompat:appcompat:${Versions.androidx_app_compact}"
    val vector_drawable = "androidx.vectordrawable:vectordrawable:${Versions.vector_drawable}"
    val kotlin_coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    val viewmodel_ktx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    val livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"

    // DAGGER HILT
    val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt_version}"
    val hilt_android_compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt_version}"
    val hilt_plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt_version}"
    val hilt_jetpack_version = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_jetpack_version}"
    val hilt_jetpack_version_compiler = "androidx.hilt:hilt-compiler:${Versions.hilt_jetpack_version}"

    // NAVIGATION
    val nav_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val nav_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    // TEST
    val junit = "junit:junit:${Versions.junit}"
    val junit_ext = "androidx.test.ext:junit:${Versions.junit_ext}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"

}
