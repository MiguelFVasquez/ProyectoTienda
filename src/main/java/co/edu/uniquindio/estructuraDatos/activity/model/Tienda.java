package co.edu.uniquindio.estructuraDatos.activity.model;

import co.edu.uniquindio.estructuraDatos.activity.exceptions.ClienteException;
import co.edu.uniquindio.estructuraDatos.activity.exceptions.ProductoException;
import co.edu.uniquindio.estructuraDatos.activity.exceptions.VentaException;
import co.edu.uniquindio.estructuraDatos.activity.model.interfaces.ITienda;

import java.util.*;
import java.util.stream.Collectors;
import lombok.*;
@Getter
@Setter
public class Tienda implements ITienda {

    private String nombre;
    private HashMap<String,Cliente> mapClientes;
    private HashMap<String,Producto> mapProductos;
    private List<Venta> listaVentas;
    private Set<Producto> inventario;


    public Tienda() {
    }

    public Tienda(String nombre) {
        this.nombre= nombre;
        this.mapClientes = new HashMap<>();
        this.listaVentas= new LinkedList<>();
        this.mapProductos = new HashMap<>();
        this.inventario= new TreeSet<>();
    }
    //-------------------------CRUD CLIENTE-----------------------------------------

    /**
     * Metodo para verificar la existencia de un cliente
     * @param numeroIdentificacion
     * @return
     */
    public boolean verificarCliente(String numeroIdentificacion){
        return mapClientes.containsKey(numeroIdentificacion);
    }
    public Cliente obtenerCliente(String numeroIdentificacion){
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
            throw new ClienteException("El cliente con el número de identificación: " + numIdentificacion+ " ya se encuentra registrados");
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
                .toList();
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
            Collections.sort(listaVentas);
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

    //---------------PRODCTOS-----------------------------------------------

    private boolean verificarProducto(String codigo){
        return mapClientes.containsKey(codigo);
    }

    /**
     * Si el producto ya existe lo que se hace es setear la cantidad existente del producto
     *
     * @param newProducto
     * @return
     */
    @Override
    public boolean agregarProducto(Producto newProducto) {
        boolean creado= false;
        if (verificarProducto(newProducto.getCodigo())){
            Producto productoAux= mapProductos.get(newProducto.getCodigo());
            int newCantidad= productoAux.getCantidad()+ newProducto.getCantidad();
            productoAux.setCantidad(newCantidad);
            creado=true;
        }else {
            mapProductos.put(newProducto.getCodigo(), newProducto);
            inventario.add(newProducto);
            creado=true;
        }
        return creado;
    }

    /**
     *
     * El metodo para eliminar, lo que hace es restar la cantidad del producto cuando se hace la venta
     *
     * @param productoEliminar
     * @return
     * @throws ProductoException
     */
    @Override
    public boolean eliminarProducto(Producto productoEliminar) throws ProductoException {
        boolean eliminado= false;
        if (verificarProducto(productoEliminar.getCodigo())){
            Producto productoAux= mapProductos.get(productoEliminar.getCodigo());
            int newCantidad= productoAux.getCantidad() - productoEliminar.getCantidad();
            productoAux.setCantidad(newCantidad);
            eliminado=true;
        }else {
            throw new ProductoException("El producto " + productoEliminar.getNombre() + "no ha sido encontrado");
        }
        return eliminado;
    }


}
