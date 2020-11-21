package com.alkar.crud

import android.content.Intent
import android.icu.text.Transliterator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

//generacion de lista de productos de una base de datos
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listaProductos = emptyList<Producto>()

        val database = AppDatabase.getDatabase(this)

        //el observador se ejecuta cuando llega la informacion o se actualice
        database.productos().getAll().observe(this, Observer {
            //it contiene la lista de productos
            listaProductos = it

            val adapter = ProductosAdapter(this, listaProductos)

            lista.adapter = adapter
        })

        //para obtener la informacion del producto (interfaz activity_producto)
        lista.setOnItemClickListener { parent, view, position, id ->
            //llamada a la clase ProductoActivity a traves de "position"
            val intent = Intent(this, ProductoActivity::class.java)
            intent.putExtra("id", listaProductos[position].idProducto)
            startActivity(intent)
        }

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, NuevoProductoActivity::class.java)
            startActivity(intent)
        }
    }
}