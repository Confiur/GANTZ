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
   
    public static ModelVentas modelCompras;
    public static ViewVentas viewCompras;
    public static ControllerVentas controllerCompras;
    
    public static ModelProveedores modelProveedores;
    public static ViewProveedores viewProveedores;
    public static ControllerProveedores controllerProveedores;
    public static viewEliminarProve viewEliminarProve;
    public static viewBuscarProvee buscaProveedores;
    public static viewProveedoresEditar editarProv;
    
    public static ModelMain modelMain;
    public static ViewMain viewMain;
    public static ControllerMain controllerMain;
    
    public static modelCompras modelComp;
    public static viewCompras viewComp;
    public static ViewBuscaPedido buscaPedido;
    public static viewLlenaStock llenaStock;
    public static controllerCompras controllerComp;
    
    public static void main(String [] lfar){
        modelProductos = new ModelProductos();
        viewProductos = new ViewProductos();
        controllerProductos = new ControllerProductos(modelProductos, viewProductos);
        
        modelProveedores = new ModelProveedores();
        viewProveedores = new ViewProveedores();
        viewEliminarProve = new viewEliminarProve();
        buscaProveedores = new viewBuscarProvee();
        editarProv = new viewProveedoresEditar();
        controllerProveedores = new ControllerProveedores(modelProveedores, viewProveedores, viewEliminarProve, buscaProveedores, editarProv);
        
        modelCompras = new ModelVentas();
        viewCompras = new ViewVentas();
        controllerCompras = new ControllerVentas(modelCompras, viewCompras);
                
        modelComp = new modelCompras();
        viewComp = new viewCompras();
        buscaPedido = new ViewBuscaPedido();
        llenaStock = new viewLlenaStock();
        controllerComp = new controllerCompras(modelComp, viewComp, buscaPedido, llenaStock);
        
        JPanel views[] = new JPanel[6];
        views[0] = viewProductos;
        views[1] = viewProveedores;
        views[2] = viewComp;
        views[3] = buscaPedido;
        views[4] = llenaStock;
        views[5] = viewCompras;
        
        viewMain = new ViewMain();
        modelMain = new ModelMain();
        controllerMain = new ControllerMain(viewMain, modelMain, views);
    }
}