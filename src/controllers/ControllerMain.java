/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ModelMain;
import views.ViewMain;
import javax.swing.JPanel;
/**
 *
 * @author megam
 */
public class ControllerMain implements ActionListener{
    ModelMain modelMain = new ModelMain();
    ViewMain viewMain = new ViewMain();
    JPanel[] views;

    public ControllerMain(ViewMain viewMain, ModelMain modelMain, JPanel[] views) {
        this.viewMain = viewMain;
        this.modelMain = modelMain;
        this.views = views;
        this.viewMain.jmi_productos.addActionListener(this);
        initView();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== viewMain.jmi_productos){
            jmi_productos();
        }
    }

    
    public void jmi_productos(){
        this.viewMain.setContentPane(views[0]);
        this.viewMain.revalidate();
        this.viewMain.repaint();
    }

    public void initView(){
        this.viewMain.setTitle("Integracion");
        this.viewMain.setLocationRelativeTo(null);
        this.viewMain.setVisible(true);
    }
}

