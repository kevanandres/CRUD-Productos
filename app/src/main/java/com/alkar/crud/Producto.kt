package com.alkar.crud

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//toda la informacion que se va a almacenar en la tabla, con el nombre de la tabla
//interfaz entre la entidad y la base de datos
@Entity(tableName = "productos")
class Producto (
    val nombre:String,
    val precio:Double,
    val descripcion:String,
    val imagen:Int,
    @PrimaryKey(autoGenerate = true)
    var idProducto: Int=0
):Serializable