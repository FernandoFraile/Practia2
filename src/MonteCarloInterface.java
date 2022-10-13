
import java.rmi.*;
import java.util.ArrayList;


public interface MonteCarloInterface extends Remote {


    public int npuntos(long NumeroPares)
            throws java.rmi.RemoteException;

} //end interface