package co.edu.uniquindio.estructuraDatos.activity.controllers;

public class ClienteController {
    public ModelFactoryController mfm;
    public ClienteController(){
        System.out.println("Llamado a la clase singleton desde ClienteViewController");
        mfm= ModelFactoryController.getInstance();
    }
}