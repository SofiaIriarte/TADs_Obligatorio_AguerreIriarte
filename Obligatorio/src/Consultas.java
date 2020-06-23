import tads.Hash.Hash;
import tads.Hash.HashImpl;
import tads.Hash.HashNode;
import tads.Heap.HeapMax;
import tads.Heap.HeapMin;
import tads.Heap.KeyYaExiste;
import tads.LinkedList.LinkedList;

import java.io.IOException;
import java.nio.charset.IllegalCharsetNameException;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.SplittableRandom;

public class Consultas {

    public void c1(HashImpl<Long,Book> to_read,HashImpl<Long,Book> books) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        String titulo="";
        HeapMax<Integer,Book> cons = new HeapMax<>(912705);
        HashImpl<Long,Integer> consInt = new HashImpl<>(912705);
        int consSize=0;
        long id_book=0;
        int counter=0;
        for (long i=1;i<9998;i++){
            try {
                if (to_read.find(i).getBook_id()==i){
                    try {
                        counter=to_read.find(i).getReserved_to_read().length;
                    } catch (Exception e){
                        i++;
                    }
                    cons.agregar(counter, to_read.find(i));
                    consSize++;
                    consInt.put(to_read.find(i).getBook_id(),counter);
                }
            } catch (Exception n){
                i++;
            }
        }
        try{
            for (int l=0;l<10;l++){
                id_book=cons.obtenerYEliminar().getBook_id();
                int cantidad = (int) consInt.find(id_book);
                try {
                    titulo=books.find(id_book).getTitle();
                } catch (Exception e){
                    titulo="";
                }
                System.out.println("Id del libro: " + id_book + " Titulo: " + titulo + " Cantidad: " + cantidad);
            }
        } catch (Exception e){
            System.out.print("La cantidad de reservas es menor a la solicitada");
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.print("Tiempo de ejecucion de la consulta: "+tiempo+" ms\n");
    }

