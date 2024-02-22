package co.edu.uniquindio.estructuraDatos.activity.model;

import co.edu.uniquindio.estructuraDatos.activity.exceptions.ClienteException;
import co.edu.uniquindio.estructuraDatos.activity.exceptions.VentaException;
import co.edu.uniquindio.estructuraDatos.activity.model.interfaces.ITienda;

import java.util.*;
import java.util.stream.Collectors;

public class Tienda implements ITienda {

    private String nombre;
    private HashMap<String,Cliente> mapClientes;
    private HashMap<String,Producto> mapProductos;

    private Set<Producto> listaProductos; //Carrito
    private List<Venta> listaVentas;



    public Tienda() {
    }

    public Tienda(String nombre) {
        this.nombre= nombre;
        this.mapClientes = new HashMap<>();
        this.listaVentas= new ArrayList<>();
        this.mapProductos = new HashMap<>();
        this.listaProductos= new HashSet<>();
    }
    //-------------------------CRUD CLIENTE-----------------------------------------

    /**
     *  Si la lista "clientesIguales" permanece vacia va a @return false, o sea que no se encontro ningun empleado igual
     *      * @param cedula
     *      * @return
     * @param
     * @return
     */
    //-------------------------CRUD CLIENTE---------------------------------

    /**
     * Metodo para verificar la existencia de un cliente
     * @param numeroIdentificacion
     * @return
     */
    private boolean verificarCliente(String numeroIdentificacion){
        return mapClientes.containsKey(numeroIdentificacion);
    }
    private Cliente obtenerCliente(String numeroIdentificacion){
        return mapClientes.getOrDefault(numeroIdentificacion, null);
    }

    /**
     *Obtengo la identificacion del newCliente
     * Si el cliente ya existe no se crea un nuevo cliente con la misma identificacion
     * Si no, lo añadp al hashMap de clientes
     * @param newCliente
     * @return
     * @throws ClienteException
     */
    public boolean crearCliente(Cliente newCliente) throws ClienteException{
        boolean creado= false;
        String numIdentificacion= newCliente.getNumeroIdentificacion();
        if (verificarCliente(numIdentificacion)){
            throw new ClienteException("El número de identificación " + numIdentificacion+ " ya se encuentra asociado a un cliente");
        }else {
            creado=true;
            mapClientes.put(numIdentificacion,newCliente);
        }
        return creado;
    }

    /**
     * Obtengo los nuevos valores del cliente
     * Verifico que el cliente si exista
     * si existe accedo al metodo set para cambiarle los atributos
     * @param clienteActualizar
     * @return
     * @throws ClienteException
     */
    public boolean acualizarCliente(Cliente clienteActualizar) throws ClienteException{
        boolean actualizado= false;
        String numIdentificacion= clienteActualizar.getNumeroIdentificacion();
        String newName= clienteActualizar.getNombre();
        String newAddress= clienteActualizar.getDireccion();
        Cliente clienteObtenido= obtenerCliente(numIdentificacion);
        if (clienteObtenido == null){
            throw new ClienteException("El cliente con el número de identificación: " + numIdentificacion+ " no ha sido encontrado"  );
        }else {
            actualizado= true;
            clienteObtenido.setNombre(newName);
            clienteObtenido.setDireccion(newAddress);
        }
        return actualizado;
    }

    /**
     * Obtengo la identificacion del cliente y verifico si el cliente si existe
     * Si existe lo elimino del hashmap de clientes
     * @param clienteEliminar
     * @return
     * @throws ClienteException
     */
    public boolean eliminarCliente(Cliente clienteEliminar) throws ClienteException{
        boolean eliminado= false;
        String numIdentificacion= clienteEliminar.getNumeroIdentificacion();
        if (obtenerCliente(numIdentificacion) == null){
            throw new ClienteException("El cliente con el número de identificación: " + numIdentificacion+ " no ha sido encontrado"  );
        }else {
            eliminado=true;
            mapClientes.remove(numIdentificacion);
        }
        return eliminado;
    }

    //-------------------------CRUD VENTA---------------------------

    private boolean verificarVenta(String codigo){
        List<Venta>ventasIguales= this.listaVentas.stream()
                .filter(v->v.verificarCodigo(codigo))
                .collect(Collectors.toList());
        return !ventasIguales.isEmpty();
    }
    private Venta obtenerVenta(String codigo){
        Optional<Venta> clienteOptional=listaVentas.stream()
                .filter(c->c.verificarCodigo(codigo))
                .findFirst();
        return clienteOptional.orElse(null);
    }
    @Override
    public boolean crearVenta(Venta newVenta) throws VentaException {
        boolean creado= false;
        if (verificarVenta(newVenta.getCodigo())){
            throw new VentaException("La factura de venta con código: "+ newVenta.getCodigo()+ " ya se encuentra registrada");
        }else {
            creado= true;
            listaVentas.add(newVenta);
        }
        return creado;
    }

    @Override
    public boolean eliminaVenta(Venta ventaEliminar) throws VentaException {
        boolean eliminado= false;
        if (obtenerVenta(ventaEliminar.getCodigo()) == null){
            throw new VentaException("La factura de venta con código: "+ ventaEliminar.getCodigo()+ " no se encuentra en el sistema");
        }else {
            eliminado= true;
            listaVentas.remove(ventaEliminar);
        }
        return eliminado;
    }
}
