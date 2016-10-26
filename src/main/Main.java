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
    
    public static ModelMain modelMain;
    public static ViewMain viewMain;
    public static ControllerMain controllerMain;
    
    public static void main(String [] lfar){
        modelProductos = new ModelProductos();
        viewProductos = new ViewProductos();
        controllerProductos = new ControllerProductos(modelProductos, viewProductos);
        
        JPanel views[] = new JPanel[1];
        views[0] = viewProductos;
        
        viewMain = new ViewMain();
        modelMain = new ModelMain();
        controllerMain = new ControllerMain(viewMain, modelMain, views);
    }
}