import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;




public class MonteCarloImpl extends UnicastRemoteObject
        implements MonteCarloInterface{

    public MonteCarloImpl() throws RemoteException {
        super( );
    }


    @Override
    public int npuntos(int NumeroPares) throws RemoteException {
        Date semilla= new Date();
        Float par[]=new Float[2];
        int puntos=0; //Variable para ver cuantos puntos caen en el area
        float min_val=0, max_val=1;

        Random rand = new Random(semilla.getTime());

        for(int i=0;i<NumeroPares;i++){
            par[0] = min_val + rand.nextFloat((max_val - min_val) );
            par[1] = min_val + rand.nextFloat((max_val - min_val));
            System.out.println("Par generado: ("+ new String(par[0].toString())+","+new String(par[1].toString())+")");
            //A continuacion se comprueba si cuadra dentro del area del ciruclo: (x²+y²<=1)
            if((Math.pow(par[0],2)+Math.pow(par[1],2))<=1){
                puntos++;
            }

        }
        System.out.println("El numero de puntos es: " + puntos);

        return puntos;
    }
}