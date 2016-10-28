/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ModelProductos;
import views.ViewProductos;
import views.viewAgregar;
import javax.swing.JOptionPane;
import libreria.Libreria;

/**
 *
 * @author megam
 */
public class ControllerProductos implements ActionListener{
    ModelProductos modelProductos = new ModelProductos();
    ViewProductos viewProductos = new ViewProductos();
    viewAgregar viewAgregar = new viewAgregar();
    Libreria libreria = new Libreria();
    
    public ControllerProductos(ModelProductos modelProductos, ViewProductos viewProductos){
        this.viewProductos = viewProductos;
        this.modelProductos = modelProductos;
        
        this.viewProductos.jbtn_agregar.addActionListener(this);
        this.viewProductos.jbtn_eliminar.addActionListener(this);
        this.viewProductos.jbtn_editar.addActionListener(this);
        initView();
        showRecords();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==viewProductos.jbtn_agregar){
            JOptionPane.showMessageDialog(viewProductos, viewAgregar);
            modelProductos.setProducto(viewAgregar.jtf_nombre.getText());
            modelProductos.setDescripcion(viewAgregar.jta_descripcion.getText());
            modelProductos.setPrecio_compra(libreria.stringToInt(viewAgregar.jtf_compra.getText()));
            modelProductos.setPrecio_venta(libreria.stringToInt(viewAgregar.jtf_venta.getText()));
            modelProductos.setExistencias(libreria.stringToInt(viewAgregar.jtf_cantidad.getText()));
            accionAgregar();
        }
    }
    
    public void accionAgregar(){
        modelProductos.conection.executeUpdate("insert into productos (producto, descripcion, precio_compra, precio_venta, existencias) values('"+modelProductos.getProducto()+"','"+modelProductos.getDescripcion()+"','"+modelProductos.getPrecio_compra()+"','"+modelProductos.getPrecio_venta()+"','"+modelProductos.getExistencias()+"');");
        clear();
    }
    
    public void clear(){
        //limpia la tabla jt_table
        while(modelProductos.tableModel.getRowCount() >0){
            modelProductos.tableModel.removeRow(0);
        }
        initView();
    }
    
    public void accionEliminar(){
        
    }
    
    public void initView(){
        viewProductos.setVisible(true);
        modelProductos.initValues();
        modelProductos.setValues();
    }
   
    private void showRecords(){
        viewProductos.jt_tabla.setModel(modelProductos.tableModel);
        modelProductos.poputable();
    }
    
}
