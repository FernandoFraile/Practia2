import java.io.*;
import java.rmi.*;
import java.util.ArrayList;


public class cliente {

    public static void main(String args[]) {
        try {
            //Declaracion de variables
            int RMIPort;
            String hostName;
            int numeroPares,numeroHilos, paresPorHilo;
            Thread[] ArrayDeHilos;
            VariableCompartida n=new VariableCompartida();
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
            do{
                System.out.println("Introduzca el numero de hilos para realizar la operacion (maximo 4)");
                numeroHilos=Integer.parseInt(br.readLine());
            }while(numeroHilos>4);

           // System.out.println("Introduzca el numero de hilos para realizar el calculo (maximo 8)");
            //  numeroHilos=Integer.parseInt(br.readLine());


            ArrayDeHilos=new Thread[numeroHilos];
            //Se calcula cuantos pares va a calcular cada hilo
            for(int i=0;i<numeroHilos;i++){
                ArrayDeHilos[i]=new threadCliente("Hilo " +(i+1) ,numeroPares/numeroHilos, h,n);
            }
            for(int i=0;i<numeroHilos;i++){
                ArrayDeHilos[i].start();
            }
            //Se espera a que todos los hilos acaben para mostrar el resultado
            for(int i=0;i<numeroHilos;i++){
                ArrayDeHilos[i].join();
            }

            System.out.println("puntos:  " + n.getValor());
            System.out.println("numeroPares:  " + numeroPares);
            float division=  (4*((float)n.getValor()/(float)numeroPares));
            System.out.println("Aproximacion Pi: " + division);



        } // end try
        catch (Exception e) {
            System.out.println("Exception in cliente: " + e);
        }
    } //end main
}//end class