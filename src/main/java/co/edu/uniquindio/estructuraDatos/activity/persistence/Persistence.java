package co.edu.uniquindio.estructuraDatos.activity.persistence;

import co.edu.uniquindio.estructuraDatos.activity.model.*;
import co.edu.uniquindio.estructuraDatos.activity.utils.ArchivoUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Persistence {

    public static final String RUTA_ARCHIVO_CLIENTES = "src/main/resources/co/edu/uniquindio/estructuraDatos/activity/app/persistence/archives/archivosClientes.txt";
    public static final String RUTA_ARCHIVO_PRODUCTOS = "src/main/resources/co/edu/uniquindio/estructuraDatos/activity/app/persistence/archives/archivosProductos.txt";
    public static final String RUTA_ARCHIVO_VENTAS = "src/main/resources/co/edu/uniquindio/estructuraDatos/activity/app/persistence/archives/archivosVentas.txt";


    public static void cargarDatosArchivos(Tienda tienda) throws FileNotFoundException, IOException {
        //Cargar archivo de usuarios
        HashMap<String,Cliente> clientesCargados = cargarClientes();
        HashMap<String, Producto> productosCargados = cargarProductos();
        List<Venta> ventasCargadas = cargarVentas();
        if ( !clientesCargados.isEmpty() ) {
            tienda.getMapClientes().putAll( clientesCargados );
        }
        if(!productosCargados.isEmpty()){
            tienda.getMapProductos().putAll( productosCargados );
        }
        if(!ventasCargadas.isEmpty()){
            tienda.getListaVentas().addAll(ventasCargadas);
        }
    }

    private static HashMap<String, Producto> cargarProductos() throws IOException {
        HashMap<String,Producto> productos = new HashMap<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTOS);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);//
            Producto producto = new Producto();
            producto.setCantidad( Integer.valueOf( linea.split("@@")[0] ) );
            producto.setCodigo(linea.split("@@")[1]);
            producto.setNombre(linea.split("@@")[2]);
            producto.setPrecio( Double.valueOf( linea.split("@@")[3] ) );

            productos.put(linea.split("@@")[1],producto);
        }
        return productos;
    }

    private static HashMap<String,Cliente> cargarClientes() throws FileNotFoundException, IOException {
        HashMap<String,Cliente> clientes = new HashMap<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_CLIENTES);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);//
            Cliente cliente = new Cliente();
            cliente.setNombre(linea.split("@@")[0]);
            cliente.setNumeroIdentificacion(linea.split("@@")[1]);
            cliente.setDireccion(linea.split("@@")[2]);
            cliente.setCarritoCliente( new CarritoCompra());

            clientes.put(linea.split("@@")[1],cliente);
        }
        return clientes;
    }

    public static List<Venta> cargarVentas() throws FileNotFoundException, IOException {
        List<Venta> ventas = new ArrayList<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_VENTAS);

        for (String linea : contenido) {
            String[] partes = linea.split("@@");
            if (partes.length >= 2) {
                Venta venta = new Venta();
                venta.setCodigo(partes[0]);
                venta.setFecha(partes[1]);

                // Si hay detalles
                if (partes.length >= 3) {
                    String[] detalles = partes[2].split("@@");
                    for (String detalle : detalles) {
                        String[] detallePartes = detalle.split("@@");
                        if (detallePartes.length == 3) {
                            DetalleVenta detalleVenta = new DetalleVenta(detallePartes[0], Integer.parseInt(detallePartes[1]), Double.parseDouble(detallePartes[2]));
                            venta.getListaDetalles().add(detalleVenta);
                        }
                    }
                }
                ventas.add(venta);
            }
        }
        return ventas;
    }

    public static void guardarVentas(List<Venta> ventas) throws IOException {
        String contenido = "";
        for(Venta ventaList : ventas){
            ventaList.getClienteVenta();
            contenido += ventaList.getCodigo() + "@@"
                    + ventaList.getFecha() + "@@ "
                    + ventaList.getListaDetalles() + "@@"
                    + ventaList.getClienteVenta() + "@@"
                    + ventaList.getIdentificacionCliente() + "@@"
                    + ventaList.getTotal() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_VENTAS, contenido, false);
    }

    /*public static void guardarVentas(List<Venta> ventas) throws IOException {
        String contenido = "";
        for(Venta ventaList : ventas) {
            contenido += "Código: " + ventaList.getCodigo() + ", ";
            contenido += "Fecha: " + ventaList.getFecha() + ", ";

            // Verificar si la lista de detalles no es nula
            List<DetalleVenta> detalles = ventaList.getListaDetalles();
            if (detalles != null) {
                contenido += "Detalles: [";
                for (DetalleVenta detalle : detalles) {
                    contenido += "{";
                    contenido += "Nombre: " + detalle.getNombre() + ", ";
                    contenido += "Cantidad: " + detalle.getCantidad() + ", ";
                    contenido += "Subtotal: " + detalle.getSubTotal() + ", ";
                    contenido += "}";
                }
                contenido += "], ";
            } else {
                contenido += "Detalles: [], ";
            }

            contenido += "Cliente: " + ventaList.getClienteVenta() + ", ";
            contenido += "Identificación Cliente: " + ventaList.getIdentificacionCliente() + ", ";
            contenido += "Total: " + ventaList.getTotal() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_VENTAS, contenido, false);
    }*/


    public static void guardarClientes(HashMap<String,Cliente> listaClientes) throws IOException {
        String contenido = "";
        for (HashMap.Entry<String, Cliente> entry : listaClientes.entrySet()){
            Cliente cliente = entry.getValue();
            contenido += cliente.getNombre() + "@@"
                    + cliente.getNumeroIdentificacion() + "@@"
                    + cliente.getDireccion() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_CLIENTES, contenido, false);
    }
    public static void guardarProductos(HashMap<String,Producto> listaProductos) throws IOException {
        String contenido = "";
        for (HashMap.Entry<String, Producto> entry : listaProductos.entrySet()){
            Producto producto = entry.getValue();
            contenido += producto.getCantidad() + "@@"
                    + producto.getCodigo() + "@@"
                    + producto.getNombre() + "@@"
                    + producto.getPrecio() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS, contenido, false);
    }



}

