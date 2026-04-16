package com.example.catalogoproductos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catalogoproductos.R
import com.example.catalogoproductos.adapters.ProductoAdapter
import com.example.catalogoproductos.databinding.FragmentInicioBinding
import com.example.catalogoproductos.models.Producto

class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.btnVerCatalogo.setOnClickListener {
            findNavController().navigate(R.id.catalogoFragment)
        }
        
        val productosDestacados = mutableListOf(
            Producto("Coco Chanel", "Chanel", 300000.0, R.drawable.chanel, "Sumérgete en la sofisticación pura.", listOf("femenino")),
            Producto("Boss Bottled", "Hugo Boss", 245000.0, R.drawable.boss, "Fragancia intensa y elegante.", listOf("masculino")),
            Producto("Dior Home", "Dior", 350000.0, R.drawable.dior, "Sofisticada y envolvente.", listOf("masculino")),
            Producto("Guess Seductive", "Guess", 250000.0, R.drawable.guess, "Juvenil y femenina.", listOf("femenino")),
            Producto("Blue Chanel", "Chanel", 480000.0, R.drawable.chanel, "Elegancia nocturna.", listOf("masculino"))
        )
        binding.rvDestacados.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvDestacados.adapter = ProductoAdapter(productosDestacados, layoutId = R.layout.item_producto_inicio)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

