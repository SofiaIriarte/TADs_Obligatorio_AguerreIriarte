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

    LinkedList<String[]> books;
    LinkedList<String[]> ratings;
    LinkedList<String[]> to_read;
    int sizeBooks;
    int sizeRatings;
    int sizeTo_Read;

    public void Principal() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Menu:\nSeleccione la opcion que desee:\n\t" +
                "1. Carga de datos\n\t" +
                "2. Ejecutar consultas\n" +
                "3. Salir");
        String opcion = br.readLine();
        int numero = Integer.parseInt(opcion);
        switch (numero){
            case 1:
                carga();
            case 2:
                consultas();
            case 3:
                break;
            default:
                throw new IOException();
        }

    }

    public void carga(){
        long tiempoInicio=System.currentTimeMillis();
        String booksFile = "books.csv";
        String ratingsFile = "ratings.csv";
        String to_readFile = "to_read.csv";
        BufferedReader br = null;
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        String line = "";
        String cvsSplitBy = ",";
        //File bFile = new File(booksFile);
        //File rFile = new File(ratingsFile);
        //File tFile = new File(to_readFile);
        try {
            br = new BufferedReader(new FileReader(booksFile));
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(cvsSplitBy);
                books.add(datos);
                sizeBooks++;
                //hacerme una lista, ver cual me quedaria mejor
                //Imprime datos.
                //System.out.println(datos[0] + ", " + datos[1] + ", " + datos[2] + ", " + datos[3] + ", " + datos[4] + ", " + datos[5]);
            }
            br1 = new BufferedReader(new FileReader(ratingsFile));
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(cvsSplitBy);
                ratings.add(datos);
                sizeRatings++;
            }
            br2 = new BufferedReader(new FileReader(to_readFile));
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(cvsSplitBy);
                to_read.add(datos);
                sizeTo_Read++;
            }
            //Scanner books = new Scanner(bFile);
            //Scanner ratings = new Scanner(rFile);
            //Scanner to_read = new Scanner(tFile);
        } catch (FileNotFoundException e){
            System.out.print("Error al cargar los datos, intente de nuevo");
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.print("Error al finalizar la carga de datos");
                }
            }
            else if (br1 != null) {
                try {
                    br1.close();
                } catch (IOException e) {
                    System.out.print("Error al finalizar la carga de datos");
                }
            }
            else if (br2 != null) {
                try {
                    br2.close();
                } catch (IOException e) {
                    System.out.print("Error al finalizar la carga de datos");
                }
            }
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.print("Carga de datos exitosa, tiempo de ejecución de la carga:" + tiempo + "ms");
    }

    public void consultas() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("1. Indicar el Top 10 de libros que más lecturas tienen por parte de usuarios.\n" +
                "2. Indicar el Top 20 de libros que más cantidad de lecturas tienen.\n" +
                "3. Indicar el Top 10 de usuarios que realizaron mayor cantidad de evaluaciones a libros " +
                "y ordenarlo por rating promedio descendente.\n" +
                "4. Indicar el Top 5 de los idiomas asociados a libros que han tenido más reservas.\n" +
                "5. Indicar el Top 20 de autores que más publicaciones han hecho por año.\n" +
                "6. Salir");
        String opcion = br.readLine();
        int numero = Integer.parseInt(opcion);
        switch (numero){
            case 1:
                try {
                    c1();
                } catch (KeyYaExiste k){
                    System.out.print("No es posible realizar esta consulta");
                }
            case 2:
                try {
                    c2();
                } catch (KeyYaExiste k){
                    System.out.print("No es posible realizar esta consulta");
                }
            case 3:
                try {
                    c3();
                } catch (KeyYaExiste k){
                    System.out.print("No es posible realizar esta consulta");
                }
            case 4:
                c4();
            case 5:
                c5();
            case 6:
                break;
            default:
                throw new IOException();
        }
    }

    public void c1() throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        String titulo=null;
        LinkedList<String[]> books1 = books;
        LinkedList<String[]> to_read1 = to_read;
        HeapMax<Integer,LinkedList<Integer>> heap1 = new HeapMax(sizeTo_Read);
        for (int i=0;i<sizeTo_Read;i++){
            String[] hola = to_read1.get(i);
            int id_book= Integer.parseInt(hola[1]);
            int id_user= Integer.parseInt(hola[0]);
            LinkedList users = new LinkedList();
            users.addFirst(id_book);
            users.add(id_user);
            try {
                heap1.agregar(id_book,users);
            } catch (KeyYaExiste k) {
                users = (LinkedList) heap1.obtenerYEliminar(); // como hago para que aca me elimine el que yo quiero y no el primero??
                boolean addId=true;
                for (int j=1;j<users.getSize();j++){
                    if (j==Integer.parseInt(String.valueOf(users.get(j)))){
                        addId=false;
                    }
                }
                if (addId==true){
                    users.add(id_user);
                    heap1.agregar(id_book,users);
                }
            }
        }
        for (int l=0;l<9;l++){
            LinkedList<Integer> datos = heap1.obtenerYEliminar();
            int id_book = datos.get(0);
            int cantidad = datos.getSize();
            for (int i=0;i<sizeBooks;i++){
                if (id_book==Integer.parseInt(books1.get(i)[0])){
                    titulo=books1.get(i)[5];
                    System.out.println("Id del libro:" + id_book + "Titulo:" + titulo + "Cantidad:" + cantidad);
                }
            }
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.print("Tiempo de ejecucion de la consulta:"+tiempo);

    }
    public void c2() throws KeyYaExiste{
        long tiempoInicio=System.currentTimeMillis();
        String titulo=null;
        LinkedList books1 = books;
        LinkedList to_read1 = to_read;
        HeapMax<Integer,LinkedList<Integer>> heap2 = new HeapMax(sizeTo_Read);
        for (int i=0;i<sizeTo_Read;i++){
            int counter=1;
            String[] hola = (String[]) to_read1.get(i);
            int id_book= Integer.parseInt(hola[1]);
            LinkedList<Integer> datos = null;
            datos.addFirst(id_book);
            datos.add(counter);
            try {
                heap2.agregar(id_book, datos);
            } catch (KeyYaExiste k){
                LinkedList<Integer> newDatos = heap2.obtenerYEliminar();
                int id_bookNew = newDatos.get(0);
                int counterNew = newDatos.get(1);
                counterNew++;
                LinkedList<Integer> datosNew=null;
                datosNew.addFirst(id_bookNew);
                datos.add(counterNew);
                heap2.agregar(id_bookNew,datosNew);
            }
        }
        for (int l=0;l<19;l++){
            LinkedList<Integer> datos = heap2.obtenerYEliminar();
            int id_book = datos.get(0);
            int cantidad = datos.get(1);
            for (int i=0;i<sizeBooks;i++){
                String[] hola1 = (String[]) books1.get(i);
                if (id_book==Integer.parseInt(hola1[0])){
                    titulo=hola1[5];
                    System.out.println("Id del libro:" + id_book + "Titulo:" + titulo + "Cantidad:" + cantidad);
                }
            }
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.print("Tiempo de ejecucion de la consulta:"+tiempo);
    }
    public void c3() throws KeyYaExiste{
        long tiempoInicio=System.currentTimeMillis();
        LinkedList ratings1 = ratings;
        HeapMax<Integer,LinkedList<Integer>> heap3 = new HeapMax(sizeTo_Read);
        for (int i=0;i<sizeRatings;i++){
            int counter=0;
            String[] hola= (String[]) ratings1.get(i);
            int id_user=Integer.parseInt(hola[0]);
            LinkedList<Integer> datos = new LinkedList<>();
            datos.addFirst(id_user);
            datos.add(counter);
            try {
                heap3.agregar(id_user,datos);
            } catch (KeyYaExiste k){
                LinkedList<Integer> newDatos = heap3.obtenerYEliminar();
                int id_userNew = newDatos.get(0);
                int counterNew = newDatos.get(1);
                counterNew++;
                LinkedList<Integer> datosNew=null;
                datosNew.addFirst(id_userNew);
                datos.add(counterNew);
                heap3.agregar(id_userNew,datosNew);
            }
        }
        int cantidadEva;
        int ratingProm;
        HeapMax<Integer,LinkedList<Integer>> conRating = new HeapMax(9);
        for (int l=0;l<9;l++){
            LinkedList<Integer> extraccion;
            extraccion = heap3.obtenerYEliminar();
            int id_userNew = extraccion.get(0);
            cantidadEva = extraccion.get(1);
            ratingProm=0;
            for (int i=0;i<sizeRatings;i++){
                String[] hola1 = (String[]) ratings1.get(i);
                if (id_userNew==Integer.parseInt(hola1[0])){
                    ratingProm+=Integer.parseInt(hola1[2]);
                }
            }
            LinkedList nuevo = new LinkedList();
            nuevo.addFirst(id_userNew);
            nuevo.addFirst(cantidadEva);
            nuevo.add(ratingProm);
            conRating.agregar(id_userNew,nuevo);
        }
        //ordenar segun 3er atributo la lista conRating
        for (int m=0;m<conRating.getSize();m++){
            LinkedList<Integer> ultima;
            ultima = conRating.obtenerYEliminar();
            int idUser = ultima.get(0);
            cantidadEva = ultima.get(1);
            ratingProm=ultima.get(2);
            System.out.println("Id del usuario:" + idUser + "Cantidad:" + cantidadEva + "Rating promedio:" + ratingProm);
        }

        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.print("Tiempo de ejecucion de la consulta:"+tiempo);
    }
    public void c4(){
        long tiempoInicio=System.currentTimeMillis();
        int cod_idioma=0;
        int cantidad=0;
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.println("Codigo del idioma:"+cod_idioma+"Cantidad:"+cantidad+"Tiempo de ejecucion de la consulta:"+tiempo);
    }
    public void c5(){
        long tiempoInicio=System.currentTimeMillis();
        String autor=null;
        Date anio_de_publi=null;
        int cantidad=0;
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.println("Autor:"+autor+"Anio de publicacion:"+anio_de_publi+"Cantidad:"+cantidad+"Tiempo de ejecucion de la consulta:"+tiempo);
    }

}
