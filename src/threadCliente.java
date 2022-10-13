import java.rmi.Naming;

public class threadCliente extends Thread {

    //Variables
    private long numeroOperaciones;
    private String registro;
    private VariableCompartida n;
    //Constructor
    public threadCliente(String name, long numeroOperaciones, String resgistro, VariableCompartida n) {
        super(name);
        this.numeroOperaciones=numeroOperaciones;
        this.registro=resgistro;
        this.n=n;
    }
    //Ejecucion
    @Override
    public void run() {
        long nAux;
        try{
            MonteCarloInterface h =
                    (MonteCarloInterface) Naming.lookup(registro);
            System.out.println("Registro del hilo "+registro+" completado" );
            nAux=h.npuntos(numeroOperaciones);
            n.Sumar(nAux);

        }catch (Exception e) {
            System.out.println("Exception in cliente: " + e);
        }

    }

}
