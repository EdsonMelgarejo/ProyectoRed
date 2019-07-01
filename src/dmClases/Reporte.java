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
import java.util.logging.Level;
import java.util.logging.Logger;
import servertransito.Conexion;

/**
 *
 * @author edson
 */
public class Reporte {
    private int idReporte;
    private String titulo;
    private String ciudad;
    private String fechaHora;
    private String descripcion;
    private String direccion;
    private Conductor conductor1;
    private Vehiculo vehiculoC1;
    private Conductor conductor2;
    private int dictaminado;
    
    public Reporte(){}
    
    public Reporte(int id){
        this.idReporte = id;
    }

    public Reporte(int idReporte, String titulo, String ciudad, String fechaHora, 
            String descripcion, String direccion, int idConductor1, String placas, int idConductor2, int dictaminado) {
        this.idReporte = idReporte;
        this.titulo = titulo;
        this.ciudad = ciudad;
        this.fechaHora = fechaHora;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.conductor1 = new Conductor(idConductor1);
        this.vehiculoC1 = new Vehiculo(placas);
        this.conductor2 = new Conductor(idConductor2);
        this.dictaminado = dictaminado;
    }
    
    //Metodo que regresa la lista con los Reportes (Principal)
    public List<String> recuperarReportes(){
        List<String> list = new ArrayList<>();
        List<Reporte> reporte = generarReportes();
        String rep;
        for (int i = 0; i < reporte.size(); i++) {
            rep = reporte.get(i).getIdReporte()+ ":" +
                    reporte.get(i).getTitulo()+ ":" +
                    reporte.get(i).getCiudad()+ ":" +
                    reporte.get(i).getFechaHora()+ ":" +
                    reporte.get(i).getDescripcion()+ ":" +
                    reporte.get(i).getDireccion()+ ":" +
                    reporte.get(i).getConductor1().getIdConductor()+ ":" +
                    reporte.get(i).getConductor1().getNombre()+ ":" +
                    reporte.get(i).getConductor1().getApellidoP()+ ":" +
                    reporte.get(i).getConductor1().getApellidoM()+ ":" +
                    reporte.get(i).getConductor1().getNoLicencia()+ ":" +
                    reporte.get(i).getVehiculoC1().getPlacas()+ ":" +
                    reporte.get(i).getVehiculoC1().getMarca()+ ":" +
                    reporte.get(i).getVehiculoC1().getModelo()+ ":" +
                    reporte.get(i).getVehiculoC1().getColor()+ ":" +
                    reporte.get(i).getVehiculoC1().getAño()+ ":" +
                    reporte.get(i).getVehiculoC1().getAseguradora()+ ":" +
                    reporte.get(i).getVehiculoC1().getNoPolizaSeguro()+ ":" +
                    reporte.get(i).getConductor2().getIdConductor()+ ":" +
                    reporte.get(i).getConductor2().getNombre()+ ":" +
                    reporte.get(i).getConductor2().getApellidoP()+ ":" +
                    reporte.get(i).getConductor2().getApellidoM()+ ":" +
                    reporte.get(i).getConductor2().getNoLicencia()+ ":" +
                    reporte.get(i).getDictaminado();
            list.add(rep);
        }
        return list;
    }
    
