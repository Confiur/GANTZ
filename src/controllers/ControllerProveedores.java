package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import models.ModelProveedores;
import views.*;
import libreria.Libreria;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

public class ControllerProveedores implements ActionListener{
    ModelProveedores modelProveedores = new ModelProveedores();
    ViewProveedores viewProveedores = new ViewProveedores();
    viewEliminarProve eliminarProv = new viewEliminarProve();
    viewBuscarProvee buscaProv = new viewBuscarProvee();
    viewProveedoresEditar editarProv = new viewProveedoresEditar();
    String nl= System.getProperty("line.separator");
    Libreria libreria = new Libreria();
    private Connection conn;

    public ControllerProveedores(ModelProveedores modelProveedores, ViewProveedores viewProveedores, viewEliminarProve eliminarProv, viewBuscarProvee buscaProv, viewProveedoresEditar editarProv) {
        this.modelProveedores=modelProveedores;
        this.viewProveedores=viewProveedores;
        this.eliminarProv=eliminarProv;
        this.buscaProv=buscaProv;
        this.editarProv=editarProv;
        
        this.viewProveedores.jb_agregar.addActionListener(this);
        this.viewProveedores.jb_editar.addActionListener(this);
        this.viewProveedores.jb_eliminar.addActionListener(this);
        this.viewProveedores.jb_buscar.addActionListener(this);
        this.viewProveedores.jb_report.addActionListener(this);
        initView();
        showRecords();
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        if(a.getSource()==viewProveedores.jb_agregar){
            modelProveedores.setName(viewProveedores.jtf_nombre.getText());
            modelProveedores.accionBuscar();
            if (modelProveedores.getId_proveedor()==0 && modelProveedores.getNo()==0){
                accionAgregar();
                conAg();
                clear();
            }else{
                JOptionPane.showMessageDialog(viewProveedores,"El proveedor ya existe"+nl+"Verifica en la tabla de abajo");
                viewProveedores.jtf_nombre.setText("");
            }
        }else if (a.getSource()==viewProveedores.jb_eliminar){
            int dialog=JOptionPane.showConfirmDialog(viewProveedores,eliminarProv);
            if (dialog==0){
                accionEliminar();
            }
        }
        else if (a.getSource()==viewProveedores.jb_buscar){
            modelProveedores.setName(viewProveedores.jtf_buscar.getText());
            modelProveedores.accionBuscar();
            if (modelProveedores.getId_proveedor()==0 && modelProveedores.getNo()==0){
                JOptionPane.showMessageDialog(viewProveedores,"El valor buscado no es correcto");
            }else{
                resultados();
                JOptionPane.showMessageDialog(viewProveedores,buscaProv);
            }
        }
        else if (a.getSource()==viewProveedores.jb_editar){
            modelProveedores.setName(viewProveedores.jtf_buscar.getText());
            modelProveedores.accionBuscar();
            if (modelProveedores.getId_proveedor()==0 && modelProveedores.getNo()==0){
                JOptionPane.showMessageDialog(viewProveedores,"El valor buscado no es correcto");
            }else{
                toEdit();
                int dialog=JOptionPane.showConfirmDialog(viewProveedores, editarProv);
                if (dialog==0){
                    edicion();
                }
            }
        }else if (a.getSource()==viewProveedores.jb_report){
            reporte();
        }
    }   
    
    public void conAg(){
        modelProveedores.conection.executeUpdate("INSERT INTO proveedores (nombre, RFC, calle, No, colonia, ciudad, estado, nombre_contacto, telefono, e_mail) VALUES "
            +"('"+modelProveedores.getNombre()+"','"+modelProveedores.getRFC()+"','"+modelProveedores.getCalle()+"','"+modelProveedores.getNo()+"','"+modelProveedores.getColonia()+"','"+modelProveedores.getCiudad()+"','"+modelProveedores.getEstado()+"','"+modelProveedores.getNombre_contacto()+"','"+modelProveedores.getTelefono()+"','"+modelProveedores.getE_mail()+"');");
    }
    
    public void reporte(){
        try {
                conectar();
                String dir = "C:\\Users\\Rodo\\Documents\\EL XIDO\\GANTZ\\GANTZ\\src\\reportes\\proveedoreReporte.jrxml";
                JasperReport reporteJasper = JasperCompileManager.compileReport(dir);
                JasperPrint mostrarReporte = JasperFillManager.fillReport(reporteJasper, null, getConn());
                JasperViewer.viewReport(mostrarReporte, false);
            } catch (Exception e) {
                System.out.println("Error");
            }
    }
    
