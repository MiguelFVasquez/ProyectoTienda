package co.edu.uniquindio.estructuraDatos.activity.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Venta implements Comparable<Venta>, Serializable {
    private String codigo;
    private LocalDate fecha;
    private List<DetalleVenta> listaDetalles;

    private String identificacionCliente;
    private Double total;

    private static final long serialVersionUID = 1L;
    public Venta() {
    }

    public Venta(String codigo, LocalDate fecha) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.total = 0.0;
        this.listaDetalles = new ArrayList<>();
    }

    private void calcularTotal(Producto producto) {
        total += producto.getSubTotal();
    }
    //-------------METODOS PROPIOS---------------------

    public boolean verificarCodigo(String codigo){
        return this.codigo.equals(codigo);
    }

    //---------------------------------


    public String getIdentificacionCliente() {
        return identificacionCliente;
    }

    public void setIdentificacionCliente(String identificacionCliente) {
        this.identificacionCliente = identificacionCliente;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<DetalleVenta> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<DetalleVenta> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }



    public void setClienteVenta(Cliente clienteVenta) {
        this.identificacionCliente = clienteVenta.getNumeroIdentificacion();
    }
    public boolean verificarFecha(LocalDate fecha){
        return this.getFecha().equals(fecha);
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

    public void agregarProducto(Producto producto){
        if(producto!=null){
            listaDetalles.add( new DetalleVenta( producto.getNombre() , producto.getCantidad() , producto.getSubTotal() ) );
            calcularTotal(producto);
        }
    }
}
