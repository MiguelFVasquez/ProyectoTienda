package co.edu.uniquindio.estructuraDatos.activity.persistence;

import co.edu.uniquindio.estructuraDatos.activity.model.CarritoCompra;
import co.edu.uniquindio.estructuraDatos.activity.model.Cliente;
import co.edu.uniquindio.estructuraDatos.activity.model.Producto;
import co.edu.uniquindio.estructuraDatos.activity.model.Tienda;
import co.edu.uniquindio.estructuraDatos.activity.utils.ArchivoUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Persistence {

    public static final String RUTA_ARCHIVO_CLIENTES = "src/main/resources/co/edu/uniquindio/estructuraDatos/activity/app/persistence/archives/archivosClientes.txt";
    public static final String RUTA_ARCHIVO_PRODUCTOS = "src/main/resources/co/edu/uniquindio/estructuraDatos/activity/app/persistence/archives/archivosProductos.txt";


    public static void cargarDatosArchivos(Tienda tienda) throws FileNotFoundException, IOException {
        //Cargar archivo de usuarios
        HashMap<String,Cliente> clientesCargados = cargarClientes();
        HashMap<String, Producto> productosCargados = cargarProductos();
        if ( !clientesCargados.isEmpty() ) {
            tienda.getMapClientes().putAll( clientesCargados );
        }
        if(!productosCargados.isEmpty()){
            tienda.getMapProductos().putAll( productosCargados );
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

