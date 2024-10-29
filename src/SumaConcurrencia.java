
/*
Este código es un ejemplo de concurrencia utilizando hilos para dividir la tarea
de sumar un arreglo grande. La clase SumaConcurrencia extiende Thread y calcula la
suma parcial de un segmento del arreglo. En el main, se inicializan varios hilos
(numHilos), cada uno sumando una porción del arreglo, y se inician en paralelo.
Tras esperar que cada hilo complete (join()), se suman los resultados parciales para
obtener la suma total, midiendo el tiempo de ejecución.*/

public class SumaConcurrencia extends Thread {
    private int[] array;
    private int suma;
    private int inicio;
    private int fin;

    public SumaConcurrencia(int[] array, int inicio, int fin) {
        this.array = array;
        this.inicio = inicio;
        this.fin = fin;
    }

    public void run() {
        suma=0;
        for (int i = inicio; i < fin; i++) {
            suma += array[i];
        }
    }
    public int getSuma() {
        return suma;
    }
    public static void main(String [] args){
        int[]array=new int[100000000];
        for(int i=0;i<array.length;i++){
            array[i]=i;
        }
        int numHilos=2;
        SumaConcurrencia[] hilos=new SumaConcurrencia[numHilos];
        int tamañoPorHilo=array.length/numHilos;
        long iniciar=System.currentTimeMillis();

        //crear los hilos
        for(int i=0;i<numHilos;i++){
            int inicio=i*tamañoPorHilo;
            int fin=(i+1)*tamañoPorHilo;
            if(i==numHilos-1){
                fin=array.length;
            }
            hilos[i]=new SumaConcurrencia(array,inicio,fin);
            hilos[i].start();
        }
        //esperar a que terminen los hilos
        for(int i=0;i<numHilos;i++){
            try{
                hilos[i].join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        //sumar los resultados
        int sumaTotal=0;
        long fin=System.currentTimeMillis();
        for(int i=0;i<numHilos;i++){
            sumaTotal+=hilos[i].getSuma();
        }
        System.out.println("La suma total es: "+sumaTotal);
        System.out.println("Tiempo de ejecución: "+(fin-iniciar)+"ms");

    }
}//cierra clase