package co.edu.uniquindio.estructuraDatos.activity.model.interfaces;

import co.edu.uniquindio.estructuraDatos.activity.model.Cliente;
import co.edu.uniquindio.estructuraDatos.activity.model.Venta;

public interface ITienda {

    //----------Metodos para los clientes----------------------------
    public void crearCliente(Cliente newCliente);
    public void acualizarCliente(Cliente clienteActualizar);
    public void eliminarCliente(Cliente clienteEliminar);

    //----------Metodos para la venta-----------------------
    public void crearVenta(Venta newVenta);
    public void eliminaVenta(Venta ventaEliminar);

}