    public void c2(HashImpl<Long,Rating> rating,HashImpl<Long,Book> books) throws KeyYaExiste {
        long tiempoInicio = System.currentTimeMillis();
        String titulo = "";
        HeapMax<Integer, Book> cons = new HeapMax<>(6000000);
        HashImpl<Long, Integer> consInt = new HashImpl<>(6000000);
        long id_book = 0;
        for (long j=1;j<9998;j++){
            int counter = 0;
            int i=0;
            while (i < 59762) { //5976238 este deberia ser el maximo de i
                try {
                    Book book =rating.find((long)i).getBook();
                    if (book.getBook_id() == j) {
                        counter++;
                        i++;
                    }
                    else{
                        i++;
                    }
                } catch (Exception n) {
                    i++;
                }
            }
            try {
                cons.agregar(counter, books.find(j));
                consInt.put(j, counter);
                j++;
            } catch (Exception e) {
                j++;
            }
        }
        try{
            for (int l=0;l<20;l++){
                id_book=cons.obtenerYEliminar().getBook_id();
                int cantidad = (int) consInt.find(id_book);
                try {
                    titulo=books.find(id_book).getTitle();
                } catch (Exception e){
                    titulo="";
                }
                System.out.println("Id del libro: " + id_book + " Titulo: " + titulo + " Cantidad: " + cantidad);
            }
        } catch (Exception e){
            System.out.print("La cantidad de reservas es menor a la solicitada");
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.print("Tiempo de ejecucion de la consulta:"+tiempo+" ms\n");
    }

    public void c3() throws KeyYaExiste {
        long tiempoInicio = System.currentTimeMillis();
        Long[] arrayDeUsuarios = new Long[10];
        Integer[] arrayDeCantidades = new Integer[10];
        Float[] arrayDePromedio = new Float[10];
        int lugaresOcupados = 0;
        boolean ordenadoInicial = false;
        LinkedList<HashNode<Long, Rating>>[] ratings = ObligatorioImp.getRatings().getMyHash();
        for (int i = 0; i < ratings.length; i++) {
            Iterator iteradorRatings = ratings[i].iterator();
            while (iteradorRatings.hasNext()) {
                HashNode<Long, Rating> nodoActual = (HashNode<Long, Rating>) iteradorRatings.next();
                Rating ratingActual = nodoActual.getValue();
                System.out.println(ratingActual.getUser().getUser_id());
                for (int j = 0; j < 10; j++) {
                    arrayDeUsuarios[j] = Long.valueOf(0);
                }
                while (lugaresOcupados < 10) {
//                    System.out.println(lugaresOcupados);
//                    for (int j =0;j<10;j++){
//                        System.out.println(arrayDeUsuarios[j]);
//                    }
                    boolean usuarioDuplicado = false;
                    for (int z = 0; z < 10; z++) {
                        if (ratingActual.getUser().getUser_id() != null && arrayDeUsuarios[z] == ratingActual.getUser().getUser_id()) {
                            usuarioDuplicado = true;
                        }
                    }
                    if (!usuarioDuplicado) {
                        arrayDeUsuarios[lugaresOcupados] = Long.valueOf(ratingActual.getUser().getUser_id());
                        lugaresOcupados++;
                        if (iteradorRatings.hasNext()) {
                            nodoActual = (HashNode<Long, Rating>) iteradorRatings.next();
                            ratingActual = nodoActual.getValue();
                        } else {
                            lugaresOcupados=12;
                        }
                    }
                }
                System.out.println("Primer while funca");
                while (ordenadoInicial == false) {
                    long aux;
                    int position = 0;
                    for (int z = 0; z < arrayDeUsuarios.length; z++) {//como el tamaño es chico usar un algoritmo mas eficiente no produce mayores beneficios
                        position = z;
                        aux = ObligatorioImp.getUsers().find(arrayDeUsuarios[z]).getRatings().getSize();
                        while (position > 0 && arrayDeUsuarios[z - 1] > aux) {
                            arrayDeUsuarios[position] = arrayDeUsuarios[position - 1];
                            position--;
                        }
                        arrayDeUsuarios[position] = aux;
                    }
                    ordenadoInicial = true;
                }
                System.out.println("Segundo while funca");
                boolean coincidencia = false;
                for (int z = 0; z < 10; z++) {
                    if (arrayDeUsuarios[z] == ratingActual.getUser().getUser_id()) {
                        coincidencia = true;
                    }
                    if (coincidencia == false && ObligatorioImp.getUsers().find((long) ratingActual.getUser().getUser_id()).getRatings().getSize() > ObligatorioImp.getUsers().find(arrayDeUsuarios[9]).getRatings().getSize()) {
                        arrayDeUsuarios[9] = Long.valueOf(ratingActual.getUser().getUser_id());
                        long temp = 0;
                        int position1 = 0;
                        for (int j = 0; j < arrayDeUsuarios.length; j++) {//como el tamaño es chico usar un algoritmo mas eficiente no produce mayores beneficios
                            position1 = j;
                            temp = ObligatorioImp.getUsers().find(arrayDeUsuarios[j]).getRatings().getSize();
                            while (position1 > 0 && arrayDeUsuarios[j - 1] > temp) {
                                arrayDeUsuarios[position1] = arrayDeUsuarios[position1 - 1];
                                position1--;
                            }
                            arrayDeUsuarios[position1] = temp;
                        }
                    }

                }
            }
        }
        long tiempoFin = System.currentTimeMillis();
        long tiempo = tiempoFin - tiempoInicio;
        for (int i = 0; i < 10; i++) {
            int promedio = 0;
            User usuariotemp = ObligatorioImp.getUsers().find(arrayDeUsuarios[i]);
            for (int j = 0; j < usuariotemp.getRatings().getSize(); j++) {
                //promedio += usuariotemp.getRatings().get(j);//.getRating();

            }
            System.out.println("Id del usuario:" + " " + arrayDeUsuarios[i]);
            System.out.println("Cantidad:" + " " + usuariotemp.getRatings().getSize());
            System.out.println("Rating promedio:" + " " + (float) promedio / usuariotemp.getRatings().getSize());
            System.out.println("Tiempo de ejecución de la consulta:" + tiempo);


        }
    }



    public void c4(HashImpl<Long,Book> to_read) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        String cod_idioma="eng";
        int cantidad=0;
        String cod_idioma1="en-US";
        int cantidad1=0;
        String cod_idioma2="en-CA";
        int cantidad2=0;
        String cod_idioma3="spa";
        int cantidad3=0;
        String cod_idioma4="en-GB";
        int cantidad4=0;
        String cod_idioma5="fre";
        int cantidad5=0;
        String cod_idioma6="por";
        int cantidad6=0;
        HeapMax<Integer,Book> cons = new HeapMax<>(912705);
        HeapMax<Integer,Integer> idiomas = new HeapMax<>(1000);
        HashImpl<Integer,String> idiom = new HashImpl<>(1000);
        HashImpl<Long,Book> consInt = new HashImpl<>(912705);
        long id_book=0;
        for (long i=1;i<9998;i++) {
            int counter = 0;
            try{
                if (to_read.find(i).getBook_id() == i) {
                    counter = to_read.find(i).getReserved_to_read().length;
                    try {
                        cons.agregar(counter, to_read.find(i));

                    } catch (Exception e) {
                        i++;
                    }
                    consInt.put(to_read.find(i).getBook_id(),to_read.find(i));
                }
            } catch(Exception n){
                i++;
            }
        }
        int m=1;
        while(m<9998) {
            try {
                Book book=consInt.find((long)m);
                String code = book.getLanguage_code();
                if (code.equals(cod_idioma)) {
                    cantidad++;
                    m++;
                }
                else if (code.equals(cod_idioma1)) {
                    cantidad1++;
                    m++;
                }
                else if (code.equals(cod_idioma2)) {
                    cantidad2++;
                    m++;
                }
                else if (code.equals(cod_idioma3)) {
                    cantidad3++;
                    m++;
                }
                else if (code.equals(cod_idioma4)) {
                    cantidad4++;
                    m++;
                }
                else if (code.equals(cod_idioma5)) {
                    cantidad5++;
                    m++;                }
                else if (code.equals(cod_idioma6)) {
                    cantidad6++;
                    m++;
                } else {
                    m++;
                }
            } catch (Exception e){
                m++;
            }
        }
        idiomas.agregar(cantidad,cantidad);
        idiomas.agregar(cantidad1,cantidad1);
        idiomas.agregar(cantidad2,cantidad2);
        idiomas.agregar(cantidad3,cantidad3);
        idiomas.agregar(cantidad4,cantidad4);
        idiomas.agregar(cantidad5,cantidad5);
        idiomas.agregar(cantidad6,cantidad6);
        idiom.put(cantidad,"eng");
        idiom.put(cantidad1,"en-US");
        idiom.put(cantidad2,"en-CA");
        idiom.put(cantidad3,"en-GB");
        idiom.put(cantidad4,"spa");
        idiom.put(cantidad5,"fre");
        idiom.put(cantidad6,"por");
        for (int j=0;j<5;j++){
            int cantidadF = idiomas.obtenerYEliminar();
            String cod_idiomaF = idiom.find(cantidadF);
            System.out.println("Codigo del idioma: "+cod_idiomaF+" Cantidad: "+cantidadF);
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo+" ms\n");
    }


