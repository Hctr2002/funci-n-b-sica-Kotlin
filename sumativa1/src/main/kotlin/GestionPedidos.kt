package org.example

import kotlin.coroutines.delay

object GestionPedidos {
    val catalogo = listOf(Comida("Hamburguesa", 5.0, "Comida Rápida", 10.0, true),
        Comida("Pizza", 8.0, "Comida Rápida", 15.0, false),
        Comida("Ensalada César", 7.0, "Ensaladas", 5.0, true),
        Bebida("Coca-Cola", 2.0, "Refresco", 1.0, "mediano"),
        Bebida("Agua Mineral", 1.5, "Agua", 1.0, "pequeño"),
        Bebida("Cerveza", 3.0, "Alcohol", 1.0, "grande"))

    fun mostrarCatalogo() {
        println("Catálogo de Productos:")
        catalogo.forEachIndexed { index, producto ->
            println("${index + 1}. ${producto.nombre} - ${producto.categoria} - Precio: \$${"%.2f".format(producto.calcularPrecio())} - Tiempo de Preparación: ${producto.tiempoPreparacion} mins")
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
        producto.forEach { println("- ${it.nombre}: \$${"%.2f".format(it.calcularPrecio())}") }
        println("Subtotal: \$${"%.2f".format(subtotal)}")
        println("Descuento aplicado: \$${"%.2f".format(descuento)}")
        println("IVA (19%): \$${"%.2f".format(iva)}")
        println("Total a pagar: \$${"%.2f".format(total)}")
        println("Estado del pedido: $estado")
    }
}
