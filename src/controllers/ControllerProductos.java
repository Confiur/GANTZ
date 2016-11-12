/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ModelProductos;
import views.*;
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
    viewEliminar viewEliminar = new viewEliminar();
    ViewEditar viewEditar = new ViewEditar();
    ViewBuscar viewBuscar = new ViewBuscar();
    Libreria libreria = new Libreria();
    
    public ControllerProductos(ModelProductos modelProductos, ViewProductos viewProductos){
        this.viewProductos = viewProductos;
        this.modelProductos = modelProductos;
        
        this.viewProductos.jbtn_agregar.addActionListener(this);
        this.viewProductos.jbtn_eliminar.addActionListener(this);
        this.viewProductos.jbtn_editar.addActionListener(this);
        this.viewProductos.jbtn_buscar.addActionListener(this);
        initView();
        showRecords();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==viewProductos.jbtn_agregar){
            accionAgregar();
        }
        else if (e.getSource()==viewProductos.jbtn_eliminar){
            accionEliminar();
        }
        else if (e.getSource()==viewProductos.jbtn_editar){
            accionEditar();
        }
        else if (e.getSource()==viewProductos.jbtn_buscar){
            accionBuscar();
        }
    }
    
    public void accionAgregar(){//se agrega un registro a la BD
        int dialog = JOptionPane.showConfirmDialog(viewProductos, viewAgregar);
        if (dialog == 0){
            if (viewAgregar.jtf_compra.getValue() == null || viewAgregar.jtf_venta.getValue() == null || viewAgregar.jtf_cantidad.getValue() == null){
                viewAgregar.jtf_nombre.setText("");
                viewAgregar.jta_descripcion.setText("");
                viewAgregar.jtf_compra.setText("");
                viewAgregar.jtf_venta.setText("");
                viewAgregar.jtf_cantidad.setText("");
                JOptionPane.showMessageDialog(viewProductos,"Porfavor, ingrese los valores correctos");
            }
            else{
                modelProductos.setProducto(viewAgregar.jtf_nombre.getText());
                modelProductos.setDescripcion(viewAgregar.jta_descripcion.getText());
                modelProductos.setPrecio_compra(libreria.stringToInt(viewAgregar.jtf_compra.getText()));
                modelProductos.setPrecio_venta(libreria.stringToInt(viewAgregar.jtf_venta.getText()));
                modelProductos.setExistencias(libreria.stringToInt(viewAgregar.jtf_cantidad.getText()));
                modelProductos.conection.executeUpdate("insert into productos (producto, descripcion, precio_compra, precio_venta, existencias) values('"+modelProductos.getProducto()+"','"+modelProductos.getDescripcion()+"','"+modelProductos.getPrecio_compra()+"','"+modelProductos.getPrecio_venta()+"','"+modelProductos.getExistencias()+"');");
                viewAgregar.jtf_nombre.setText("");
                viewAgregar.jta_descripcion.setText("");
                viewAgregar.jtf_compra.setText("");
                viewAgregar.jtf_venta.setText("");
                viewAgregar.jtf_cantidad.setText("");
            clear();
            }
        }
    }
    
    public void accionEliminar(){
        //elimina un registro (producto)
        int dialog = JOptionPane.showConfirmDialog(viewProductos,viewEliminar);
        if (dialog == 0){
            if(viewEliminar.jtf_id.getValue() == null){
                JOptionPane.showMessageDialog(viewProductos,"Porfavor, elimine por medio del ID");
            }
            else{
                modelProductos.setId_producto(libreria.stringToInt(viewEliminar.jtf_id.getText()));
                modelProductos.conection.executeUpdate("DELETE from productos where id_producto ="+modelProductos.getId_producto()+";");
                clear();
                viewEliminar.jtf_id.setText("");      
            }
        }
    }
    
    public void accionEditar(){
            if(viewProductos.jtf_buscar.getValue()==null){
                JOptionPane.showMessageDialog(viewProductos,"La edición solo se hace por medio del ID");
            }
            else{
                modelProductos.setId_producto(libreria.stringToInt(viewProductos.jtf_buscar.getText()));
                accionBu();
                viewEditar.jtf_nombre.setText(""+modelProductos.getProducto());
                viewEditar.jta_descripcion.setText(""+modelProductos.getDescripcion());
                viewEditar.jtf_compra.setText(""+modelProductos.getPrecio_compra());
                viewEditar.jtf_venta.setText(""+modelProductos.getPrecio_venta());
                viewEditar.jtf_cantidad.setText(""+modelProductos.getExistencias());
                viewProductos.jtf_buscar.setText("");
                int dialog = JOptionPane.showConfirmDialog(viewProductos,viewEditar);
                if (dialog == 0){
                modelProductos.setProducto(viewEditar.jtf_nombre.getText());
                modelProductos.setDescripcion(viewEditar.jta_descripcion.getText());
                modelProductos.setPrecio_compra(libreria.stringToInt(viewEditar.jtf_compra.getText()));
                modelProductos.setPrecio_venta(libreria.stringToInt(viewEditar.jtf_venta.getText()));
                modelProductos.setExistencias(libreria.stringToInt(viewEditar.jtf_cantidad.getText()));
                modelProductos.conection.executeUpdate("update productos set producto ='"+modelProductos.getProducto()+"', descripcion ='"+modelProductos.getDescripcion()+"', precio_compra ='"+modelProductos.getPrecio_compra()+"', precio_venta ='"+modelProductos.getPrecio_venta()+"',existencias='"+modelProductos.getExistencias()+"' where id_producto ='"+modelProductos.getId_producto()+"'");
                clear();
                viewEditar.jtf_nombre.setText("");
                viewEditar.jta_descripcion.setText("");
                viewEditar.jtf_compra.setText("");
                viewEditar.jtf_venta.setText("");
                viewEditar.jtf_cantidad.setText("");
            }
        }
    }
    
    public void accionBuscar(){
        if(viewProductos.jtf_buscar.getValue()==null){
            JOptionPane.showMessageDialog(viewProductos,"Porfavor, haga su busqueda por medio del ID");
        }
        else{
            modelProductos.setId_producto(libreria.stringToInt(viewProductos.jtf_buscar.getText()));
            accionBu();
            viewBuscar.jtf_nombre.setText(""+modelProductos.getProducto());
            viewBuscar.jta_descripcion.setText(""+modelProductos.getDescripcion());
            viewBuscar.jtf_compra.setText(""+modelProductos.getPrecio_compra());
            viewBuscar.jtf_venta.setText(""+modelProductos.getPrecio_venta());
            viewBuscar.jtf_cantidad.setText(""+modelProductos.getExistencias());
            viewProductos.jtf_buscar.setText("");
            int dialog = JOptionPane.showConfirmDialog(viewProductos,viewBuscar);
            if (dialog == 0){
            viewBuscar.jtf_nombre.setText("");
            viewBuscar.jta_descripcion.setText("");
            viewBuscar.jtf_compra.setText("");
            viewBuscar.jtf_venta.setText("");
            viewBuscar.jtf_cantidad.setText("");
            }
        }
    }
    
    public void accionBu(){
            modelProductos.conection.executeQuery("select * from productos where id_producto='"+modelProductos.getId_producto()+"'");
            modelProductos.conection.moveNext();
            modelProductos.setValues();
    }
    
    public void clear(){//limpia la tabla jt_table
        while(modelProductos.tableModel.getRowCount() >0){
            modelProductos.tableModel.removeRow(0);
        }
        initView();
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