    public void conectar() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/acme", "root", "1234");
        } finally {
        }
    }
    
    public void accionAgregar(){
            modelProveedores.setNombre(viewProveedores.jtf_nombre.getText());
            modelProveedores.setRFC(viewProveedores.jtf_RFC.getText());
            modelProveedores.setCalle(viewProveedores.jtf_calle.getText());
            modelProveedores.setNo(libreria.stringToInt(viewProveedores.jtf_numero.getText()));
            modelProveedores.setColonia(viewProveedores.jtf_colonia.getText());
            modelProveedores.setCiudad(viewProveedores.jtf_ciudad.getText());
            modelProveedores.setEstado(viewProveedores.jtf_estado.getText());
            modelProveedores.setNombre_contacto(viewProveedores.jtf_contacto.getText());
            modelProveedores.setTelefono(libreria.stringToInt(viewProveedores.jtf_telefono.getText()));
            modelProveedores.setE_mail(viewProveedores.jtf_eMail.getText());
    }
    
    public void clear(){
        viewProveedores.jtf_RFC.setText("");
        viewProveedores.jtf_calle.setText("");
        viewProveedores.jtf_ciudad.setText("");
        viewProveedores.jtf_colonia.setText("");
        viewProveedores.jtf_contacto.setText("");
        viewProveedores.jtf_eMail.setText("");
        viewProveedores.jtf_estado.setText("");
        viewProveedores.jtf_nombre.setText("");
        viewProveedores.jtf_numero.setText(" ");
        viewProveedores.jtf_telefono.setText("");
        
        while(modelProveedores.tableModel.getRowCount() >0){
            modelProveedores.tableModel.removeRow(0);
        }
        initView();
    }
    
    public void initView(){
        viewProveedores.setVisible(true);
        modelProveedores.initValues();
        modelProveedores.setValues();
    }
   
    private void showRecords(){
        viewProveedores.jt_tabla.setModel(modelProveedores.tableModel);
        modelProveedores.poputable();
    }
    
    public void accionEliminar(){
        String id_prov=eliminarProv.jtf_eliminar.getText();
        modelProveedores.conection.executeUpdate("DELETE from proveedores where id_proveedor="+id_prov);
        clear();
    }
    
    public void resultados(){
        buscaProv.jl_idProv.setText(""+modelProveedores.getId_proveedor());
        buscaProv.jl_nombre.setText(modelProveedores.getNombre());
        buscaProv.jl_rfc.setText(modelProveedores.getRFC());
        buscaProv.jl_calle.setText(modelProveedores.getCalle());
        buscaProv.jl_num.setText(""+modelProveedores.getNo());
        buscaProv.jl_colonia.setText(modelProveedores.getColonia());
        buscaProv.jl_ciudad.setText(modelProveedores.getCiudad());
        buscaProv.jl_estado.setText(modelProveedores.getEstado());
        buscaProv.jl_contacto.setText(modelProveedores.getNombre_contacto());
        buscaProv.jl_telefono.setText(""+modelProveedores.getTelefono());
        buscaProv.jl_e_mail.setText(modelProveedores.getE_mail());
    }
    
    public void toEdit(){
        editarProv.jl_id.setText(""+modelProveedores.getId_proveedor());
        editarProv.jtf_name.setText(modelProveedores.getNombre());
        editarProv.jtf_rfc.setText(modelProveedores.getRFC());
        editarProv.JTF_calle.setText(modelProveedores.getCalle());
        editarProv.jtf_num.setText(""+modelProveedores.getNo());
        editarProv.jtf_colonia.setText(modelProveedores.getColonia());
        editarProv.jtf_city.setText(modelProveedores.getCiudad());
        editarProv.jtf_estado.setText(modelProveedores.getEstado());
        editarProv.jtf_nameContact.setText(modelProveedores.getNombre_contacto());
        editarProv.jtf_phone.setText(""+modelProveedores.getTelefono());
        editarProv.jtf_mail.setText(modelProveedores.getE_mail());
    }
    
    public void edicion(){
        modelProveedores.setNombre(editarProv.jtf_name.getText());
        modelProveedores.setRFC(editarProv.jtf_rfc.getText());
        modelProveedores.setCalle(editarProv.JTF_calle.getText());
        modelProveedores.setNo(libreria.stringToInt(editarProv.jtf_num.getText()));
        modelProveedores.setColonia(editarProv.jtf_colonia.getText());
        modelProveedores.setCiudad(editarProv.jtf_city.getText());
        modelProveedores.setEstado(editarProv.jtf_estado.getText());
        modelProveedores.setNombre_contacto(editarProv.jtf_nameContact.getText());
        modelProveedores.setTelefono(libreria.stringToInt(editarProv.jtf_phone.getText()));
        modelProveedores.setE_mail(editarProv.jtf_mail.getText());
        modelProveedores.conection.executeUpdate("UPDATE proveedores "
                + "set nombre='"+modelProveedores.getNombre()+"', RFC='"+modelProveedores.getRFC()+"',calle='"+modelProveedores.getCalle()+"',No='"+modelProveedores.getNo()+"',colonia='"+modelProveedores.getColonia()+"',ciudad='"+modelProveedores.getCiudad()+"',estado='"+modelProveedores.getEstado()+"',nombre_contacto='"+modelProveedores.getNombre_contacto()+"',telefono='"+modelProveedores.getTelefono()+"',e_mail='"+modelProveedores.getE_mail()+"'"
                + " WHERE id_proveedor="+modelProveedores.getId_proveedor()+";");
        clear();
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}