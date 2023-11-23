package ej12_Fabrica_munyecas;

public class Principal {

    public static void main(String[] args) {
        Carro carro = new Carro();
        Trabajador[] trabajadores = new Trabajador[2];

        for (int i = 0; i < 2; i++) {
            trabajadores[i] = new Trabajador(carro);
            trabajadores[i].start();
        }

        while (true) {
            synchronized (carro) {
                while (carro.getNumero_munyecas() < carro.getMAX_MUNYECAS_EN_CARRO()) {
                    int espacio_disponible = carro.getMAX_MUNYECAS_EN_CARRO() - carro.getNumero_munyecas();
                    int munyecasAAgregar = (int) (Math.random() * Math.min(5, espacio_disponible)) + 1;
                    carro.setNumero_munyecas(carro.getNumero_munyecas() + munyecasAAgregar);
                    carro.notifyAll();
                }
            }
        }
    }
}
