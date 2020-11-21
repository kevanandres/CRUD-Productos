package com.alkar.crud

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//si la instancia existe, regresa la instancia; caso contrario la crea
//funcion abstracta.
//declaracion de clases, la version dependera de la base de datos
@Database(entities = [Producto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    //llamada a la clase DAO
    abstract fun productos(): ProductosDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            //nombre de la base de datos
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "producto"
                ).build()

                INSTANCE = instance

                return instance
            }
        }
    }
}