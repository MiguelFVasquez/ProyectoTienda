package co.edu.uniquindio.estructuraDatos.activity.model;

public class CarritoCompra {
    private String codigo;

    public CarritoCompra() {
    }
    public CarritoCompra(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
