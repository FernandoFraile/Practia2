
import java.rmi.*;



public interface MonteCarloInterface extends Remote {


    public int npuntos(long NumeroPares)
            throws java.rmi.RemoteException;

}