    private List<Reporte> generarReportes(){
        List<Reporte> reportes = new ArrayList<>();
        
        Conexion conn = new Conexion();
        ResultSet rs;
        String sql = "SELECT * FROM `reporte`";
        rs = conn.consultar(sql);
        try {
            while(rs.next()){
                reportes.add(
                    new Reporte(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getString(6),rs.getInt(7), rs.getString(8),rs.getInt(9), rs.getInt(10))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            conn.cerrar();
        }
        
        try {
            reportes = recuperarDatosConductorVehiculo1(reportes);
            reportes = recuperarDatosConductor2(reportes);
        } catch (SQLException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reportes;
    }
    
    private List<Reporte> recuperarDatosConductorVehiculo1(List<Reporte> reportes) throws SQLException{
        List<Reporte> list = reportes;
        Conexion conn = new Conexion();
        ResultSet rs;
        String sql;
        for (int i = 0; i < list.size(); i++) {
            sql = "SELECT conductor.nombre, conductor.apellidoP, conductor.apellidoM, "
                + "conductor.noLicencia, vehiculo.marca, vehiculo.modelo, vehiculo.color, "
                + "vehiculo.año, vehiculo.aseguradora, vehiculo.noPolizaSeguro "
                + "FROM conductor, vehiculo "
                + "WHERE conductor.idConductor = "+list.get(i).getConductor1().getIdConductor()+" "
                + "AND vehiculo.placas = '"+list.get(i).getVehiculoC1().getPlacas()+"'";
            rs = conn.consultar(sql);
            rs.next();
            list.get(i).getConductor1().setNombre(rs.getString(1));
            list.get(i).getConductor1().setApellidoP(rs.getString(2));
            list.get(i).getConductor1().setApellidoM(rs.getString(3));
            list.get(i).getConductor1().setNoLicencia(rs.getString(4));
            list.get(i).getVehiculoC1().setMarca(rs.getString(5));
            list.get(i).getVehiculoC1().setModelo(rs.getString(6));
            list.get(i).getVehiculoC1().setColor(rs.getString(7));
            list.get(i).getVehiculoC1().setAño(rs.getString(8));
            list.get(i).getVehiculoC1().setAseguradora(rs.getString(9));
            list.get(i).getVehiculoC1().setNoPolizaSeguro(rs.getString(10));
        }
        conn.cerrar();
        return list;
    }

    private List<Reporte> recuperarDatosConductor2(List<Reporte> reportes) throws SQLException{
        List<Reporte> list = reportes;
        Conexion conn = new Conexion();
        ResultSet rs;
        String sql;
        for (int i = 0; i < list.size(); i++) {
            sql = "SELECT conductor.nombre, conductor.apellidoP, conductor.apellidoM, conductor.noLicencia "
                + "FROM conductor "
                + "WHERE conductor.idConductor = "+list.get(i).getConductor2().getIdConductor();
            rs = conn.consultar(sql);
            rs.next();
            list.get(i).getConductor2().setNombre(rs.getString(1));
            list.get(i).getConductor2().setApellidoP(rs.getString(2));
            list.get(i).getConductor2().setApellidoM(rs.getString(3));
            list.get(i).getConductor2().setNoLicencia(rs.getString(4));
        }
        conn.cerrar();
        return list;
    }
    
    public int dictaminarReporte(int dictamen){
        Conexion conn = new Conexion();
        String sql = "UPDATE `reporte` SET `dictaminado`= "+dictamen+" WHERE `idReporte` = "+ this.idReporte;
        int resp = conn.ejecutar(sql);
        return resp;
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Getters and Setters">
    /**
     * @return the idReporte
     */
    public int getIdReporte() {
        return idReporte;
    }

    /**
     * @param idReporte the idReporte to set
     */
    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
     * @return the fechaHora
     */
    public String getFechaHora() {
        return fechaHora;
    }

    /**
     * @param fechaHora the fechaHora to set
     */
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
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
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the conductor1
     */
    public Conductor getConductor1() {
        return conductor1;
    }

    /**
     * @param conductor1 the conductor1 to set
     */
    public void setConductor1(Conductor conductor1) {
        this.conductor1 = conductor1;
    }

    /**
     * @return the vehiculoC1
     */
    public Vehiculo getVehiculoC1() {
        return vehiculoC1;
    }

    /**
     * @param vehiculoC1 the vehiculoC1 to set
     */
    public void setVehiculoC1(Vehiculo vehiculoC1) {
        this.vehiculoC1 = vehiculoC1;
    }

    /**
     * @return the conductor2
     */
    public Conductor getConductor2() {
        return conductor2;
    }

    /**
     * @param conductor2 the conductor2 to set
     */
    public void setConductor2(Conductor conductor2) {
        this.conductor2 = conductor2;
    }

    /**
     * @return the dictaminado
     */
    public int getDictaminado() {
        return dictaminado;
    }

    /**
     * @param dictaminado the dictaminado to set
     */
    public void setDictaminado(int dictaminado) {
        this.dictaminado = dictaminado;
    }
    // </editor-fold>
}
