package co.edu.uniquindio.estructuraDatos.activity.model;

import java.util.Objects;

public class Cliente {
    private String nombre;
    private String numeroIdentificacion;

    private String direccion;

    public Cliente() {
    }

    public Cliente(String nombre, String numeroIdentificacion, String direccion) {
        this.nombre = nombre;
        this.numeroIdentificacion = numeroIdentificacion;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    //Funciones propias

    public boolean verificarIdentificacion(String numeroIdentificacion){
        return this.numeroIdentificacion.equals(numeroIdentificacion);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(numeroIdentificacion, cliente.numeroIdentificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroIdentificacion);
    }
}
