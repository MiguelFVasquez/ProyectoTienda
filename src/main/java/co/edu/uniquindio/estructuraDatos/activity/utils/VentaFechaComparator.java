package co.edu.uniquindio.estructuraDatos.activity.utils;

import co.edu.uniquindio.estructuraDatos.activity.model.Venta;

import java.util.Comparator;

public class VentaFechaComparator implements Comparator<Venta> {
    @Override
    public int compare(Venta venta1, Venta venta2) {
        // Comparar las fechas de las ventas
        return venta1.getFecha().compareTo(venta2.getFecha());
    }
}
