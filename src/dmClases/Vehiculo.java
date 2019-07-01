/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmClases;

/**
 *
 * @author edson
 */
public class Vehiculo {
    private String placas;
    private String marca;
    private String modelo;
    private String color;
    private String año;
    private String aseguradora;
    private String noPolizaSeguro;

    public Vehiculo(){}
    
    public Vehiculo(String placas){
        this.placas = placas;
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Getters and Setters">
    /**
     * @return the placas
     */
    public String getPlacas() {
        return placas;
    }

    /**
     * @param placas the placas to set
     */
    public void setPlacas(String placas) {
        this.placas = placas;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the año
     */
    public String getAño() {
        return año;
    }

    /**
     * @param año the año to set
     */
    public void setAño(String año) {
        this.año = año;
    }

    /**
     * @return the aseguradora
     */
    public String getAseguradora() {
        return aseguradora;
    }

    /**
     * @param aseguradora the aseguradora to set
     */
    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
    }

    /**
     * @return the noPolizaSeguro
     */
    public String getNoPolizaSeguro() {
        return noPolizaSeguro;
    }

    /**
     * @param noPolizaSeguro the noPolizaSeguro to set
     */
    public void setNoPolizaSeguro(String noPolizaSeguro) {
        this.noPolizaSeguro = noPolizaSeguro;
    }
    // </editor-fold>
}
