package co.edu.uniquindio.estructuraDatos.activity.controllers;

import co.edu.uniquindio.estructuraDatos.activity.viewControllers.InicioViewController;

public class ModelFactoryController {
    private InicioViewController inicioViewController;

    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aquí al ser protected
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();

    }

    // Método para obtener la instancia de nuestra clase

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }
    public ModelFactoryController() {
        //1. inicializar datos y luego guardarlo en archivos

        System.out.println("Invocacion clase singleton");
        inicializarDatos();
    }
    private void inicializarDatos(){

    }
    public void initInicioViewController(InicioViewController inicioViewController){
        this.inicioViewController= inicioViewController;
    }
}
