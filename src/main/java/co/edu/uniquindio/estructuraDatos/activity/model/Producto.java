package co.edu.uniquindio.estructuraDatos.activity.model;

import java.util.Objects;

public class Producto implements Comparable<Producto> {
    private Integer cantidad;
    private String codigo;
    private String nombre;
    private Double precio;
    private Double subTotal;

    private Boolean existencia;

    public Producto() {
        this.precio = (double) 0;
    }

    public Producto(Integer cantidad, String codigo, String nombre, Double precio) {
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public void calcularSubtotal() {
        subTotal = precio*cantidad;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public void actualizarExistencia() {
        existencia = cantidad > 0;
    }

    public Boolean getExistencia() {
        return existencia;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        actualizarExistencia();
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

    public boolean verificarNombre(String nombre){
        return this.getNombre().toLowerCase().contains(nombre) && !nombre.equals(" ");
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
