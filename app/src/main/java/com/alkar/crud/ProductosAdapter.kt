package com.alkar.crud

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_producto.view.*

class ProductosAdapter(private val mContext: Context, private val listaProductos: List<Producto>) : ArrayAdapter<Producto>(mContext, 0, listaProductos) {
    //sobrecribir la funcion
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //instanciar el layout item_producto
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_producto, parent, false)
        //instanciar hacia la lista de productos
        val producto = listaProductos[position]
        //informacion de productos que se mostrara en (activity_producto)
        layout.nombre.text = producto.nombre
        layout.precio.text = "$${producto.precio}"
        val imageUri = ImageController.getImageUri(mContext,producto.idProducto.toLong())

        layout.imageView.setImageURI(imageUri)

        return layout
    }

}