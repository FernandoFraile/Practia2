import java.io.IOException;

public class VariableCompartida {
    private boolean disponible = true;
    private int valor;

    public VariableCompartida (){
        valor= 0;
    }

    public synchronized void Sumar (long sumando) {
        while (!disponible){
            try{
                wait();
            }catch (InterruptedException e){
                System.out.println("Error en el wait: "+ e.getMessage());
            }
        }
        disponible=false;
        valor+=sumando;
        disponible=true;
        notifyAll();
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
