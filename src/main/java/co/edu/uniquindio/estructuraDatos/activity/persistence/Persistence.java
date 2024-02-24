package co.edu.uniquindio.estructuraDatos.activity.persistence;

import co.edu.uniquindio.estructuraDatos.activity.model.Cliente;
import co.edu.uniquindio.estructuraDatos.activity.model.Tienda;
import co.edu.uniquindio.estructuraDatos.activity.utils.ArchivoUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Persistence {

    public static final String RUTA_ARCHIVO_CLIENTES = "src/main/resources/co/edu/uniquindio/estructuraDatos/activity/app/persistence/archives/archivosClientes.txt";


    public static void cargarDatosArchivos(Tienda tienda) throws FileNotFoundException, IOException {
        //Cargar archivo de usuarios
        HashMap<String,Cliente> clientesCargados = cargarClientes();
        if ( !clientesCargados.isEmpty() ) {
            tienda.getMapClientes().putAll( clientesCargados );
        }
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
}

