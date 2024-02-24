package co.edu.uniquindio.estructuraDatos.activity.controllers;

public class ClienteViewController {
    public ModelFactoryController mfm;
    public ClienteViewController(){
        System.out.println("Llamado a la clase singleton desde ClienteViewController");
        mfm= ModelFactoryController.getInstance();
    }
}