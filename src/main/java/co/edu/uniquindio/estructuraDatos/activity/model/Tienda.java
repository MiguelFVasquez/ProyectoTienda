package co.edu.uniquindio.estructuraDatos.activity.model;

import co.edu.uniquindio.estructuraDatos.activity.exceptions.ClienteException;
import co.edu.uniquindio.estructuraDatos.activity.exceptions.VentaException;
import co.edu.uniquindio.estructuraDatos.activity.model.interfaces.ITienda;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Tienda implements ITienda {


    private List<Cliente> listaClientes;
    private List<Venta> listaVentas;
    public Tienda() {
        this.listaClientes = new ArrayList<>();
    }
    //-------------------------CRUD CLIENTE-----------------------------------------

    /**
     *  Si la lista "clientesIguales" permanece vacia va a @return false, o sea que no se encontro ningun empleado igual
     *      * @param cedula
     *      * @return
     * @param identificacion
     * @return
     */
    private boolean verificarCliente(String identificacion){
        List<Cliente>clientesIguales= this.listaClientes.stream()
                .filter(c ->c.verificarIdentificacion(identificacion))
                .collect(Collectors.toList());
        return !clientesIguales.isEmpty();
    }

    /**
     * La idea detrás de usar Optional es indicar de manera explícita que el resultado puede ser nulo, evitando así los problemas asociados con los valores nulos.
     * @param identificacion
     * @return
     */
    private Cliente obtenerCliente(String identificacion){
        Optional<Cliente> clienteOptional=listaClientes.stream()
                .filter(c->c.verificarIdentificacion(identificacion))
                .findFirst();
        return clienteOptional.orElse(null);
    }
    @Override
    public boolean crearCliente(Cliente newCliente) throws ClienteException {
        boolean creado=false;
        if (verificarCliente(newCliente.getNumeroIdentificacion())){
            throw new ClienteException("El cliente con el número de identicación: " + newCliente.getNumeroIdentificacion() + " ya se encuentra registrado");
        }else {
            creado=true;
            listaClientes.add(newCliente);
        }
        return creado;
    }

    @Override
    public void acualizarCliente(Cliente clienteActualizar) {

    }

    @Override
    public boolean eliminarCliente(Cliente clienteEliminar) throws ClienteException {
        boolean eliminado= false;
        if (obtenerCliente(clienteEliminar.getNumeroIdentificacion())==null){
            throw new ClienteException("El cliente con el número de identificación: "+ clienteEliminar.getNumeroIdentificacion()+ " no se encuentra en el sistema");
        }else {
            eliminado=true;
            listaClientes.remove(clienteEliminar);
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
