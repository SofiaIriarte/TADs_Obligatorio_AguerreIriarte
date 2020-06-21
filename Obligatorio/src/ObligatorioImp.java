import tads.Hash.ElementoNoExiste;
import tads.Hash.Hash;
import tads.Heap.HeapMax;
import tads.Heap.KeyYaExiste;
import tads.LinkedList.LinkedList;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.Scanner;

public class ObligatorioImp implements Obligatorio{

    private static LinkedList<Book> books = new LinkedList();
    private static LinkedList<Rating> ratings = new LinkedList();
    private static LinkedList<Book> to_read = new LinkedList();
    static Scanner br = new Scanner(System.in);

    public static void main(String[] args)throws IOException,KeyYaExiste{
        Principal();
    }

    public static void Principal() throws IOException,KeyYaExiste{
        System.out.print("Menu:\nSeleccione la opcion que desee:\n\t" +
                "1. Carga de datos\n\t" +
                "2. Ejecutar consultas\n\t" +
                "3. Salir\n");
        int numero = br.nextInt();
        //switch (numero){
            if(numero==1) {//case 1:
                long tiempoInicio=System.currentTimeMillis();
                CargaDeDatos temp= new CargaDeDatos();
                books=temp.cargaBooks();
                ratings=temp.cargaRatings();
                to_read=temp.cargaTo_Read(books);
                long tiempoFin=System.currentTimeMillis();
                long tiempo= tiempoFin-tiempoInicio;
                System.out.print("Carga de datos exitosa, tiempo de ejecución de la carga:" + tiempo + " ms\n");
                Principal();}
            //case 2:
              if(numero==2){  try {
                    System.out.print("55");
                    consultas();
                } catch (IOException i) {
                    System.out.print("");
                } catch (KeyYaExiste k) {
                    System.out.print("");
                }}
            //case 3:
              if(numero!=1&&numero!=2&&numero!=3) {
                  System.out.print("Opcion no valida, intente nuevamente");
                  Principal();
              }


        //}
    }


    public static void consultas() throws IOException,KeyYaExiste{
        System.out.print("1. Indicar el Top 10 de libros que más lecturas tienen por parte de usuarios.\n" +
                "2. Indicar el Top 20 de libros que más cantidad de lecturas tienen.\n" +
                "3. Indicar el Top 10 de usuarios que realizaron mayor cantidad de evaluaciones a libros " +
                "y ordenarlo por rating promedio descendente.\n" +
                "4. Indicar el Top 5 de los idiomas asociados a libros que han tenido más reservas.\n" +
                "5. Indicar el Top 20 de autores que más publicaciones han hecho por año.\n" +
                "6. Salir\n");
        int numero = br.nextInt();
        Consultas temp = new Consultas();
        int sizeTo_Read = to_read.getSize();
        int sizeBooks = books.getSize();
        int sizeRatings = ratings.getSize();
        switch (numero){
            case 1:
                try {
                    temp.c1(to_read,sizeTo_Read,books,sizeBooks);
                } catch (KeyYaExiste k){
                    System.out.print("No es posible realizar esta consulta");
                    consultas();
                }
            case 2:
                try {
                    temp.c2(to_read,sizeTo_Read,books,sizeBooks);
                } catch (KeyYaExiste k){
                    System.out.print("No es posible realizar esta consulta");
                    consultas();
                }
            case 3:
                try {
                    temp.c3(to_read,sizeTo_Read,ratings,sizeRatings);
                } catch (KeyYaExiste k){
                    System.out.print("No es posible realizar esta consulta");
                    consultas();
                }
            case 4:
                try {
                    temp.c4();
                } catch (KeyYaExiste k){
                    System.out.print("No es posible realizar esta consulta");
                    consultas();
                }
            case 5:
                try {
                    temp.c5();
                } catch (KeyYaExiste k){
                    System.out.print("No es posible realizar esta consulta");
                    consultas();
                }
            case 6:
                break;
            default:
                throw new IOException();
        }
    }
}