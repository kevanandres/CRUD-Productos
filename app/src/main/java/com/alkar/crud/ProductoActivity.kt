package com.alkar.crud

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_producto.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductoActivity : AppCompatActivity() {
    //aaceso a la base de datos y producto
    private lateinit var database: AppDatabase
    private lateinit var producto: Producto
    //observable
    private lateinit var productoLiveData: LiveData<Producto>
    private val EDIT_ACTIVITY = 49

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)

        database = AppDatabase.getDatabase(this)

        //recibe el id del producto seleccionado
        val idProducto = intent.getIntExtra("id", 0)

        val imageUri = ImageController.getImageUri(this,idProducto.toLong())
        imagen.setImageURI(imageUri)

        //nos otorga un producto con el id enviado con el Observer
        productoLiveData = database.productos().get(idProducto)

        productoLiveData.observe(this, Observer {
            producto = it

            nombre_producto.text = producto.nombre
            precio_producto.text = "$${producto.precio}"
            detalles_producto.text = producto.descripcion

        })
    }

    //con esta funcion el menu creado que pone en el menu por defecto
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.producto_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    //funcion para realizar las funciones de editar y eliminar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit_item -> {
                val intent = Intent(this, NuevoProductoActivity::class.java)
                intent.putExtra("producto", producto)
                startActivityForResult(intent,EDIT_ACTIVITY)
            }

            R.id.delete_item -> {
                productoLiveData.removeObservers(this)

                CoroutineScope(Dispatchers.IO).launch {
                    database.productos().delete(producto)
                    ImageController.deleteImage(this@ProductoActivity,producto.idProducto.toLong())
                    this@ProductoActivity.finish()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    //imagen
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when{
            requestCode == EDIT_ACTIVITY && resultCode == Activity.RESULT_OK -> {
                imagen.setImageURI(data!!.data)
            }
        }
    }
}