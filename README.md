# CRUD-Productos
 CRUD de productos realizado en SQLite en lenguaje Kotlin
 
---Dependencia que seran agregadas en Gradle---
apply plugin: 'kotlin-kapt'

def room_version = "2.2.5"

implementation "androidx.room:room-runtime:$room_version"
implementation "androidx.room:room-ktx:$room_version"
kapt "androidx.room:room-compiler:$room_version"
