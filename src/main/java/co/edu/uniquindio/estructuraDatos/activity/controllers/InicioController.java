package co.edu.uniquindio.estructuraDatos.activity.controllers;

public class InicioController {
    public ModelFactoryController mfm;
    public InicioController(){
        System.out.println("Llamado a la clase singleton desde InicioViewController");
        mfm= ModelFactoryController.getInstance();
    }
}