    public void c5(HashImpl<Long,Book> books) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        String autor=null;
        int anio_de_publi=0;
        HeapMin<Integer, Integer> anio = new HeapMin<>(10000);

        for (int i=0;i<10000;i++){
            try{
                Book book = books.find((long) i);
                long id = book.getBook_id();
                anio_de_publi = book.getOriginal_publication_year();
                if (anio_de_publi<0){
                    anio_de_publi*=(-1);
                    anio.agregar(anio_de_publi,anio_de_publi);
                }
                if (anio_de_publi>0){
                anio.agregar(anio_de_publi,anio_de_publi);
                }
                else {
                    i++;
                }
            }
            catch (Exception e){
                i++;
            }
        }
        anio_de_publi = anio.obtenerYEliminar();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();
        int actual = Integer.parseInt(dtf.format(now));
        while (anio_de_publi+1<actual){
            HeapMax<Integer,LinkedList> autores = new HeapMax<>(10000);
            HashImpl<LinkedList,Integer> autores1 = new HashImpl<>(10000);
            for (int i=1;i<9998;i++){
                int counter=1;
                Book book=books.find((long) i);
                int year=book.getOriginal_publication_year();
                if (year<0){
                    year*=(-1);
                }
                if (year==anio_de_publi){
                    try {
                        int m=1;
                        while(m<9998){
                            if (books.find((long) m).getAuthor()==books.find((long) i).getAuthor()){
                                counter++;
                                m++;
                            }
                            m++;
                        }
                        LinkedList autorList = new LinkedList();
                        int size = book.getAuthor().getSize();
                        for (int k=0;k<size-1;k++){
                            autorList.add(book.getAuthor().get(k).getName());
                        }
                        autores.agregar(counter,autorList);
                        autores1.put(autorList,counter);
                    } catch (KeyYaExiste k){
                        i++;
                    }
                }
                else{i++;}
            }
            try {
                for (int i=0;i<20;i++){
                    LinkedList autor2 = autores.obtenerYEliminar();
                    int counter = autores1.find(autor2);
                    System.out.println("Autor: "+autor2+" Año de publicacion: "+anio_de_publi+" Cantidad: "+counter);
                    anio_de_publi++;
                }
            } catch (Exception e){
                anio_de_publi++;
            }
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo+" ms\n");
    }
}
