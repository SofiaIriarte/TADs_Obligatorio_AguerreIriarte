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

    public void c3(HashImpl<Long,Book> to_read,HashImpl<Long,Rating> ratings) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        int sizeTo_Read= 6000000;
        HeapMax<Integer, LinkedList> heap3 = new HeapMax(sizeTo_Read);
        for (int i=0;i<1000;i++){
            int counter=1;
            Rating hola= (Rating) ratings.find((long) i);
            User id_user=hola.getUser();
            int j=0;
            while(j<1000&&ratings.find((long)j).getUser()==id_user){
                counter++;
                j++;
            }
            LinkedList datos = new LinkedList<>();
            datos.addFirst(id_user);
            datos.add(counter);
            try {
                heap3.agregar(counter,datos);
            } catch (KeyYaExiste k){}}
                /*LinkedList newDatos = heap3.obtenerYEliminar();
                long id_userNew = (long) newDatos.get(0);
                int counterNew = (int) newDatos.get(1);
                counterNew++;
                LinkedList datosNew=null;
                datosNew.addFirst(id_userNew);
                datos.add(counterNew);
                heap3.agregar(id_userNew,datosNew);
    i++;
}
        }*/
                int cantidadEva;
                int ratingProm;
                HeapMax<Long, LinkedList<Integer>> conRating = new HeapMax(10);
        /*for (int l=0;l<10;l++){
        LinkedList extraccion;
        extraccion = heap3.obtenerYEliminar();
        long id_userNew = (Long) extraccion.get(0);
        cantidadEva = (int) extraccion.get(1);
        ratingProm=0;
        for (int i=0;i<1000;i++){
        Rating hola1 = (Rating) ratings.find((long) i);
        if (id_userNew==hola1.getUser_id().getUser_id()){ //revisar
        ratingProm+=hola1.getRating();
        }
        }
        LinkedList nuevo = new LinkedList();
        nuevo.addFirst(id_userNew);
        nuevo.addFirst(cantidadEva);
        nuevo.add(ratingProm);
        conRating.agregar(id_userNew,nuevo);
        }*/
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
        System.out.print("Tiempo de ejecucion de la consulta:"+tiempo+" ms\n");
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
        String cod_idioma7="per";
        int cantidad7=0;
        String cod_idioma8="ara";
        int cantidad8=0;
        String cod_idioma9="dan";
        int cantidad9=0;
        String cod_idioma10="ger";
        int cantidad10=0;
        String cod_idioma11="jpn";
        int cantidad11=0;
        String cod_idioma12="ind";
        int cantidad12=0;
        String cod_idioma13="rum";
        int cantidad13=0;
        String cod_idioma14="pol";
        int cantidad14=0;
        String cod_idioma15="swe";
        int cantidad15=0;
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
                }
                else if (code.equals(cod_idioma7)) {
                    cantidad7++;
                    m++;
                }
                else if (code.equals(cod_idioma8)) {
                    cantidad8++;
                    m++;
                }
                else if (code.equals(cod_idioma9)) {
                    cantidad9++;
                    m++;
                }
                else if (code.equals(cod_idioma10)) {
                    cantidad10++;
                    m++;                }
                else if (code.equals(cod_idioma11)) {
                    cantidad11++;
                    m++;
                }
                else if (code.equals(cod_idioma12)) {
                    cantidad12++;
                    m++;
                }
                else if (code.equals(cod_idioma13)) {
                    cantidad13++;
                    m++;
                }
                else if (code.equals(cod_idioma14)) {
                    cantidad14++;
                    m++;
                }
                else if (code.equals(cod_idioma15)) {
                    cantidad15++;
                    m++;
                }
                else {
                    m++;
                }
            } catch (Exception e){
                m++;
            }
        }
        int cantidadE = cantidad + cantidad1 + cantidad2 + cantidad3;
        idiomas.agregar(cantidadE, cantidadE);
        idiom.put(cantidadE, "eng");
        idiomas.agregar(cantidad4, cantidad4);
        idiom.put(cantidad4, "spa");
        idiomas.agregar(cantidad5, cantidad5);
        idiom.put(cantidad5, "fre");
        idiomas.agregar(cantidad6, cantidad6);
        idiom.put(cantidad6, "por");
        idiomas.agregar(cantidad8, cantidad8);
        idiom.put(cantidad8, "ara");
        idiomas.agregar(cantidad10, cantidad10);
        idiom.put(cantidad10, "ger");
        idiomas.agregar(cantidad11, cantidad11);
        idiom.put(cantidad11, "jpn");
        idiomas.agregar(cantidad12, cantidad12);
        idiom.put(cantidad12, "ind");
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
