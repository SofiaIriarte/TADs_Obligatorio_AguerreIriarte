public class CargasAnteriores {

    /*public LinkedList<Long> id_books() throws IOException{
        LinkedList<Long> id_books = new LinkedList();
        try {
            br = new BufferedReader(new FileReader(booksFile));
            line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] datos = identificadorDeComas(line);
                id_books.add(Long.parseLong(datos[0]));
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
        }
        br.close();
        return id_books;
    }

    */

    /*To_Read
    //if (book_id==book.find(book_id).getBook_id()) {

                    /*    line=br.readLine();
                    } else {
                        line=br.readLine();
                    }*/
                        /*if (to_read.find(book_id)!=book.find(book_id)){
                            System.out.println("heeeeeeey");//i++;//to_read.put(book_id,book.find(book_id));
                        }*/

                        /*ratings
                        *
                        *     int contador=0;
        HashImpl<Long, Rating> ratings = new HashImpl<>(6000000);
        try {
            br = new BufferedReader(new FileReader(ratingsFile));
            line=br.readLine();
            line=br.readLine();
            while (line != null) {
                //if (!(line.contains(",\"nan\"")||  line.contains(",NaN")||  line.contains(",nan"))){
                    String[] datos = identificadorDeComas(line);
                    for (long i=1;i<9998;i++){
                        try{
                            if (Long.parseLong(datos[1])==i){
                                Book libroAEncontrar = books.find(i);
                                try {
                                    User user = new User(Long.parseLong(datos[0]));
                                    Rating dato = new Rating();
                                    dato.setUser_id(user);
                                    dato.setBook_id(libroAEncontrar);
                                    dato.setRating(Integer.parseInt(datos[2]));
                                    ratings.put(user.getUser_id(),dato);
                                    contador++;
                                    line=br.readLine();
                                } catch (Exception e ){
                                    i++;
                                    System.out.println("Error en linea"+(contador+1));
                                }

                         /*       else {
                        try {
                            Book book = new Book();
                            book.setBook_id(Long.parseLong(datos[1]));
                            book.setTitle(null);
                            book.setAuthor(null);
                            book.setImage_url(null);
                            book.setIsbn(null);
                            book.setLanguage_code("nan");
                            book.setOriginal_publication_year(0000);
                            book.setOriginal_title(null);
                            User user = new User(Long.parseLong(datos[0]));
                            Rating dato = new Rating(Integer.parseInt(datos[2]),user,book);
                            ratings.put(user.getUser_id(),dato);
                            contador++;
                        } catch (Exception e) {
                            contador++;
                            System.out.println("Error en linea"+contador);
                        }
                                    System.out.println("d");//line=br.readLine();
                                }
}
                        } catch (NullPointerException n){
                                i++;
                                }
                                }
                                //}
                                //line=br.readLine();
                                }
                                } catch (IOException e) {
                                System.out.print("Error al cargar los datos, intente de nuevo");
                                }
                                System.out.println(contador);
                                br.close();
                                return ratings;
                                }*/


}
