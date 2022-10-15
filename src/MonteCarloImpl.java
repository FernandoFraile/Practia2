import java.rmi.*;
import java.rmi.server.*;
import java.util.Random;
import java.util.Date;




public class MonteCarloImpl extends UnicastRemoteObject
        implements MonteCarloInterface{

    public MonteCarloImpl() throws RemoteException {
        super( );
    }


    @Override
    public int npuntos(long NumeroPares) throws RemoteException {
        Date semilla= new Date();
        Double par[]=new Double[2];
        int puntos=0; //Variable para ver cuantos puntos caen en el area

        Random rand = new Random(semilla.getTime());

        for(int i=0;i<NumeroPares;i++){
            par[0] = Math.random();
            par[1] = Math.random();
            //System.out.println("Par generado: ("+ new String(par[0].toString())+","+new String(par[1].toString())+")");
            //A continuacion se comprueba si cuadra dentro del area del ciruclo: (x²+y²<=1)
            if((Math.pow(par[0],2)+Math.pow(par[1],2))<=1){
                puntos++;
            }

        }
        System.out.println("El numero de puntos que entra en el area del circulo de radio 1 es: " + puntos);

        return puntos;
    }
}