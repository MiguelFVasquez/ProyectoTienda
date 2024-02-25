package co.edu.uniquindio.estructuraDatos.activity.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Venta implements Comparable<Venta>  {
    private String codigo;
    private String fecha;
    private List<DetalleVenta> lisaDetalles;
    private Cliente clienteVenta;

    public Venta() {
    }

    public Venta(String codigo, String fecha) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.lisaDetalles = new ArrayList<>();
        this.clienteVenta = new Cliente();
    }
    //-------------METODOS PROPIOS---------------------

    public boolean verificarCodigo(String codigo){
        return this.codigo.equals(codigo);
    }

    //---------------------------------


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<DetalleVenta> getLisaDetalles() {
        return lisaDetalles;
    }

    public void setLisaDetalles(List<DetalleVenta> lisaDetalles) {
        this.lisaDetalles = lisaDetalles;
    }

    public Cliente getClienteVenta() {
        return clienteVenta;
    }

    public void setClienteVenta(Cliente clienteVenta) {
        this.clienteVenta = clienteVenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venta venta = (Venta) o;
        return Objects.equals(codigo, venta.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public int compareTo(Venta o) {
        return this.getFecha().compareTo(o.getFecha());
    }
}
