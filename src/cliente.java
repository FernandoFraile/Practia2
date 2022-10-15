import java.io.*;
import java.rmi.*;
import java.util.ArrayList;


public class cliente {

    public static void main(String args[]) {
        try {
            //Declaracion de variables
            int RMIPort;
            String hostName;
            long numeroPares, resto;
            int numeroHilos;
            Thread[] ArrayDeHilos;
            VariableCompartida n=new VariableCompartida();

            //Fin de declaracion de variable

            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            //Se empieza el método de MonteCarlo
            System.out.println("Introduzca el numero de pares generados para efectuar el metodo de MonteCarlo");
            numeroPares=Long.parseLong(br.readLine());

            System.out.println("Introduzca el numero de hilos para realizar la operacion ");
            numeroHilos=Integer.parseInt(br.readLine());
            ArrayDeHilos=new Thread[numeroHilos];

            //Se ve el resto de la division de numeroPares/numeroHilos, ya que puede no ser entera
            resto=Math.floorMod( numeroPares, numeroHilos);

            for(int i=0;i<numeroHilos; i++){
                System.out.println("Introduce el nombre del Host del registro RMI, para el hilo: "+i);
                hostName = br.readLine();
                System.out.println("Introduce el puerto para el registro RMI");
                String portNum = br.readLine();
                String registryURL =
                        "rmi://" + hostName+ ":" + portNum + "/MonteCarlo";
                // find the remote object and cast it to an interface object
                if(resto>0){
                    ArrayDeHilos[i]=new threadCliente("Hilo " +(i+1) ,numeroPares/numeroHilos+1, registryURL,n);
                    resto--;
                }
                else{
                    ArrayDeHilos[i]=new threadCliente("Hilo " +(i+1) ,numeroPares/numeroHilos, registryURL,n);

                }

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