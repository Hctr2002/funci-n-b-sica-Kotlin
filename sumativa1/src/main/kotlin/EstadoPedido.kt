package org.example

sealed class EstadoPedido {
    object Pendiente : EstadoPedido(){
        override fun toString(): String {
            return "Pendiente"
        }
    }

    object EnPreparacion : EstadoPedido(){
        override fun toString(): String {
            return "En Preparación"
        }
    }

    object Listo : EstadoPedido(){
        override fun toString(): String {
            return "Listo"
        }
    }

    data class Error(val mensaje: String) : EstadoPedido(){
        override fun toString(): String {
            return "Error: $mensaje"
        }
    }
}
