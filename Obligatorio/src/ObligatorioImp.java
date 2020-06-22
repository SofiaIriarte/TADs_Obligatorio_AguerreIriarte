import tads.Hash.ElementoNoExiste;
import tads.Hash.Hash;
import tads.Heap.HeapMax;
import tads.Heap.KeyYaExiste;
import tads.LinkedList.LinkedList;
import tads.Hash.HashImpl;
import sun.awt.image.ImageWatched;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.Scanner;

public class ObligatorioImp implements Obligatorio {

    private static HashImpl<Long, Book> books;
    private static HashImpl<Long, User> users;
    private static HashImpl<Long, Rating> ratings;
    private static HashImpl<Long,Book> to_read;
    private static LinkedList<Long> id_books;
    static Scanner br = new Scanner(System.in);

    public static void main(String[] args) throws IOException, KeyYaExiste {
        Principal();
    }

    public static void Principal() throws IOException, KeyYaExiste {
        System.out.print("Menu:\nSeleccione la opcion que desee:\n\t" +
                "1. Carga de datos\n\t" +
                "2. Ejecutar consultas\n\t" +
                "3. Salir\n");
        int numero = br.nextInt();
        if (numero == 1) {
            long tiempoInicio = System.currentTimeMillis();
            CargaDeDatos temp = new CargaDeDatos();
            id_books=temp.id_books();
            books = temp.cargaBooks();
            ratings = temp.cargaRatings(books);
            to_read = temp.cargaTo_Read(books);
            long tiempoFin = System.currentTimeMillis();
            long tiempo = tiempoFin - tiempoInicio;
            System.out.print("Carga de datos exitosa, tiempo de ejecución de la carga:" + tiempo + " ms\n");
            Principal();
        }
        if (numero == 2) {
            try {
                consultas();
            } catch (IOException i) {
                System.out.print("");
            } catch (KeyYaExiste k) {
                System.out.print("");
            }
        }
        if (numero != 1 && numero != 2 && numero != 3) {
            System.out.print("Opcion no valida, intente nuevamente");
            Principal();
        }
    }


    public static void consultas() throws IOException, KeyYaExiste {
        System.out.print("1. Indicar el Top 10 de libros que más lecturas tienen por parte de usuarios.\n" +
                "2. Indicar el Top 20 de libros que más cantidad de lecturas tienen.\n" +
                "3. Indicar el Top 10 de usuarios que realizaron mayor cantidad de evaluaciones a libros " +
                "y ordenarlo por rating promedio descendente.\n" +
                "4. Indicar el Top 5 de los idiomas asociados a libros que han tenido más reservas.\n" +
                "5. Indicar el Top 20 de autores que más publicaciones han hecho por año.\n" +
                "6. Salir\n");
        int numero = br.nextInt();
        Consultas temp = new Consultas();
        switch (numero) {
            case 1:
                try {
                    temp.c1(to_read,books);
                } catch (KeyYaExiste k) {
                    System.out.print("No es posible realizar esta consulta\n");
                }
                consultas();
            case 2:
                try {
                    temp.c2(to_read, books,id_books);
                } catch (KeyYaExiste k) {
                    System.out.print("No es posible realizar esta consulta\n");
                }
                consultas();
            case 3:
                try {
                    temp.c3(to_read, ratings);
                } catch (KeyYaExiste k) {
                    System.out.print("No es posible realizar esta consulta\n");
                }
                consultas();
            case 4:
                try {
                    temp.c4(to_read);
                } catch (KeyYaExiste k) {
                    System.out.print("No es posible realizar esta consulta\n");
                }
                consultas();
            case 5:
                try {
                    temp.c5(books);
                } catch (KeyYaExiste k) {
                    System.out.print("No es posible realizar esta consulta\n");
                }
                consultas();
            case 6:
                break;
            default:
                System.out.print("Opcion no valida, intente nuevamente");
                consultas();
        }
    }
}