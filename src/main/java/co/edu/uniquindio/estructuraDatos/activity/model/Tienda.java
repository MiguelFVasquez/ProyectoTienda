package co.edu.uniquindio.estructuraDatos.activity.model;
import co.edu.uniquindio.estructuraDatos.activity.exceptions.ClienteException;
import co.edu.uniquindio.estructuraDatos.activity.exceptions.ProductoException;
import co.edu.uniquindio.estructuraDatos.activity.exceptions.VentaException;
import co.edu.uniquindio.estructuraDatos.activity.model.interfaces.ITienda;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashMap<String, Cliente> getMapClientes() {
        return mapClientes;
    }

    public void setMapClientes(HashMap<String, Cliente> mapClientes) {
        this.mapClientes = mapClientes;
    }

    public HashMap<String, Producto> getMapProductos() {
        return mapProductos;
    }

    public void setMapProductos(HashMap<String, Producto> mapProductos) {
        this.mapProductos = mapProductos;
    }

    public List<Venta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }

    public Set<Producto> getInventario() {
        return inventario;
    }

    public void setInventario(Set<Producto> inventario) {
        this.inventario = inventario;
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

    //---------------PRODUCTOS-----------------------------------------------

    private boolean verificarProducto(String codigo){
        return mapProductos.containsKey(codigo);
    }
    public Producto obtenerProducto(String codigo){
        return mapProductos.getOrDefault(codigo,null);
    }

    public Producto obtenerProductoNombre(String nombre){
        String nombreLowerCase= nombre.toLowerCase();
        for (Producto productoAux: mapProductos.values()) {
            if (productoAux.getNombre().toLowerCase().contains(nombreLowerCase) && !nombre.equals(" ")) return productoAux;
        }
        return null;
    }

    /**
     * Si el producto ya existe lo que se hace es setear la cantidad existente del producto
     *
     * @param newProducto
     * @return
     */
    @Override
    public boolean agregarProducto(Producto newProducto) throws ProductoException{
        boolean creado= false;
        if (verificarProducto(newProducto.getCodigo())){
            throw new ProductoException("El producto con código: " + newProducto.getCodigo() + " ya se encuentra registrado");
        }else {
            mapProductos.put(newProducto.getCodigo(), newProducto);
            inventario.add(newProducto);
            creado=true;
        }
        return creado;

    }

    @Override
    public boolean actualizarProducto(Producto productoActualizar) throws ProductoException{
        boolean actualizado=false;
        Producto productoObtenido= obtenerProducto(productoActualizar.getCodigo());
        if (productoObtenido==null){
            throw new ProductoException("El producto con el código: " + productoActualizar.getCodigo()+ " no ha sido encontrado");
        }else {
            String newName= productoActualizar.getNombre();
            Integer newCant= productoActualizar.getCantidad() + productoObtenido.getCantidad();
            actualizado=true;
            productoObtenido.setNombre(newName);
            productoObtenido.setCantidad(newCant);
        }
        return actualizado;
    }
    /**
     *
     * El metodo para vender, lo que hace es restar la cantidad del producto cuando se hace la venta
     *
     * @param productoVender
     * @return
     * @throws ProductoException
     */
    @Override
    public boolean ventaProducto(Producto productoVender) throws ProductoException {
        boolean vendido= false;
        if (verificarProducto(productoVender.getCodigo())){
            Producto productoAux= mapProductos.get(productoVender.getCodigo());
            int newCantidad= productoAux.getCantidad() - productoVender.getCantidad();
            if ( newCantidad>0){
                productoAux.setCantidad(newCantidad);
                vendido=true;
            }else{
                if(newCantidad==0){
                    productoAux.setCantidad( newCantidad );
                    vendido= true;
                }else{
                    throw new ProductoException( "No contamos con la cantidad de " + productoVender.getNombre()+ " solicitada" );
                }
            }
        }else {
            throw new ProductoException("El producto " + productoVender.getNombre() + " no ha sido encontrado");
        }
        return vendido;
    }
    @Override
    public boolean eliminarProducto(Producto productoEliminar) throws ProductoException{
        boolean eliminado= false;
        if (obtenerProducto(productoEliminar.getCodigo())== null ){
            throw new ProductoException("El producto no ha sido encontrado");
        }else {
            eliminado=true;
            mapProductos.remove(productoEliminar.getCodigo());
        }
        return eliminado;
    }
//----------------------------PRODUCTO-CLIENTE---------------------------------------
    public boolean agregarProductoCliente(Producto producto, String id) throws ProductoException {
        boolean agregado = false;
        if(verificarProducto( producto.getCodigo() )) {
            Cliente cliente = obtenerCliente(id);
            producto.calcularSubtotal();
            if (ventaProducto(producto)){
                cliente.agregarACarrito(producto);
                agregado = true;
            }

        }else{
            throw new ProductoException("El producto: " + producto.getNombre() + " no ha sido encontrado");
        }
        return agregado;
    }
    public boolean eliminarProductoCliente(Producto producto, String id) throws ProductoException {
        boolean eliminado = false;
        if(verificarProducto( producto.getCodigo() )){
            Cliente cliente = obtenerCliente( id );
            cliente.eliminarDeCarrito( producto );
            actualizarProducto( producto );
            eliminado = true;
        }else{
            throw new ProductoException("El producto: " + producto.getNombre() + " no ha sido encontrado");

        }
        return eliminado;
    }


    public boolean eliminarProductosCliente(String identificacionCliente) throws ProductoException, ClienteException {
        boolean eliminados = false;
        if(verificarCliente( identificacionCliente )){
            Cliente cliente = obtenerCliente( identificacionCliente );
            for(Producto producto: cliente.obtenerProductosCarrito()){
                actualizarProducto( producto );
            }
            cliente.cancelarVenta();
            eliminados =  true;

        }else{
            throw new ClienteException("El Cliente: " + identificacionCliente + " no ha sido encontrado");
        }
        return eliminados;
    }

    public boolean comprarProductosCarrito(Cliente cliente) throws ClienteException {
        boolean comprados = false;
            if(verificarCliente( cliente.getNumeroIdentificacion() )){
                LocalDate fechaActual = LocalDate.now();
                // Formatear la fecha como una cadena
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fechaActualComoString = fechaActual.format(formatter);
                Venta venta = new Venta("VEN-" + System.currentTimeMillis(), fechaActual.toString());
                for (Producto producto: cliente.obtenerProductosCarrito()) {
                    if(producto!=null){
                        venta.agregarProducto( producto );
                    }
                }
                venta.setClienteVenta( cliente );
                listaVentas.add( venta );
                cliente.comprarProductos();
                comprados = true;
            }else{
                throw new ClienteException("El Cliente: " + cliente.getNumeroIdentificacion() + " no ha sido encontrado");
            }
        return comprados;
    }

}
