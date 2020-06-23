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

    public void c2(HashImpl<Long,Book> to_read,HashImpl<Long,Book> books) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        String titulo="";
        HeapMax<Integer,Book> cons = new HeapMax<>(912705);
        HashImpl<Long,Integer> consInt = new HashImpl<>(912705);
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
                consInt.put(to_read.find(i).getBook_id(), counter);
                }
            } catch(Exception n){
                i++;
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
                promedio += usuariotemp.getRatings().get(j).getRating();

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
        int cantidad1=1;
        String cod_idioma2="en-CA";
        int cantidad2=2;
        String cod_idioma3="spa";
        int cantidad3=3;
        String cod_idioma4="en-GB";
        int cantidad4=4;
        String cod_idioma5="fre";
        int cantidad5=5;
        String cod_idioma6="por";
        int cantidad6=6;
        HeapMax<Integer,Book> cons = new HeapMax<>(912705);
        LinkedList idiomasList=new LinkedList();
        LinkedList idiomasList1=new LinkedList();
        LinkedList idiomasList2=new LinkedList();
        LinkedList idiomasList3=new LinkedList();
        LinkedList idiomasList4=new LinkedList();
        LinkedList idiomasList5=new LinkedList();
        LinkedList idiomasList6=new LinkedList();
        HeapMax<Integer,LinkedList> idiomas = new HeapMax<>(10);
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
                if (book.getLanguage_code() == cod_idioma) {
                    cantidad++;
                    m++;
                }
                else if (book.getLanguage_code() == cod_idioma1) {
                    cantidad1++;
                    m++;
                }
                else if (book.getLanguage_code() == cod_idioma2) {
                    cantidad2++;
                    m++;
                }
                else if (book.getLanguage_code() == cod_idioma3) {
                    cantidad3++;
                    m++;
                }
                else if (book.getLanguage_code() == cod_idioma4) {
                    cantidad4++;
                    m++;
                }
                else if (book.getLanguage_code() == cod_idioma5) {
                    cantidad5++;
                    m++;                }
                else if (book.getLanguage_code() == cod_idioma6) {
                    cantidad6++;
                    m++;
                } else {
                    m++;
                }
            } catch (Exception e){
                m++;
            }
        }
        System.out.println(cantidad+"   "+cantidad1+"     "+cantidad2+"      "+cantidad3+"        "+cantidad4+"        "+cantidad5+"         "+cantidad6);
        idiomasList.add(cantidad);
        idiomasList.add(cod_idioma);
        idiomas.agregar(cantidad+8,idiomasList);
        idiomasList1.add(cantidad1);
        idiomasList1.add(cod_idioma1);
        idiomas.agregar(cantidad1,idiomasList1);
        idiomasList2.add(cantidad2);
        idiomasList2.add(cod_idioma2);
        idiomas.agregar(cantidad2,idiomasList2);
        idiomasList3.add(cantidad3);
        idiomasList3.add(cod_idioma3);
        idiomas.agregar(cantidad3,idiomasList3);
        idiomasList4.add(cantidad4);
        idiomasList4.add(cod_idioma4);
        idiomas.agregar(cantidad4,idiomasList4);
        idiomasList5.add(cantidad5);
        idiomasList5.add(cod_idioma5);
        idiomas.agregar(cantidad5,idiomasList5);
        idiomasList6.add(cantidad6);
        idiomasList6.add(cod_idioma6);
        idiomas.agregar(cantidad6,idiomasList6);
        for (int j=0;j<5;j++){
            LinkedList lista =idiomas.obtenerYEliminar();
            cantidad = (int) lista.get(0);
            //cod_idioma = (String) lista.get(1);
            System.out.println("Codigo del idioma:"+cod_idioma+"Cantidad:"+cantidad);
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo+" ms\n");
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
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo+" ms\n");
    }
}
