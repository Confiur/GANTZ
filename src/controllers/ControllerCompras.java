/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.ModelCompras;
import views.*;
import libreria.Libreria;

/**
 *
 * @author megam
 */
public class ControllerCompras implements ActionListener{
    ModelCompras modelCompras = new ModelCompras();
    ViewCompras viewCompras = new ViewCompras();
    ViewBusqueda viewBusqueda = new ViewBusqueda();
    Libreria libreria = new Libreria();
    double ivaincrement = 0.00;
    double preciototal = 0.00;
    
    public ControllerCompras(ModelCompras modelCompras, ViewCompras viewCompras){
        this.modelCompras = modelCompras;
        this.viewCompras = viewCompras;
        this.viewCompras.jbtn_busqueda.addActionListener(this);
        this.viewCompras.jbtn_agregar.addActionListener(this);
        this.viewCompras.jbtn_comprar.addActionListener(this);
        
        initView();
        showRecords();
        showRecords1();
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == viewCompras.jbtn_busqueda){
            busqueda();
       }
       else if(e.getSource() == viewCompras.jbtn_agregar){
           agregar();
       }
       else if (e.getSource() == viewCompras.jbtn_comprar){
           clear();
           finalizar();
       }
       else if (e.getSource() == viewCompras.jbtn_eliminar){
           
       }
    }
    
     public void busqueda(){
            modelCompras.setProducto(viewCompras.jtf_busqueda.getText());
            accionBu();
            viewBusqueda.jtf_id.setText(""+modelCompras.getId_producto());
            viewBusqueda.jtf_nombre.setText(""+modelCompras.getProducto());
            viewBusqueda.jta_descripcion.setText(""+modelCompras.getDescripcion());
            viewBusqueda.jtf_venta.setText(""+modelCompras.getPrecio_venta());
            viewBusqueda.jtf_cantidad.setText(""+modelCompras.getExistencias());
            int dialog = JOptionPane.showConfirmDialog(viewCompras,viewBusqueda);
            if (dialog == 0){
               viewBusqueda.jtf_id.setText("");
               viewBusqueda.jtf_nombre.setText("");
               viewBusqueda.jta_descripcion.setText("");
               viewBusqueda.jtf_venta.setText("");
               viewBusqueda.jtf_cantidad.setText("");
               viewCompras.jtf_busqueda.setText("");
            }
     }
     
    public void accionBu(){
        modelCompras.conection.executeQuery("select * from productos where Producto='"+modelCompras.getProducto()+"'");
        modelCompras.conection.moveNext();
        modelCompras.setValues();
    }
     
     public void agregar(){
        modelCompras.conection.executeQuery("select existencias from productos where id_producto ='"+viewCompras.jtf_agregar.getText()+"';");
        modelCompras.conection.moveNext();
        if(viewCompras.jtf_agregar.getValue()==null || viewCompras.jtf_cantidad.getValue() == null) {
            JOptionPane.showMessageDialog(viewCompras,"Porfavor, ingrese los valores correctos");
        }
        else if(modelCompras.getExistencias() < libreria.stringToInt(viewCompras.jtf_cantidad.getText())){
            JOptionPane.showMessageDialog(viewCompras, "No puede comprar más productos de los que existen");
        }
        else{
            modelCompras.setCantidad(libreria.stringToInt(viewCompras.jtf_cantidad.getText()));
            modelCompras.conection.executeQuery("Select producto, precio_venta From productos where id_producto = '"+viewCompras.jtf_agregar.getText()+"';");
            modelCompras.poputable1();
            modelCompras.conection.moveNext();
            modelCompras.setValues1();
            modelCompras.conection.executeUpdate(("update productos set existencias = (existencias-"+viewCompras.jtf_cantidad.getText()+") where id_producto ='"+viewCompras.jtf_agregar.getText()+"';"));
            clear1();
            initView();
            
            
            modelCompras.setIva((modelCompras.getPrecio_venta() * .16) * modelCompras.getCantidad());
            ivaincrement = ivaincrement + modelCompras.getIva();
            viewCompras.jtf_iva.setValue(ivaincrement);
            
            modelCompras.setTotal(modelCompras.getPrecio_venta() * modelCompras.getCantidad());
            preciototal = preciototal + modelCompras.getTotal();
            viewCompras.jtf_subtotal.setValue(preciototal);
            
            viewCompras.jtf_total.setValue(preciototal + ivaincrement);
            viewCompras.jtf_agregar.setText("");
            viewCompras.jtf_cantidad.setText("");
         }
     }
     
     public void finalizar(){
         viewCompras.jtf_iva.setValue(0.00);
         viewCompras.jtf_subtotal.setValue(0.00);
         viewCompras.jtf_total.setValue(0.00);
     }
     
     public void clear1(){
         while(modelCompras.tableModel.getRowCount()>0){
             modelCompras.tableModel.removeRow(0);
         }
     }
     
     public void clear(){//limpia la tabla jt_table
         while(modelCompras.tableModel1.getRowCount() >0){
            modelCompras.tableModel1.removeRow(0);
        }
        JOptionPane.showMessageDialog(viewCompras,"La compra ha finalizado");
     }
     
     public void quitar(){
         
     }
     
     
     public void initView(){
        viewCompras.setVisible(true);
        modelCompras.initValues();
        modelCompras.setValues();
    }
   
    private void showRecords(){
        viewCompras.jt_tabla.setModel(modelCompras.tableModel);
        modelCompras.poputable();
    }
    
    private void showRecords1(){
        viewCompras.jt_tabla1.setModel(modelCompras.tableModel1);
    }
}
