package models;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sax.DBConnection;

public class ModelProveedores {
    public DBConnection conection = new DBConnection(3307,"localhost", "acme", "root", "1234");
    public DefaultTableModel tableModel = new DefaultTableModel(new String[]{"id_proveedor","nombre","RFC","calle","No","colonia","ciudad","estado","nombre_contacto","telefono","e_mail"},0);
    
    private Connection conn;
    private int id_proveedor;
    private String nombre;
    private String RFC;
    private String calle;
    private int No;
    private String colonia;
    private String ciudad;
    private String estado;
    private String nombre_contacto;
    private int telefono;
    private String e_mail;
    private String name;

    /**
     * @return the id_proveedor
     */
    public int getId_proveedor() {
        return id_proveedor;
    }

    /**
     * @param id_proveedor the id_proveedor to set
     */
    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the RFC
     */
    public String getRFC() {
        return RFC;
    }

    /**
     * @param RFC the RFC to set
     */
    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    /**
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * @param calle the calle to set
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * @return the No
     */
    public int getNo() {
        return No;
    }

    /**
     * @param No the No to set
     */
    public void setNo(int No) {
        this.No = No;
    }

    /**
     * @return the colonia
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * @param colonia the colonia to set
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the nombre_contacto
     */
    public String getNombre_contacto() {
        return nombre_contacto;
    }

    /**
     * @param nombre_contacto the nombre_contacto to set
     */
    public void setNombre_contacto(String nombre_contacto) {
        this.nombre_contacto = nombre_contacto;
    }

    /**
     * @return the telefono
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the e_mail
     */
    public String getE_mail() {
        return e_mail;
    }

    /**
     * @param e_mail the e_mail to set
     */
    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public void moveNext(){
        conection.moveNext();
        setValues();
        poputable();
    }
    
    public void movePrevious(){
        conection.movePrevious();
        setValues();
        poputable();
    }
    
    public void moveFirst(){
        conection.moveFirst();
        setValues();
        poputable();
    }
    
    public void moveLast(){
        conection.moveLast();
        setValues();
        poputable();
    }
    
    public void initValues(){
        conection.executeQuery("SELECT * FROM proveedores;");
        poputable();
        conection.moveNext();
        setValues();
    }
    
    public void accionBuscar(){
        conection.executeQuery(" select * from proveedores where nombre='"+getName()+"'");
        conection.moveNext();
        setValues();
    }
    
    public void setValues(){
        id_proveedor = conection.getInteger("id_proveedor");
        nombre = conection.getString("nombre");
        RFC = conection.getString("RFC");
        calle = conection.getString("calle");
        No = conection.getInteger("No");
        colonia = conection.getString("colonia");
        ciudad = conection.getString("ciudad");
        estado = conection.getString("estado");
        nombre_contacto = conection.getString("nombre_contacto");
        telefono = conection.getInteger("telefono");
        e_mail = conection.getString("e_mail");
    }
    
    public void poputable(){
    while(conection.moveNext()){
            setValues();
            tableModel.addRow(new Object[]{id_proveedor, nombre, RFC, calle, No, colonia, ciudad, estado, nombre_contacto, telefono, e_mail});
        }
    }
    
    public void ingresar() {
        String add = "INSERT INTO `acme`.`proveedores` (`nombre`, `RFC`, `calle`, `No`, `colonia`, `ciudad`, `estado`, `nombre_contacto`, `telefono`, `e_mail`) VALUES "
                + "('"+nombre+"', '"+RFC+"', '"+calle+"', '"+No+"', '"+colonia+"', '"+ciudad+"', '"+estado+"', '"+nombre_contacto+"', '"+telefono+"', '"+e_mail+"');";
        conection.executeUpdate(add);        
        initValues();
    }
    public Connection getConn() {
        return conn;
    }
}
