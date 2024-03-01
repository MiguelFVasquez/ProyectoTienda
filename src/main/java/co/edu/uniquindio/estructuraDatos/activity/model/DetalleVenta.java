package co.edu.uniquindio.estructuraDatos.activity.model;

import java.util.concurrent.ThreadPoolExecutor;

public class DetalleVenta {

    private String nombre;
    private Integer cantidad;
    private Double subTotal;

    public DetalleVenta() {
    }

    public DetalleVenta(String nombre, Integer cantidad, Double subTotal) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
}
