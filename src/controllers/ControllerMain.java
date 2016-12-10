/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.ModelMain;
import views.ViewMain;
import javax.swing.JPanel;
import views.ViewLogin;
import libreria.Libreria;
/**
 *
 * @author megam
 */
public class ControllerMain implements ActionListener{
    ModelMain modelMain = new ModelMain();
    ViewMain viewMain = new ViewMain();
    ViewLogin viewLogin = new ViewLogin();
    Libreria libreria = new Libreria();
    JPanel[] views;

    public ControllerMain(ViewMain viewMain, ModelMain modelMain, JPanel[] views) {
        this.viewMain = viewMain;
        this.modelMain = modelMain;
        this.views = views;
        this.viewMain.jmi_productos.addActionListener(this);
        this.viewMain.jmi_proveedores.addActionListener(this);
        this.viewMain.jmi_Pedido.addActionListener(this);
        this.viewMain.jmi_rCompra.addActionListener(this);
        this.viewMain.jmi_ingresar.addActionListener(this);
        this.viewMain.jmi_compras.addActionListener(this);
        this.viewMain.jbtn_registrarse.addActionListener(this);
        this.viewMain.jbtn_ingresar.addActionListener(this);
        initView();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== viewMain.jmi_productos){
            jmi_productos();
        }else if (e.getSource()==viewMain.jmi_proveedores){
            jmi_proveedores();
        }else if(e.getSource()==viewMain.jmi_Pedido){
            jmi_compras();
        }else if(e.getSource()==viewMain.jmi_rCompra){
            jmi_buscaCompras();
        }else if(e.getSource()==viewMain.jmi_ingresar){
            jmi_ingresar();
        }else if(e.getSource()==viewMain.jmi_compras){
            jmi_ventas();
        }else if(e.getSource()== viewMain.jbtn_registrarse){
            registrar();
        }
        else if(e.getSource()== viewMain.jbtn_ingresar){
            ingresar();
        }
    }
        
    public void jmi_productos(){
        this.viewMain.setContentPane(views[0]);
        this.viewMain.revalidate();
        this.viewMain.repaint();
        this.viewMain.pack();
    }
    
    public void jmi_proveedores(){
        this.viewMain.setContentPane(views[1]);
        this.viewMain.revalidate();
        this.viewMain.repaint();
        this.viewMain.pack();
    }
    
    public void jmi_compras(){
        this.viewMain.setContentPane(views[2]);
        this.viewMain.revalidate();
        this.viewMain.repaint();
        this.viewMain.pack();
    }
    
    public void jmi_buscaCompras(){
        this.viewMain.setContentPane(views[3]);
        this.viewMain.revalidate();
        this.viewMain.repaint();
        this.viewMain.pack();
    }
    
    private void jmi_ingresar() {
        this.viewMain.setContentPane(views[4]);
        this.viewMain.revalidate();
        this.viewMain.repaint();
        this.viewMain.pack();
    }
    
    private void jmi_ventas() {
        this.viewMain.setContentPane(views[5]);
        this.viewMain.revalidate();
        this.viewMain.repaint();
        this.viewMain.pack();
    }
    
    public void ingresar(){
        modelMain.setId_login(libreria.stringToInt(viewMain.jtf_id.getText()));
        modelMain.setUsuario(viewMain.jtf_usuario.getText());
        modelMain.setPassword(viewMain.jtf_password.getText());
        if(viewMain.jtf_id.getValue() == null){
            JOptionPane.showMessageDialog(viewMain, "No ha ingresado su ID");
        }
        else{
            modelMain.conection.executeQuery("SELECT usuario, password from login where id_login = '"+modelMain.getId_login()+"';");
            modelMain.moveNext();
            if(modelMain.getUsuario().equals(modelMain.getConf_usuario()) && modelMain.getPassword().equals(modelMain.getConf_pass())){
                modelMain.conection.executeQuery("SELECT tipo from login where id_login = '"+viewMain.jtf_id.getText()+"';");
                modelMain.initValues2();
                JOptionPane.showMessageDialog(viewMain, "Bienvenido "+modelMain.getTipo());
                if(modelMain.getTipo().equals("administrador")){
                    viewMain.jm_admin.setEnabled(true);
                    viewMain.jm_usuario.setEnabled(true);
                    viewMain.jm_mas.setEnabled(true);
                    viewMain.jtf_id.setText("");
                    viewMain.jtf_usuario.setText("");
                    viewMain.jtf_password.setText("");
                }
                else if(modelMain.getTipo().equals("usuario")){
                    viewMain.jm_usuario.setEnabled(true);
                    viewMain.jm_mas.setEnabled(true);
                    viewMain.jtf_id.setText("");
                    viewMain.jtf_usuario.setText("");
                    viewMain.jtf_password.setText("");
                }
            }
            else{
                JOptionPane.showMessageDialog(viewMain, "Este usuario no esta registrado");
            }
        }
    }
    
    public void registrar(){
        int dialog = JOptionPane.showConfirmDialog(viewMain, viewLogin);
            if (dialog == 0){
                
            }
            else{
                
            }
    }

    public void initView(){
        this.viewMain.setTitle("Integracion");
        this.viewMain.setLocationRelativeTo(null);
        this.viewMain.setVisible(true);
    }
}

