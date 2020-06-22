import tads.Hash.Hash;
import tads.Hash.HashImpl;
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

public class Consultas {

    public void c1(LinkedList<Book> to_read,HashImpl<Long,Book> books) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        int sizeTo_Read = to_read.getSize();
        String titulo=null;
        LinkedList<Book> to_read1=to_read;
        HeapMax<Long, LinkedList<Integer>> heap1 = new HeapMax(sizeTo_Read);
        for (int i=0;i<sizeTo_Read;i++){
            Book hola = to_read1.get(i);
            long id_book= hola.getBook_id();
            User[] user=hola.getReserved_to_read();;

            LinkedList users = new LinkedList();
            users.addFirst(id_book);
            users.add(user);
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
                    users.add(user);
                    heap1.agregar(id_book,users);
                }
            }
        }
        for (int l=0;l<9;l++){
            LinkedList<Integer> datos = heap1.obtenerYEliminar();
            int id_book = datos.get(0);
            int cantidad = datos.getSize();
            HashImpl<Long,Book> books1=books;
            for (int i=0;i<10000;i++){
                // if (id_book==Integer.parseInt(books1.get(i))){ //[0]
                //    titulo=books1.get(i); //[5]
                System.out.println("Id del libro:" + id_book + "Titulo:" + titulo + "Cantidad:" + cantidad);
                //  }
            }
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.print("Tiempo de ejecucion de la consulta:"+tiempo);

    }
    public void c2(LinkedList<Book> to_read,HashImpl<Long,Book> books) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        String titulo=null;
        HashImpl<Long,Book> books1 = books;
        LinkedList<Book> to_read1 = to_read;
        int sizeTo_Read = to_read.getSize();
        HeapMax<Long, LinkedList> heap2 = new HeapMax(sizeTo_Read);
        for (int i=0;i<sizeTo_Read;i++){
            int counter=1;
            Book hola = (Book) to_read1.get(i);
            long id_book= hola.getBook_id();
            LinkedList datos = new LinkedList();
            datos.addFirst(id_book);
            datos.add(counter);
            try {
                heap2.agregar(id_book, datos);
            } catch (KeyYaExiste k){
                LinkedList newDatos = heap2.obtenerYEliminar();
                long id_bookNew = (long) newDatos.get(0);
                int counterNew = (int) newDatos.get(1);
                counterNew++;
                LinkedList datosNew=new LinkedList<>();
                datosNew.addFirst(id_bookNew);
                datos.add(counterNew);
                heap2.agregar(id_bookNew,datosNew);
            }
        }
        for (int l=0;l<19;l++){
            LinkedList<Integer> datos = heap2.obtenerYEliminar();
            long id_book = datos.get(0);
            int cantidad = datos.get(1);
            for (int i=0;i<10000;i++){
                Book hola1 = (Book) books1.find((long) i);
                if (id_book==hola1.getBook_id()){
                    titulo=hola1.getTitle();
                    System.out.println("Id del libro:" + id_book + "Titulo:" + titulo + "Cantidad:" + cantidad);
                }
            }
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.print("Tiempo de ejecucion de la consulta:"+tiempo);
    }
    public void c3(LinkedList<Book> to_read,HashImpl<Long,Rating> ratings) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        HashImpl<Long,Rating> ratings1 = ratings;
        HeapMax<Long, LinkedList> heap3 = new HeapMax(to_read.getSize());
        for (int i=0;i<1000;i++){
            int counter=0;
            Rating hola= (Rating) ratings1.find((long) i);
            User id_user=hola.getUser_id();
            LinkedList datos = new LinkedList<>();
            datos.addFirst(id_user);
            datos.add(counter);
            try {
                heap3.agregar(id_user.getUser_id(),datos);
            } catch (KeyYaExiste k){
                LinkedList newDatos = heap3.obtenerYEliminar();
                long id_userNew = (long) newDatos.get(0);
                int counterNew = (int) newDatos.get(1);
                counterNew++;
                LinkedList datosNew=null;
                datosNew.addFirst(id_userNew);
                datos.add(counterNew);
                heap3.agregar(id_userNew,datosNew);
            }
        }
        int cantidadEva;
        int ratingProm;
        HeapMax<Long, LinkedList<Integer>> conRating = new HeapMax(9);
        for (int l=0;l<9;l++){
            LinkedList extraccion;
            extraccion = heap3.obtenerYEliminar();
            long id_userNew = (Long) extraccion.get(0);
            cantidadEva = (int) extraccion.get(1);
            ratingProm=0;
            for (int i=0;i<1000;i++){
                Rating hola1 = (Rating) ratings1.find((long) i);
                if (id_userNew==hola1.getUser_id().getUser_id()){ //revisar
                    ratingProm+=hola1.getRating();
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
    public void c4(LinkedList<Book> to_read) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        String cod_idioma=null;
        int cantidad=0;
        HeapMax<String,Integer> idiomas = new HeapMax<>(to_read.getSize());
        for (int i=0;i<to_read.getSize();i++){
            cod_idioma = to_read.get(i).getLanguage_code();
            cantidad = to_read.get(i).getReserved_to_read().length;
            try {
                idiomas.agregar(cod_idioma,cantidad);
            } catch (KeyYaExiste k) {
                int cantidadNew = idiomas.obtenerYEliminar(); //como se que esta es la misma key??
                cantidadNew += cantidad;
                idiomas.agregar(cod_idioma,cantidadNew);
            }
        }
        for (int j=0;j<5;j++){
            cantidad = idiomas.obtenerYEliminar(); //como obtengo mi key?
            System.out.println("Codigo del idioma:"+cod_idioma+"Cantidad:"+cantidad);
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo);
    }
    public void c5(HashImpl<Long,Book> books) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        String autor=null;
        int anio_de_publi=0;
        HeapMin<Long, Integer> anio = new HeapMin<>(10000);
        for (int i=0;i<10000;i++){
            long id = books.find((long) i).getBook_id();
            anio_de_publi = books.find((long) i).getOriginal_publication_year();
            anio.agregar(id,anio_de_publi);
        }
        anio_de_publi = anio.obtenerYEliminar();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();
        int actual = Integer.parseInt(dtf.format(now));
        while (anio_de_publi<actual){
            HeapMax<Integer,Author[]> autores = new HeapMax<>(10000);
            HeapMax<Integer,Integer> autores1 = new HeapMax<>(10000);
            for (int i=0;i<10000;i++){
                int counter=1;
                if (books.find((long) i).getOriginal_publication_year()==anio_de_publi){
                    try {
                        autores.agregar(counter,books.find((long)i).getAuthor());
                        autores1.agregar(counter,counter);
                    } catch (KeyYaExiste k){
                        Author[] autor1 = autores.obtenerYEliminar();
                        counter = autores1.obtenerYEliminar();
                        counter++;
                        autores.agregar(counter,autor1);
                        autores1.agregar(counter,counter);
                    }
                }
            }
            for (int i=0;i<20;i++){
                Author[] autor2 = autores.obtenerYEliminar();
                int counter = autores1.obtenerYEliminar();
                System.out.println("Autor:"+autor2+"Anio de publicacion:"+anio_de_publi+"Cantidad:"+counter);
            }
            anio_de_publi++;
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo);
    }
}
