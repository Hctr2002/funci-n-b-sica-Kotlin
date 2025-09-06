package org.example

suspend fun main() {

    var productos: List<Producto> = emptyList()
    var tipoCliente: String = ""

    while (true){
        println("¡Bienvenido al sistema de gestión de pedidos!")
        GestionPedidos.mostrarCatalogo()

        print("Seleccione los productos por su número separados por comas (ej. 1,3): ")
        val seleccion = readLine() ?.split(",")?.mapNotNull(){ it.trim().toIntOrNull()?.minus(1)} ?: emptyList()
        productos = seleccion.mapNotNull { index -> GestionPedidos.catalogo.getOrNull(index) }

        if (productos.isEmpty()) {
            println("No se seleccionaron productos válidos.")
        }else{
            break
        }
    }

    println("Ingrese su tipo de cliente (Regular, VIP, Premium):")
    tipoCliente = readLine() ?: ""
    tipoCliente.lowercase()


    GestionPedidos.procesarPedido(productos, tipoCliente)



}
