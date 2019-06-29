package servertransito;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import servertransito.IServices.*;

/**
 *
 * @author edson
 */
public class ServerTransito {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            TServerSocket socketServidor = new TServerSocket(8000);
            Servicios.Processor procesor = new Servicios.Processor(new ServiciosImpl());
            TServer servidor = new TThreadPoolServer(new TThreadPoolServer.Args(socketServidor).processor(procesor));
            System.out.println("Servidor Iniciado");
            servidor.serve();
            ServiciosImpl serv = new ServiciosImpl();
        } catch (TTransportException ex) {
            Logger.getLogger(ServerTransito.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
