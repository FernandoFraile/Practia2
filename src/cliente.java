import java.io.*;
import java.rmi.*;
import java.util.ArrayList;


public class cliente {

    public static void main(String args[]) {
        try {
            //Declaracion de variables
            int RMIPort;
            String hostName;
            int numeroPares,numeroHilos, puntos;
            //Fin de declaracion de variables

            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            System.out.println("Enter the RMIRegistry host namer:");
            hostName = br.readLine();
            System.out.println("Enter the RMIregistry port number:");
            String portNum = br.readLine();
            RMIPort = Integer.parseInt(portNum);
            String registryURL =
                    "rmi://" + hostName+ ":" + portNum + "/MonteCarlo";
            // find the remote object and cast it to an interface object
            MonteCarloInterface h =
                    (MonteCarloInterface) Naming.lookup(registryURL);
            System.out.println("Lookup completed " );
            // invoke the remote method
            //Se empieza el método de MonteCarlo
            System.out.println("Introduzca el numero de pares generados para efectuar el metodo de MonteCarlo");
            numeroPares=Integer.parseInt(br.readLine());
           // System.out.println("Introduzca el numero de hilos para realizar el calculo (maximo 8)");
            //  numeroHilos=Integer.parseInt(br.readLine());



            puntos=h.npuntos(numeroPares);
            System.out.println("puntos:  " + puntos);
            System.out.println("numeroPares:  " + numeroPares);
            float division= 4*(puntos/numeroPares);
            System.out.println("Aproximacion Pi: " + division);



        } // end try
        catch (Exception e) {
            System.out.println("Exception in cliente: " + e);
        }
    } //end main
}//end class