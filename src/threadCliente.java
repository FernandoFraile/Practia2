

public class threadCliente extends Thread {

    //Variables
    private int numeroOperaciones;
    private MonteCarloInterface h;
    private VariableCompartida n;
    //Constructor
    public threadCliente(String name, int numeroOperaciones, MonteCarloInterface h, VariableCompartida n) {
        super(name);
        this.numeroOperaciones=numeroOperaciones;
        this.h=h;
        this.n=n;
    }
    //Ejecucion
    @Override
    public void run() {
        int nAux;
        try{
            nAux=h.npuntos(numeroOperaciones);
            n.Sumar(nAux);

        }catch (Exception e) {
            System.out.println("Exception in cliente: " + e);
        }

    }

}
