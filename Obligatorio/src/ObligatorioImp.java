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
    static Scanner br = new Scanner(System.in);

    public static HashImpl<Long, User> getUsers() {
        return users;
    }

    public static HashImpl<Long, Rating> getRatings() {
        return ratings;
    }
    public ObligatorioImp() throws IOException, KeyYaExiste {
        users = new HashImpl<>(1000);
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
            books = temp.cargaBooks();
            ratings = temp.cargaRatings(books);
            to_read = temp.cargaTo_Read(books);
            long tiempoFin = System.currentTimeMillis();
            long tiempo = tiempoFin - tiempoInicio;
            System.out.print("Carga de datos exitosa, tiempo de ejecución de la carga:" + tiempo + " ms\n");
            for (int i=0;i<6000000;i++){
                if (users.find((long) i)!=null){
                    System.out.println(users.find((long)i).getUser_id());
                }
            }
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
        System.out.print("Consultas:\n\t1. Indicar el Top 10 de libros que más lecturas tienen por parte de usuarios.\n" +
                "\t2. Indicar el Top 20 de libros que más cantidad de lecturas tienen.\n" +
                "\t3. Indicar el Top 10 de usuarios que realizaron mayor cantidad de evaluaciones a libros " +
                "y ordenarlo por rating promedio descendente.\n" +
                "\t4. Indicar el Top 5 de los idiomas asociados a libros que han tenido más reservas.\n" +
                "\t5. Indicar el Top 20 de autores que más publicaciones han hecho por año.\n" +
                "\t6. Salir\n");
        int numero = br.nextInt();
        Consultas temp = new Consultas();
        if (numero==1){
            try {
                temp.c1(to_read,books);
            } catch (KeyYaExiste k) {
                System.out.print("No es posible realizar esta consulta\n");
            }
            consultas();
        }
        if (numero==2){
            try {
                temp.c2(to_read, books);
            } catch (KeyYaExiste k) {
                System.out.print("No es posible realizar esta consulta\n");
            }
            consultas();
        }
        if (numero==3){
            try {
                temp.c3();
            } catch (KeyYaExiste k) {
                System.out.print("No es posible realizar esta consulta\n");
            }
            consultas();
        }
        /*if (numero==4){
            try {
                temp.c4(to_read);
            } catch (KeyYaExiste k) {
                System.out.print("No es posible realizar esta consulta\n");
            }
            consultas();
        }*/
       /* if (numero==5){
            try {
                temp.c5(books);
            } catch (KeyYaExiste k) {
                System.out.print("No es posible realizar esta consulta\n");
            }
            consultas();
        }*/
        if (numero!=1&&numero!=2&&numero!=3&&numero!=4&&numero!=5&&numero!=6){
            System.out.print("Opcion no valida, intente nuevamente");
            consultas();
        }
    }
}