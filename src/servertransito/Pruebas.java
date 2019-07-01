package servertransito;

import dmClases.*;
import java.util.List;

/**
 *
 * @author edson
 */
public class Pruebas {
    public static void main(String[] args){
        Reporte reporte = new Reporte();
        List<String> list = reporte.recuperarReportes();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    } 
}
