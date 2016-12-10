/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import models.ModelVentas;
import views.*;
import libreria.Libreria;

/**
 *
 * @author megam
 */
public class ControllerVentas implements ActionListener{
    ModelVentas modelVentas = new ModelVentas();
    ViewVentas viewVentas = new ViewVentas();
    ViewBusqueda viewBusqueda = new ViewBusqueda();
    Libreria libreria = new Libreria();
    double ivaincrement = 0.00;
    double preciototal = 0.00;
    int fila = 0;
    int precio = 0;
    int cantidad = 0;
    int fila1 = 0;
    
    public ControllerVentas(ModelVentas modelVentas, ViewVentas viewVentas){
        this.modelVentas = modelVentas;
        this.viewVentas = viewVentas;
        this.viewVentas.jbtn_busqueda.addActionListener(this);
        this.viewVentas.jbtn_agregar.addActionListener(this);
        this.viewVentas.jbtn_comprar.addActionListener(this);
        this.viewVentas.jbtn_retirar.addActionListener(this);
        this.viewVentas.jbtn_retirarTodo.addActionListener(this);
        modelVentas.conection.executeQuery("begin;");
        initView();
        showRecords();
        showRecords1();
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == viewVentas.jbtn_busqueda){
            busqueda();
       }
       else if(e.getSource() == viewVentas.jbtn_agregar){
           agregar();
           total();
       }
       else if (e.getSource() == viewVentas.jbtn_comprar){
           finalizar();
       }
       else if (e.getSource() == viewVentas.jbtn_retirar){
           retirar();
       }
       else if(e.getSource() == viewVentas.jbtn_retirarTodo){
           retirarTodo();
       }
    }
    
     public void busqueda(){
         if(viewVentas.jtf_busqueda.getText().equals("")) {//revalidar texto
            JOptionPane.showMessageDialog(viewVentas,"Porfavor, ingrese los valores correctos");
         }
         else{
            modelVentas.setProducto(viewVentas.jtf_busqueda.getText());
            accionBu();
            viewBusqueda.jtf_id.setText(""+modelVentas.getId_producto());
            viewBusqueda.jtf_nombre.setText(""+modelVentas.getProducto());
            viewBusqueda.jta_descripcion.setText(""+modelVentas.getDescripcion());
            viewBusqueda.jtf_venta.setText(""+modelVentas.getPrecio_venta());
            viewBusqueda.jtf_cantidad.setText(""+modelVentas.getExistencias());
            JOptionPane.showMessageDialog(viewVentas,viewBusqueda);
                viewBusqueda.jtf_id.setText("");
                viewBusqueda.jtf_nombre.setText("");
                viewBusqueda.jta_descripcion.setText("");
                viewBusqueda.jtf_venta.setText("");
                viewBusqueda.jtf_cantidad.setText("");
                viewVentas.jtf_busqueda.setText("");
         }
     }
     
    public void accionBu(){
        modelVentas.conection.executeQuery("select * from productos where Producto='"+modelVentas.getProducto()+"'");
        modelVentas.conection.moveNext();
        modelVentas.setValues();
    }
     
    public void agregar(){
        modelVentas.conection.executeQuery("select existencias from productos where id_producto ='"+viewVentas.jtf_agregar.getText()+"';");
        modelVentas.conection.moveNext();
        if(modelVentas.getExistencias() < (libreria.stringToInt(viewVentas.jtf_cantidad.getText()))){
            JOptionPane.showMessageDialog(viewVentas, "No puede comprar más productos de los que existen");
            viewVentas.jtf_agregar.setText(null);
            viewVentas.jtf_cantidad.setText(null);
        }
        else if(viewVentas.jtf_agregar.getValue()==null || viewVentas.jtf_cantidad.getValue() == null) {
            JOptionPane.showMessageDialog(viewVentas,"Porfavor, ingrese los valores correctos");
            viewVentas.jtf_agregar.setText(null);
            viewVentas.jtf_cantidad.setText(null);
        }
        else{
            modelVentas.setCantidad(libreria.stringToInt(viewVentas.jtf_cantidad.getText()));
            modelVentas.conection.executeQuery("Select producto, precio_venta, ((precio_venta * .16)*"+modelVentas.getCantidad()+") as iva, ((precio_venta *"+modelVentas.getCantidad()+")+((precio_venta * .16)*"+modelVentas.getCantidad()+")) as subtotal from productos where id_producto = '"+viewVentas.jtf_agregar.getText()+"';");
            modelVentas.poputable1();
            modelVentas.conection.moveNext();
            modelVentas.setValues1();
            modelVentas.conection.executeUpdate(("update productos set existencias = (existencias-"+viewVentas.jtf_cantidad.getText()+") where id_producto ='"+viewVentas.jtf_agregar.getText()+"';"));
            clear();
            modelVentas.initValues();
            
         }
     }
    
    public void total(){
        //Esta condicion y ciclo me permite generar el total de las compras.
            if(modelVentas.tableModel1.getRowCount()>1){
                int ro = modelVentas.tableModel1.getRowCount();
                for(int j=0; j<ro;j++){
                modelVentas.setTotal(libreria.stringToInt(viewVentas.jt_tabla1.getValueAt(j, 4).toString()));
                }
            }
            preciototal = preciototal + modelVentas.getTotal();
            viewVentas.jtf_total.setValue(preciototal);
            viewVentas.jtf_agregar.setText(null);
            viewVentas.jtf_cantidad.setText(null);
    }
     
    public void finalizar(){
        if (viewVentas.jt_tabla1.getRowCount()== 0){
            JOptionPane.showMessageDialog(viewVentas, "No ha seleccionado nada para comprar");
        }
        else{
            int dialog = JOptionPane.showConfirmDialog(viewVentas, "¿Estás seguro que deseas cancelar la compra?");
            if (dialog == 0){
                JOptionPane.showMessageDialog(viewVentas,"La compra ha finalizado");
                modelVentas.conection.executeQuery("commit;");
                clear1();
                clear();
                modelVentas.conection.executeQuery("begin;");
                modelVentas.initValues();
                modelVentas.setTotal(0.00);
                viewVentas.jtf_total.setValue(modelVentas.getTotal());
            }
            else{
                
            }
        }
    }
     
     public void clear(){
         while(modelVentas.tableModel.getRowCount()>0){
             modelVentas.tableModel.removeRow(0);
         }
     }
     
    public void clear1(){//limpia la tabla jt_table
        while(modelVentas.tableModel1.getRowCount() >0){
            modelVentas.tableModel1.removeRow(0);
        }
     }
     
     public void retirar(){
        viewVentas.jt_tabla1.getSelectionModel().addListSelectionListener((ListSelectionEvent g)->{
            if(viewVentas.jt_tabla1.getSelectedRow() != -1){
                fila = viewVentas.jt_tabla1.getSelectedRow();
            }
        });
        if(viewVentas.jt_tabla1.getRowCount() == -1){
            JOptionPane.showMessageDialog(viewVentas, "No hay productos que retirar");
        }
        if(viewVentas.jt_tabla1.getSelectedRow()== -1){
            JOptionPane.showMessageDialog(viewVentas, "No ha seleccionado ningun producto");
        }
        else{
            if(modelVentas.tableModel1.getRowCount()>0){
                 modelVentas.setCantidad(libreria.stringToInt(viewVentas.jt_tabla1.getValueAt(fila, 1).toString()));
                 modelVentas.setProducto(viewVentas.jt_tabla1.getValueAt(fila, 0).toString());
                 modelVentas.setTotal(libreria.stringToInt(viewVentas.jt_tabla1.getValueAt(fila, 4).toString()));
            }
            else{
                
            }
            modelVentas.conection.executeUpdate("update productos set existencias = (existencias+"+modelVentas.getCantidad()+") where producto ='"+modelVentas.getProducto()+"';");
            clear1();
            initView();
            modelVentas.tableModel1.removeRow(fila);
            preciototal = preciototal - modelVentas.getTotal();
            viewVentas.jtf_total.setValue(preciototal);
        }
    }

    public void retirarTodo(){
        if (viewVentas.jt_tabla1.getRowCount()== 0){
            JOptionPane.showMessageDialog(viewVentas, "No hay productos que retirar");
        }
        else{
            int dialog = JOptionPane.showConfirmDialog(viewVentas, "¿Estás seguro que deseas cancelar la compra?");
            if (dialog == 0){
                modelVentas.conection.executeQuery("rollback;");
                clear1();
                clear();
                modelVentas.conection.executeQuery("begin");
                modelVentas.initValues();
                modelVentas.setTotal(0.00);
                viewVentas.jtf_total.setValue(modelVentas.getTotal());
            }
            else{
                 
            }
        }
    }
     
    public void initView(){
        viewVentas.setVisible(true);
        modelVentas.initValues();
        modelVentas.setValues();
    }
    
    private void showRecords(){
        viewVentas.jt_tabla.setModel(modelVentas.tableModel);
        modelVentas.poputable();
    }
    
    private void showRecords1(){
        viewVentas.jt_tabla1.setModel(modelVentas.tableModel1);
    }
}
