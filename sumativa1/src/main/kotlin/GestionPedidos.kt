package org.example

import kotlinx.coroutines.delay

object GestionPedidos {
    val catalogo = listOf(Comida("Hamburguesa Clásica", 8990.0, "Comida", 10.0, false),
        Comida("Salmón Grillado", 15990.0, "Comida", 15.0, true),
        Bebida("Coca-Cola (mediano)", 2000.0, "Bebida", 1.0, "mediano"),
        Bebida("Jugo Natural (grande)", 3000.0, "Bebida", 1.0, "grande"))

    fun mostrarCatalogo() {
        println("Catálogo de Productos:")
        catalogo.forEachIndexed { index, producto ->
            println("${index + 1}. ${producto.nombre} - Precio: \$${"%.0f".format(producto.calcularPrecio())} - Tiempo de Preparación: ${producto.tiempoPreparacion} mins")
        }
    }

    fun calcularDescuento(tipoCliente: String, total: Double): Double {
        return when (tipoCliente.lowercase()) {
            "regular" -> total * 0.95
            "vip" -> total * 0.9
            "premium" -> total * 0.85
            else -> total
        }
    }

    suspend fun procesarPedido(producto: List<Producto>, tipoCliente: String){
        println("Procesando pedido...")
        var estado: EstadoPedido = EstadoPedido.EnPreparacion
        println("Estado del pedido: $estado")

        delay(3000L)

        val subtotal = producto.sumOf { it.calcularPrecio() }
        val descuento = calcularDescuento(tipoCliente, subtotal)
        val iva = descuento * 0.19
        val total = descuento + iva

        estado = EstadoPedido.Listo

        println("=== RESUMEN DEL PEDIDO ===")
        producto.forEach { println("- ${it.nombre}: \$${"%.0f".format(it.calcularPrecio())}") }
        println("Subtotal: \$${"%.0f".format(subtotal)}")
        println("Descuento aplicado: \$${"%.2f".format( subtotal - descuento)}")
        println("IVA (19%): \$${"%.2f".format(iva)}")
        println("Total a pagar: \$${"%.2f".format(total)}")
        println("Estado del pedido: $estado")
    }
}
