package ej_04_semaforo;

import java.util.concurrent.Semaphore;

public class Comprador extends Thread {
    private Semaphore semaforo;
    private Integer num_entradas = 0;

    public Comprador(Semaphore s) {
        this.semaforo = s;
    }

    @Override
    public void run() {
        while (true) {
            ejecucion();
        }
    }

    private void ejecucion() {
        try {
            int numero_entradas = (int) ((Math.random() * 5) + 1);

            synchronized (semaforo) {
                semaforo.acquire(numero_entradas);
                System.out.println("Comprador " + this.getId() + " compra " + numero_entradas + " entradas, quedan "
                        + semaforo.availablePermits() + " entradas");
                this.num_entradas += numero_entradas;
            }

            sleep(100);

            synchronized (semaforo) {
            	System.out.println(num_entradas);
                int numero_entradas_vender = (int) (Math.random() * num_entradas);
                semaforo.release(numero_entradas_vender);
                System.out.println("Comprador " + this.getId() + " vende " + numero_entradas_vender
                        + " entradas , quedan " + semaforo.availablePermits() + " entradas");
                this.num_entradas -= numero_entradas_vender;
            }
        } catch (InterruptedException e) {
            System.err.println("Error en ejecución");
        }
    }
}
