import java.util.concurrent.RecursiveTask;
/*
Este código es un ejemplo de paralelismo utilizando la clase RecursiveTask de Java para
realizar una suma en un gran arreglo de forma paralela. La clase EjemploParalelismo
divide el arreglo en segmentos cada vez más pequeños hasta alcanzar un umbral
(umbral = 1000), momento en el cual cada segmento se suma de manera secuencial.
Si el segmento es grande, la tarea se divide en dos subtareas: tareaIzquierda y
tareaDerecha. Usando fork() y join(), cada subtarea se ejecuta en paralelo,
aprovechando varios núcleos para acelerar la ejecución de la suma.*/
public class EjemploParalelismo extends RecursiveTask<Integer>
{
    private int[] array;
    private int inicio;
    private int fin;
    private int umbral = 1000;

    public EjemploParalelismo(int[] array, int inicio, int fin)
    {
        this.array = array;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    protected Integer compute()
    {
        if (fin - inicio <= umbral)
        {
            int suma = 0;
            for (int i = inicio; i < fin; i++)
            {
                suma += array[i];
            }
            return suma;
        }
        else
        {
            int mitad = inicio + (fin - inicio) / 2;
            EjemploParalelismo tareaIzquierda = new EjemploParalelismo(array, inicio, mitad);
            EjemploParalelismo tareaDerecha = new EjemploParalelismo(array, mitad, fin);
            tareaIzquierda.fork();
            int resultadoDerecha = tareaDerecha.compute();
            int resultadoIzquierda = tareaIzquierda.join();
            return resultadoIzquierda + resultadoDerecha;
        }
    }

    public static void main(String[] args)
    {
        long inicio = System.currentTimeMillis();
        int[] array = new int[100000000];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = i;
        }
        EjemploParalelismo tarea = new EjemploParalelismo(array, 0, array.length);
        long fin = System.currentTimeMillis();
        System.out.println("Resultado: " + tarea.compute());
        System.out.println("Tiempo de ejecución: " + (fin - inicio) + "ms");
    }
}