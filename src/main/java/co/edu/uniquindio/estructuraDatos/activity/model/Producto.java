package co.edu.uniquindio.estructuraDatos.activity.model;

import java.util.Objects;

public class Producto {
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
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
}
