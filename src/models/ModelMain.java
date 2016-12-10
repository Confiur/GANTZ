/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import sax.DBConnection;

/**
 *
 * @author megam
 */
public class ModelMain {
    public DBConnection conection = new DBConnection(3307,"localhost", "acme", "root", "1234");
    private int id_login;
    private String usuario;
    private String nombre;
    private String apellido_pat;
    private String apellido_mat;
    private String password;
    private String tipo;
    private int conf_id;
    private String conf_usuario;
    private String conf_pass;

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
     * @return the apellido_pat
     */
    public String getApellido_pat() {
        return apellido_pat;
    }

    /**
     * @param apellido_pat the apellido_pat to set
     */
    public void setApellido_pat(String apellido_pat) {
        this.apellido_pat = apellido_pat;
    }

    /**
     * @return the apellido_mat
     */
    public String getApellido_mat() {
        return apellido_mat;
    }

    /**
     * @param apellido_mat the apellido_mat to set
     */
    public void setApellido_mat(String apellido_mat) {
        this.apellido_mat = apellido_mat;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the id_login
     */
    public int getId_login() {
        return id_login;
    }

    /**
     * @param id_login the id_login to set
     */
    public void setId_login(int id_login) {
        this.id_login = id_login;
    }
    
    public void moveNext(){
        conection.moveNext();
        setValues();
    }
    
    public void setValues(){
        conf_usuario = conection.getString("usuario");
        conf_pass = conection.getString("password");
    }
    
    public void setValues2(){
        tipo = conection.getString("tipo");
    }
    
    
    public void initValues2(){
        conection.moveNext();
        setValues2();
    }

    /**
     * @return the conf_id
     */
    public int getConf_id() {
        return conf_id;
    }

    /**
     * @param conf_id the conf_id to set
     */
    public void setConf_id(int conf_id) {
        this.conf_id = conf_id;
    }

    /**
     * @return the conf_usuario
     */
    public String getConf_usuario() {
        return conf_usuario;
    }

    /**
     * @param conf_usuario the conf_usuario to set
     */
    public void setConf_usuario(String conf_usuario) {
        this.conf_usuario = conf_usuario;
    }

    /**
     * @return the conf_pass
     */
    public String getConf_pass() {
        return conf_pass;
    }

    /**
     * @param conf_pass the conf_pass to set
     */
    public void setConf_pass(String conf_pass) {
        this.conf_pass = conf_pass;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
