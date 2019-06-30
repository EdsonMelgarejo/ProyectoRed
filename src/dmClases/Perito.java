/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmClases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import servertransito.Conexion;

/**
 *
 * @author edson
 */
public class Perito {
    private int id;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String cargo;
    private String usuario;
    private String password;
    private int rolUsuario;

    public Perito(){
    }
    
    public Perito(String nombre){
        this.nombre = nombre;
    }
            
    public Perito(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public Perito(String usuario, String password, int rolUsuario) {
        this.usuario = usuario;
        this.password = password;
        this.rolUsuario = rolUsuario;
    }

    public Perito(String nombre, String apellidoP, String apellidoM, String cargo, 
            String usuario, String password, int rol) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.cargo = cargo;
        this.usuario = usuario;
        this.password = password;
        this.rolUsuario = rol;
    }

    public Perito(int id, String nombre, String apellidoP, String apellidoM, String cargo, String usuario, int rolUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.cargo = cargo;
        this.usuario = usuario;
        this.rolUsuario = rolUsuario;
    }
    
    
    //Metodo que valida el login del perito (Principal)
    public int validarLogin(){
        Perito peritoSelect;
        Conexion conn = new Conexion();
        ResultSet rs;
        String sql = "SELECT `usuario`,`password`,`rolUsuario` FROM `perito` WHERE `usuario` = '"+this.usuario+"';";
        rs = conn.consultar(sql);
        try {
            rs.next();
            peritoSelect = new Perito(rs.getString(1), rs.getString(2), rs.getInt(3));
            
            if(this.password.equals(peritoSelect.getPassword())){
                if(peritoSelect.getRolUsuario() == -1){
                    return -1; //Usuario Administrador
                } else {
                    return 0; //Usuario estandar
                }
            } else {
                return 3; //Error de Password
            }
        } catch(SQLException ex){
            System.err.println(ex);
            return 4; // Usuario inexistente
        } finally {
            conn.cerrar();
        }
    }
    
    //Metodo que registra un perito (Principal)
    public int registrarPerito(){
        int resp; //0 correcto // 1 incorrecto // 3 perito registrado
            if(validarPerito() == 0){
                Conexion conn = new Conexion();
                String sql = "INSERT INTO `perito`(`nombre`, `apellidoP`, `apellidoM`, "
                    + "`cargo`, `usuario`, `password`, `rolUsuario`) "
                    + "VALUES ("
                    + "'"+this.nombre+"',"
                    + "'"+this.apellidoP+"',"
                    + "'"+this.apellidoM+"',"
                    + "'"+this.cargo+"',"
                    + "'"+this.usuario+"',"
                    + "'"+this.password+"',"
                    + ""+this.rolUsuario+")";
            resp = conn.ejecutar(sql);
            conn.cerrar();
            } else {
                resp =  3;
            }
        return resp;
    }
    
    private int validarPerito(){
        int resp;
        Conexion conn = new Conexion();
        ResultSet rs;
        System.out.println(this.usuario);
        String sql = "SELECT `usuario` FROM `perito` WHERE `usuario` = '"+this.usuario+"'";
        rs = conn.consultar(sql);
        try {
            if(rs.next() == false){
                System.out.println("vacio");
                resp = 0;
            } else {
                resp = 1;
            }
        } catch(SQLException ex){
            System.err.println(ex);
            resp = 0;
        } finally {
            conn.cerrar();
        }
        return resp;
    }
    
    //Metodo que recupera todos los peritos (Principal)
    public List<String> getTodos(){
        List<String> list = new ArrayList<>();
        Conexion conn = new Conexion();
        ResultSet rs;
        String sql = "SELECT `idPerito`,`nombre`,`apellidoP`,`apellidoM`,`cargo`,`usuario`,`rolUsuario` FROM `perito`";
        rs = conn.consultar(sql);
        try{
            while(rs.next()){
                String perito = ""+rs.getInt(1)+":"
                        +rs.getString(2)+":"
                        +rs.getString(3)+":"
                        +rs.getString(4)+":"
                        +rs.getString(5)+":"
                        +rs.getString(6)+":"
                        +rs.getInt(7);
                list.add(perito);
            }
        } catch(SQLException e){
            System.err.println(e);
        } finally {
            conn.cerrar();
        }
        return list;
    }

    public int actualizarPerito(){
        int resp;  //0 correcto // 1 incorrecto
        Conexion conn = new Conexion();
        String sql = "UPDATE `perito` SET "
                + "`nombre`='"+this.nombre+"',"
                + "`apellidoP`='"+this.apellidoP+"',"
                + "`apellidoM`='"+this.apellidoM+"',"
                + "`cargo`='"+this.cargo+"',"
                + "`usuario`='"+this.usuario+"',"
                + "`rolUsuario`="+this.rolUsuario+" "
                + "WHERE `idPerito` = " + this.id;
        resp = conn.ejecutar(sql);
        conn.cerrar();
        return resp;
    }
            
//-------------------------------------GETTERS AND SETTERS----------------------
    // <editor-fold defaultstate="collapsed" desc=" Getters and Setters">
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the apellidoP
     */
    public String getApellidoP() {
        return apellidoP;
    }

    /**
     * @param apellidoP the apellidoP to set
     */
    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    /**
     * @return the apellidoM
     */
    public String getApellidoM() {
        return apellidoM;
    }

    /**
     * @param apellidoM the apellidoM to set
     */
    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

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
     * @return the rolUsuario
     */
    public int getRolUsuario() {
        return rolUsuario;
    }

    /**
     * @param rolUsuario the rolUsuario to set
     */
    public void setRolUsuario(int rolUsuario) {
        this.rolUsuario = rolUsuario;
    }
    // </editor-fold>
}
