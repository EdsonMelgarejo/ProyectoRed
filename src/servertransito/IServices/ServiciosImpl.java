package servertransito.IServices;

import dmClases.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.thrift.TException;

/**
 *
 * @author edson
 */
public class ServiciosImpl implements Servicios.Iface{

    @Override
    public int IniciarSesion(String usuario, String password) throws TException {
        int resp;
        Perito perito = new Perito(usuario, password);
        resp = perito.validarLogin();
        return resp;
    }

    @Override
    public int RegistrarPerito(String nombre, String apellidoP, String apellidoM, 
            String cargo, String usuario, String password, int rol) throws TException {
        int resp;
        Perito perito = new Perito(nombre, apellidoP, apellidoM, cargo, usuario, password, rol);
        resp = perito.registrarPerito();
        return resp;
    }

    @Override
    public List<String> RecuperarPeritos() throws TException {
        List<String> peritos = new ArrayList<>();
        Perito perito = new Perito();
        peritos = perito.getTodos();
        return peritos;
    }

    @Override
    public int ActualizarPerito(int idPerito, String nombre, String apellidoP, String apellidoM, String cargo, String usuario, int rol) throws TException {
        int resp;
        Perito perito = new Perito(idPerito,nombre,apellidoP,apellidoM,cargo,usuario,rol);
        resp = perito.actualizarPerito();
        return resp;
    }

    @Override
    public List<String> RecuperarReportes() throws TException {
        List<String> reportes = new ArrayList<>();
        Reporte rep = new Reporte();
        reportes = rep.recuperarReportes();
        return reportes;
    }

    @Override
    public int DictaminarReporte(int idReporte, int dictamen) throws TException {
        Reporte reporte = new Reporte(idReporte);
        reporte.dictaminarReporte(dictamen);
        return 0;
    }
}
