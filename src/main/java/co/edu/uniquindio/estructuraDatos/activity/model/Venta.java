package co.edu.uniquindio.estructuraDatos.activity.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Venta implements Comparable<Venta>  {
    private String codigo;
    private String fecha;
    private List<DetalleVenta> listaDetalles;
    private Cliente clienteVenta;
    private String identificacionCliente;
    private Double total;

    public Venta() {
    }

    public Venta(String codigo, String fecha) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.total = 0.0;
        this.listaDetalles = new ArrayList<>();
        this.clienteVenta = new Cliente();
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<DetalleVenta> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<DetalleVenta> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    public Cliente getClienteVenta() {
        return clienteVenta;
    }

    public void setClienteVenta(Cliente clienteVenta) {
        this.clienteVenta = clienteVenta;
        this.identificacionCliente = clienteVenta.getNumeroIdentificacion();
    }
    public boolean verificarFecha(String fecha){
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
