/*Este código demuestra el uso de hilos en Java creando dos instancias de la clase
EjemploHilo, que extiende Thread. En el método run(), cada hilo ejecuta un bucle que
imprime un mensaje diez veces, indicando el número de ejecución. En el main, ambos
hilos (hilo y hilo2) se inician con start(), permitiendo que ambos se ejecuten de manera
concurrente. Esto provoca que los mensajes de ambos hilos se impriman en el orden que el
sistema operativo los gestione, de manera intercalada.*/

public class EjemploHilo  extends Thread{
    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println("Ejecutando hilo " + i);
        }
    }

    public static void main(String[] args) {
        EjemploHilo hilo = new EjemploHilo();
        EjemploHilo hilo2= new EjemploHilo();
        hilo.start();

        hilo2.start();
    }
}