/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import javax.swing.table.DefaultTableModel;
import sax.DBConnection;

/**
 *
 * @author megam
 */
public class ModelProductos {
    public DBConnection conection = new DBConnection(3306,"localhost", "acme", "root", "1234");
    public DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Id_producto","Producto","Descripcion","Precio_compra","Precio_venta","Existencias"},0);
    
    private int id_producto;
    private String producto;
    private String descripcion;
    private double precio_compra;
    private double precio_venta;
    private int existencias;
    
    /**
     * @return the id_producto
     */
    public int getId_producto() {
        return id_producto;
    }

    /**
     * @param id_producto the id_producto to set
     */
    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    /**
     * @return the producto
     */
    public String getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(String producto) {
        this.producto = producto;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the precio_compra
     */
    public double getPrecio_compra() {
        return precio_compra;
    }

    /**
     * @param precio_compra the precio_compra to set
     */
    public void setPrecio_compra(int precio_compra) {
        this.precio_compra = precio_compra;
    }

    /**
     * @return the precio_venta
     */
    public double getPrecio_venta() {
        return precio_venta;
    }

    /**
     * @param precio_venta the precio_venta to set
     */
    public void setPrecio_venta(int precio_venta) {
        this.precio_venta = precio_venta;
    }

    /**
     * @return the existencias
     */
    public int getExistencias() {
        return existencias;
    }

    /**
     * @param existencias the existencias to set
     */
    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }
    
    public void moveNext(){
        conection.moveNext();
        setValues();
    }
    
    public void initValues(){
        conection.executeQuery("SELECT id_producto, producto, descripcion, precio_compra, precio_venta, existencias FROM productos;");
        poputable();
        conection.moveNext();
        setValues();
    }
    
    public void setValues(){
        id_producto = conection.getInteger("id_producto");
        producto = conection.getString("producto");
        descripcion = conection.getString("descripcion");
        precio_compra = conection.getDouble("precio_compra");
        precio_venta = conection.getDouble("precio_venta");
        existencias = conection.getInteger("existencias");
    }
    
    public void poputable(){
    while(conection.moveNext()){
            setValues();
            tableModel.addRow(new Object[]{id_producto, producto, descripcion, precio_compra, precio_venta, existencias});
        }
    }
}
