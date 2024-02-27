package co.edu.uniquindio.estructuraDatos.activity.controllers;

public class CarritoController {
    public ModelFactoryController mfm;
    public CarritoController(){
        System.out.println("Llamado a la clase singleton desde CarritoViewController");
        mfm= ModelFactoryController.getInstance();
    }
}