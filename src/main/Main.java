/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import models.*;
import views.*;
import controllers.*;
import javax.swing.JPanel;

/**
 *
 * @author megam
 */
public class Main {
    public static ModelProductos modelProductos;
    public static ViewProductos viewProductos;
    public static ControllerProductos controllerProductos;
   
    public static ModelProveedores modelProveedores;
    public static ViewProveedores viewProveedores;
    public static ControllerProveedores controllerProveedores;
    public static viewEliminarProve viewEliminarProve;
    public static viewBuscarProvee buscaProveedores;
    public static viewProveedoresEditar editarProv;
    
    public static ModelMain modelMain;
    public static ViewMain viewMain;
    public static ControllerMain controllerMain;
    
    public static void main(String [] lfar){
        modelProductos = new ModelProductos();
        viewProductos = new ViewProductos();
        controllerProductos = new ControllerProductos(modelProductos, viewProductos);
        
        modelProveedores = new ModelProveedores();
        viewProveedores = new ViewProveedores();
        viewEliminarProve = new viewEliminarProve();
        buscaProveedores = new viewBuscarProvee();
        editarProv = new viewProveedoresEditar();
        controllerProveedores = new ControllerProveedores(modelProveedores, viewProveedores, viewEliminarProve, buscaProveedores,editarProv);
        
        JPanel views[] = new JPanel[5];
        views[0] = viewProductos;
        views[1] = viewProveedores;
        
        viewMain = new ViewMain();
        modelMain = new ModelMain();
        controllerMain = new ControllerMain(viewMain, modelMain, views);
    }
}