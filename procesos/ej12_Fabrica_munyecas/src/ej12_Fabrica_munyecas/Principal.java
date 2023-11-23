package ej12_Fabrica_munyecas;

public class Principal {

    public static void main(String[] args) {
        Carro carro = new Carro();
        carro.start();
        Trabajador[] trabajadores = new Trabajador[2];

        for (int i = 0; i < 2; i++) {
            trabajadores[i] = new Trabajador(carro);
            trabajadores[i].start();
        }
    }
}
