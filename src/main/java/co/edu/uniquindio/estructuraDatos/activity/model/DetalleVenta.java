package co.edu.uniquindio.estructuraDatos.activity.model;

import java.util.concurrent.ThreadPoolExecutor;

public class DetalleVenta {
    private Integer cantidad;
    private Double subTotal;

    public DetalleVenta() {
    }

    public DetalleVenta(Integer cantidad, Double subTotal) {
        this.cantidad = cantidad;
        this.subTotal = subTotal;
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
