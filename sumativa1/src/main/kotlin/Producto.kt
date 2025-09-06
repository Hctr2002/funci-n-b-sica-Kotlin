package org.example

open class Producto(var nombre:String = "",
                    var precio: Double = 0.0,
                    var categoria:String = "",
                    var tiempoPreparacion:Double = 0.0) {
    open fun calcularPrecio():Double = precio
}

class Bebida(nombre: String, precio: Double, categoria: String, tiempoPreparacion: Double, var tamanio: String = ""):
    Producto(nombre, precio, categoria, tiempoPreparacion){
    override fun calcularPrecio(): Double {
        return when (tamanio.lowercase()) {
            "pequeño" -> precio * 1.0
            "mediano" -> precio * 1.15
            "grande" -> precio * 1.3
            else -> precio
        }
    }
}

class Comida(nombre: String, precio: Double, categoria: String, tiempoPreparacion: Double, var premium: Boolean = false):
        Producto(nombre, precio, categoria, tiempoPreparacion){
    override fun calcularPrecio(): Double {
        if (premium) precio *= 1.2 else precio
        return precio
    }
}
