# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/igor/Android/Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-printmapping mapping.txt
-keep class com.ikholopov.yamblz.weather.weathermobilization.data.dto.** { *; }
-keep class com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeather { *; }
-keep class com.ikholopov.yamblz.weather.weathermobilization.data.CityInfo { *; }
-keep class com.ikholopov.yamblz.weather.weathermobilization.data.CityAutoComplete { *; }

# Retrofit 2.X
## https://square.github.io/retrofit/ ##

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

-dontwarn okio.**
-dontwarn javax.annotation.**
