package co.edu.uniquindio.estructuraDatos.activity.model;

import java.util.Objects;

public class Venta {
    private String codigo;
    private String fecha;

    public Venta() {
    }

    public Venta(String codigo, String fecha) {
        this.codigo = codigo;
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    //-------------METODOS PROPIOS---------------------

    public boolean verificarCodigo(String codigo){
        return this.codigo.equals(codigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venta venta = (Venta) o;
        return Objects.equals(codigo, venta.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
