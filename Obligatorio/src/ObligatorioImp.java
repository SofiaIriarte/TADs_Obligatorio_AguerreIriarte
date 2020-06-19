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

    private static LinkedList<String[]> books;
    private static LinkedList<String[]> ratings;
    private static LinkedList<String[]> to_read;

    public static void main(String[] args)throws IOException,KeyYaExiste{
        Principal();
    }
    static Scanner br = new Scanner(System.in);

    public static void Principal() throws IOException,KeyYaExiste{
        System.out.print("Menu:\nSeleccione la opcion que desee:\n\t" +
                "1. Carga de datos\n\t" +
                "2. Ejecutar consultas\n" +
                "3. Salir");
        int numero = br.nextInt();
        switch (numero){
            case 1:
                CargaDeDatos temp= new CargaDeDatos();
                books=temp.cargaBooks(books);
                ratings=temp.cargaRatings(ratings);
                to_read=temp.cargaTo_Read(to_read);
                break;
            case 2:
                try {
                    consultas();
                } catch (IOException i) {
                    System.out.print("");
                } catch (KeyYaExiste k) {
                    System.out.print("");
                }
                break;
            case 3:
                break;
        }

    }


    public static void consultas() throws IOException,KeyYaExiste{
        System.out.print("1. Indicar el Top 10 de libros que más lecturas tienen por parte de usuarios.\n" +
                "2. Indicar el Top 20 de libros que más cantidad de lecturas tienen.\n" +
                "3. Indicar el Top 10 de usuarios que realizaron mayor cantidad de evaluaciones a libros " +
                "y ordenarlo por rating promedio descendente.\n" +
                "4. Indicar el Top 5 de los idiomas asociados a libros que han tenido más reservas.\n" +
                "5. Indicar el Top 20 de autores que más publicaciones han hecho por año.\n" +
                "6. Salir");
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
                }
            case 2:
                try {
                    temp.c2(to_read,sizeTo_Read,books,sizeBooks);
                } catch (KeyYaExiste k){
                    System.out.print("No es posible realizar esta consulta");
                }
            case 3:
                try {
                    temp.c3(to_read,sizeTo_Read,ratings,sizeRatings);
                } catch (KeyYaExiste k){
                    System.out.print("No es posible realizar esta consulta");
                }
            case 4:
                try {
                    temp.c4();
                } catch (KeyYaExiste k){
                    System.out.print("No es posible realizar esta consulta");
                }
            case 5:
                try {
                    temp.c5();
                } catch (KeyYaExiste k){
                    System.out.print("No es posible realizar esta consulta");
                }
            case 6:
                break;
            default:
                throw new IOException();
        }
    }
}
