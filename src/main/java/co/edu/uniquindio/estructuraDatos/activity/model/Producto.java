package co.edu.uniquindio.estructuraDatos.activity.model;

import java.util.Objects;
import lombok.*;
@Getter
@Setter
public class Producto implements Comparable<Producto> {
    private Integer cantidad;
    private String codigo;
    private String nombre;
    private Double precio;

    public Producto() {
    }

    public Producto(Integer cantidad, String codigo, String nombre, Double precio) {
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    //-------------FUNCIONES PROPIAS---------------
    public boolean verificarCodigo(String codigo){
        return this.codigo.equals(codigo);
    }
    public boolean verificarCantidad(Integer cantidad) {
        return cantidad<=this.cantidad;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(codigo, producto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public int compareTo(Producto otroProducto) {
        return this.getCantidad().compareTo(otroProducto.getCantidad());
    }
}
