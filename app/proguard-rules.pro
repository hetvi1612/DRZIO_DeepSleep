# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

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

-keep class com.wang.avi.** { *; }
-keep class com.wang.avi.indicators.** { *; }
-dontwarn org.xmlpull.v1.**
-dontnote org.xmlpull.v1.**
-keep class org.xmlpull.** { *; }
-keep class javax.xml.** { *; }
-keep class org.xml.sax.** { *; }
#-dontwarn javax.xml.parsers.SAXParserFactory
-keep class org.apache.** { *; }

-dontwarn android.support.v7.**
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.* { *; }
-keepattributes *Annotation,Signature
-dontwarn com.github.siyamed.**
-keep class com.github.siyamed.shapeimageview.**{ *; }

-keep class com.github.javiersantos.appupdater.**{ *; }

-keep class com.ramotion.directselect.DSListView.**{ *; }


-keep class safe.data.app.getData {
   int getFacetCount*(...);
}
-dontwarn org.apache.commons.**
 -keep class org.apache.http.** { *; }
 -dontwarn org.apache.http.**


-keepattributes SourceFile,LineNumberTable
-keep class com.parse.*{ *; }
-dontwarn com.parse.**
-dontwarn com.squareup.picasso.**
-keepclasseswithmembernames class * {
    native <methods>;
}
#-keep public class <com.universe.aquariumPhotoFrames.**>
-keep class com.squareup.okhttp.** { *; }
-allowaccessmodification
-adaptresourcefilenames    **.properties,**.gif,**.jpg
-adaptresourcefilecontents **.properties,META-INF/MANIFEST.MF
-adaptresourcefilenames **.xsd,**.wsdl,**.xml,**.properties,**.gif,**.jpg,**.png
-adaptresourcefilecontents **.xml
-dontwarn com.google.ads.**
-keep class com.aviary.android.**
-dontwarn com.aviary.android.**
-dontnote com.aviary.android.**

-dontwarn it.sephiroth.**
-dontwarn **
-dontnote jp.co.**
-dontnote me.**
-keep class android.** { *; }
-keep interface com.aviary.** { *; }
-keep class com.aviary.** { *; }

-keepattributes *Annotation*
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}
-dontwarn com.alexvasilkov.**
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn rx.**


#-keep class videostatus.fullscreen.missyouvideostatus.** { *; }
#-keep interface videostatus.fullscreen.missyouvideostatus.* { *; }
#-keep public class videostatus.fullscreen.missyouvideostatus.retrofitClient.RetrofitClient
-keep public class videostatus.fullscreen.missyouvideostatus.utils.JZVideoPlayerNew

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Application classes that will be serialized/deserialized over Gson, keepclassmembers
-keep class com.myapp.model.** { *; }
#-keepclassmembers class videostatus.fullscreen.missyouvideostatus.models.** { *; }


-dontwarn android.support.v7.**
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.* { *; }
-keepattributes *Annotation,Signature
-dontwarn com.github.siyamed.**
-keep class com.github.siyamed.shapeimageview.**{ *; }

-keep class safe.data.app.getData {
   int getFacetCount*(...);
}
-dontwarn org.apache.commons.**
 -keep class org.apache.http.** { *; }
 -dontwarn org.apache.http.**


-keepattributes SourceFile,LineNumberTable
-keep class com.parse.*{ *; }
-dontwarn com.parse.**
-dontwarn com.squareup.picasso.**
-keepclasseswithmembernames class * {
    native <methods>;
}
#-keep public class <com.universe.aquariumPhotoFrames.**>
-keep class com.google.android.exoplayer2.** { *; }
-keep class cn.jzvd.** { *; }
-keep class com.squareup.okhttp.** { *; }
-repackageclasses ''
-allowaccessmodification
-adaptresourcefilenames    **.properties,**.gif,**.jpg
-adaptresourcefilecontents **.properties,META-INF/MANIFEST.MF
-adaptresourcefilenames **.xsd,**.wsdl,**.xml,**.properties,**.gif,**.jpg,**.png
-adaptresourcefilecontents **.xml
-dontwarn com.google.ads.**
-keep class com.aviary.android.**
-dontwarn com.aviary.android.**
-dontnote com.aviary.android.**

-dontwarn it.sephiroth.**
-dontwarn **
-dontnote jp.co.**
-dontnote me.**
-keep class android.** { *; }
-keep interface com.aviary.** { *; }
-keep class com.aviary.** { *; }

-keepattributes *Annotation*
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}
-dontwarn com.alexvasilkov.**
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn rx.**

-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn retrofit2.Platform$Java8

-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { <fields>; }

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}
-keep class drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication** { *; }
-keep class drzio.insomnia.treatment.bedtime.yoga.sleep.models.** { <fields>; }
-keep class drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.** { <fields>; }