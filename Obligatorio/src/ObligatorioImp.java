import tads.Hash.ElementoNoExiste;
import tads.Hash.Hash;
import tads.Heap.HeapMax;
import tads.LinkedList.LinkedList;

import java.io.*;
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
                c1();
            case 2:
                c2();
            case 3:
                c3();
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

    public void c1() throws ElementoNoExiste {
        long tiempoInicio=System.currentTimeMillis();
        int id_libro=0;
        String titulo=null;
        LinkedList<String[]> books1 = books;
        LinkedList<String[]> to_read1 = to_read;
        Hash<Integer,LinkedList<String[]>> h1 = new Hash<Integer,LinkedList<String[]>>;
        for (int i=0;i<sizeTo_Read;i++){
            int id_book= Integer.parseInt(to_read1.get(i)[1]);
            int id_user= Integer.parseInt(to_read1.get(i)[0]);
            if (!h1.contains(id_book)){
                LinkedList id = new LinkedList();
                id.addFirst(id_user);
                h1.put(id_book,id);
            } else if (h1.contains(id_book)) { //como hago para agregar solo si el usuario es!=??
                LinkedList tryId = h1.find(id_book);
                boolean addId=true;
                for (int j=0;j<tryId.getSize();j++){
                    if (j==Integer.parseInt(String.valueOf(tryId.get(j)))){
                        addId=false;
                    }
                }
                if (addId==true){
                    tryId.add(id_user);
                    h1.remove(id_book);
                    h1.put(id_book,tryId);
                }
            }
        }
        LinkedList top10 = new LinkedList();
        //ordenar lista desde el size mas grande de id_user hasta el mas chico, pero que sea de largo 10
        //ir desde el primero hasta el decimo con un for, primero tomo el id_book y guardo el largo de tryId, luego,
        // lo busco en la lista books con un for, agarro el titulo que quiero
        for (int l=0;l<9;l++){
            int id_book = Integer.parseInt(top10[l][0]);
            int cantidad = Integer.parseInt(top10[l][1].size());
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
    public void c2() throws ElementoNoExiste{
        long tiempoInicio=System.currentTimeMillis();
        int id_libro=0;
        String titulo=null;
        LinkedList books1 = books;
        LinkedList to_read1 = to_read;
        Hash<Integer,Integer> h1 = new Hash;
        for (int i=0;i<sizeTo_Read;i++){
            int counter=0;
            int id_book=Integer.parseInt(to_read1.get(i)[1]);
            int id_user=Integer.parseInt(to_read1.get(i)[0]);
            if (!h1.contains(id_book)){
                h1.put(id_book,counter++);
            } else if (h1.contains(id_book)) { //como hago para agregar solo si el usuario es!=??
                Integer newCount = h1.find(id_book);
                newCount++;
                h1.remove(id_book);
                h1.put(id_book,newCount);
            }
        }
        LinkedList<Integer> top20 = new LinkedList();
        //ordenar lista desde el size mas grande de id_user hasta el mas chico, pero que sea de largo 10
        //ir desde el primero hasta el decimo con un for, primero tomo el id_book y guardo el largo de tryId, luego,
        // lo busco en la lista books con un for, agarro el titulo que quiero
        for (int l=0;l<19;l++){
            int id_book = top20[l][0];
            int cantidad = top20[l][1];
            for (int i=0;i<sizeBooks;i++){
                if (id_book==Integer.parseInt(books1.get(i)[0])){
                    titulo=Integer.parseInt(books1.get(i)[5]);
                    System.out.println("Id del libro:" + id_book + "Titulo:" + titulo + "Cantidad:" + cantidad);
                }
            }
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.print("Tiempo de ejecucion de la consulta:"+tiempo);
    }
    public void c3() throws ElementoNoExiste{
        long tiempoInicio=System.currentTimeMillis();
        LinkedList ratings1 = ratings;
        Hash<Integer,Integer> h1 = new Hash;
        for (int i=0;i<sizeRatings;i++){
            int counter=0;
            int id_user=Integer.parseInt(ratings1.get(i)[0]);
            if (!h1.contains(id_user)){
                h1.put(id_user,counter++);
            } else if (h1.contains(id_user)) { //como hago para agregar solo si el usuario es!=??
                Integer newCount = h1.find(id_user);
                newCount++;
                h1.remove(id_user);
                h1.put(id_user,newCount);
            }
        }
        LinkedList<Integer> top10 = new LinkedList();
        //ordenar lista desde el size mas grande de cantidad hasta el mas chico, pero que sea de largo 10
        //ir desde el primero hasta el decimo con un for, primero tomo el id_user y voy sumando rating, luego,
        // lo busco en la lista books con un for, agarro el titulo que quiero
        int cantidadEva;
        int id_user;
        int ratingProm;
        LinkedList<LinkedList<Integer>> conRating = new LinkedList();
        for (int l=0;l<9;l++){
            int id_user = top10[l][0];
            cantidadEva = top10[l][1];
            ratingProm=0;
            for (int i=0;i<sizeRatings;i++){
                if (id_user==Integer.parseInt(ratings1.get(i)[0])){
                    ratingProm+=Integer.parseInt(ratings1.get(i)[2]);
                }
            }
            conRating.add([id_user,cantidadEva,ratingProm]);
        }
        //ordenar segun 3er atributo la lista conRating
        for (int m=0;m<conRating.getSize();m++){
            id_user=conRating.get(m).get(0);
            cantidadEva=conRating.get(m).get(1);
            ratingProm=conRating.get(m).get(2);
            System.out.println("Id del usuario:" + id_user + "Cantidad:" + cantidadEva + "Rating promedio:" + ratingProm);
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
