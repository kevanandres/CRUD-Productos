# CRUD-Productos
Universidad de las Americas
Kevin Cárdenas.
Seminario de Sistemas.
Aplicacion 4 - Uso de SQLite.
La siguiente aplicacion utiliza los CRUD de productos realizado en SQLite utilizando workbench en lenguaje Kotlin. 

Link de github: https://github.com/kevanandres/CRUD-Productos

Link de descarga IntelliJ IDEA: https://www.jetbrains.com/es-es/idea/download/#section=windows

Link de descarga Android Studio: https://developer.android.com/studio

Link de descarga Workbench: https://dev.mysql.com/downloads/workbench/

CRUD de productos realizado en SQLite en lenguaje Kotlin
 
---Dependencia que seran agregadas en Gradle---

apply plugin: 'kotlin-kapt'

def room_version = "2.2.5"

implementation "androidx.room:room-runtime:$room_version"
implementation "androidx.room:room-ktx:$room_version"
kapt "androidx.room:room-compiler:$room_version"
