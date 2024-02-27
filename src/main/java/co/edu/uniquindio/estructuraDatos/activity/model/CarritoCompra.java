package co.edu.uniquindio.estructuraDatos.activity.model;

import co.edu.uniquindio.estructuraDatos.activity.exceptions.ProductoException;

import java.util.ArrayList;
import java.util.HashSet;

public class CarritoCompra {
    private String codigoCarro;
    private HashSet<String> listaCodigosProductos;
    private ArrayList<Producto> listaProductos;

    public CarritoCompra() {
        this.codigoCarro="0";
        this.listaCodigosProductos = new HashSet<>();
        this.listaProductos = new ArrayList<>();
    }

    public CarritoCompra(String codigoCarro) {
        this.codigoCarro=codigoCarro;
        this.listaCodigosProductos = new HashSet<>();
        this.listaProductos = new ArrayList<>();
    }

    public boolean verificarProducto(String codigo){
        return listaCodigosProductos.contains(codigo);
    }

    public void agregarACarro(Producto newProducto){
        if(verificarProducto( newProducto.getCodigo() )){
            for (int i = 0; i < listaProductos.size(); i++) {
                Producto productoAux = listaProductos.get( i );
                if(productoAux.getCodigo().equals( newProducto.getCodigo() )){
                    productoAux.setCantidad( productoAux.getCantidad() + newProducto.getCantidad() );
                }
            }
        }else{
            listaCodigosProductos.add(newProducto.getCodigo());
            listaProductos.add( newProducto );
        }
    }
    public void eliminarDeCarrito(Producto productoEliminar){
        listaCodigosProductos.remove(productoEliminar.getCodigo());
        listaProductos.remove(productoEliminar);
    }

    public HashSet<String> getListaCodigosProductos() {
        return listaCodigosProductos;
    }

    public void setListaCodigosProductos(HashSet<String> listaCodigosProductos) {
        this.listaCodigosProductos = listaCodigosProductos;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Producto obtenerProductoCarrito(String codigo) throws ProductoException {
        if(verificarProducto( codigo )){
            for (int i = 0; i < listaProductos.size(); i++) {
                if(listaProductos.get( i ).getCodigo().equals( codigo )){
                    return listaProductos.get( i );
                }
            }
        }else{
            throw new ProductoException( "El producto " + codigo + " no está en el carrito del cliente" );
        }
        return null;
    }
}
