package com.example.catalogoproductos.adapters

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.catalogoproductos.R
import com.example.catalogoproductos.models.Producto
import com.example.catalogoproductos.models.Carrito
import com.example.catalogoproductos.ui.DetalleFragment

class ProductoAdapter(
    private val lista: MutableList<Producto>,
    private val esCarrito: Boolean = false,
    private val layoutId: Int = R.layout.item_producto
) : RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.txtNombre)
        val marca: TextView = view.findViewById(R.id.txtMarca)
        val precio: TextView = view.findViewById(R.id.txtPrecio)
        val imagen: ImageView = view.findViewById(R.id.imgProducto)
        val boton: Button = view.findViewById(R.id.btnAgregar)
        val btnEliminar: Button = view.findViewById(R.id.btnEliminar)
        val btnFavorito: ImageView = view.findViewById(R.id.btnFavorito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        
        val view = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = lista[position]

        holder.nombre.text = producto.nombre
        holder.marca.text = producto.marca
        holder.precio.text = "$${producto.precio}"
        holder.imagen.setImageResource(producto.imagen)

        if (producto.esFavorito) {
            holder.btnFavorito.setImageResource(R.drawable.ic_favorite)
        } else {
            holder.btnFavorito.setImageResource(R.drawable.ic_favorite_border)
        }

        holder.btnFavorito.setOnClickListener {
            producto.esFavorito = !producto.esFavorito
            if (producto.esFavorito) {
                holder.btnFavorito.setImageResource(R.drawable.ic_favorite)
                Toast.makeText(holder.itemView.context, "Añadido a favoritos", Toast.LENGTH_SHORT).show()
            } else {
                holder.btnFavorito.setImageResource(R.drawable.ic_favorite_border)
            }
        }

        // Lógica de botones para Carrito vs Catálogo
        if (esCarrito) {
            holder.boton.text = "Comprar"
            holder.btnEliminar.visibility = View.VISIBLE
        } else {
            holder.boton.text = "Agregar"
            holder.btnEliminar.visibility = View.GONE
        }

        // Acción del botón principal (Agregar/Comprar)
        holder.boton.setOnClickListener {
            if (esCarrito) {
                Toast.makeText(holder.itemView.context, "Compra individual 🛒", Toast.LENGTH_SHORT).show()
            } else {
                Carrito.agregar(producto)
                Toast.makeText(holder.itemView.context, "Agregado al carrito", Toast.LENGTH_SHORT).show()
            }
        }

        // Acción de eliminar del carrito
        holder.btnEliminar.setOnClickListener {
            Carrito.eliminar(producto)
            lista.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, lista.size)
        }

        // Abrir detalle del producto
        holder.itemView.setOnClickListener {
            val bundle = Bundle().apply {
                putString("nombre", producto.nombre)
                putString("marca", producto.marca)
                putDouble("precio", producto.precio)
                putString("descripcion", producto.descripcion)
                putInt("imagen", producto.imagen)
            }

            val fragment = DetalleFragment()
            fragment.arguments = bundle

            val activity = holder.itemView.context as FragmentActivity
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
