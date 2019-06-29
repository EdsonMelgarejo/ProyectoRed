package servertransito.IServices;

import dmClases.*;
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
}
