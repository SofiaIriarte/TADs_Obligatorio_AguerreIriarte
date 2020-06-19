import tads.LinkedList.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Rating {

    private int rating;
    public long user_id;
    public int book_id;
    private BufferedReader br = null;
    private String ratingFile = "src\\docs\\ratings.csv";
    private String line = "";
    private String cvsSplitBy = ",";

    public LinkedList cargaRating() throws IOException {
        LinkedList ratings = new LinkedList();
        try {
            br = new BufferedReader(new FileReader(ratingFile));
            line=br.readLine();
            while (line != null) {
                String[] datos = line.split(cvsSplitBy);
                user_id = Long.parseLong(datos[0]);
                book_id = Integer.parseInt(datos[1]);
                rating = Integer.parseInt(datos[2]);
                ratings.addFirst(user_id);
                ratings.add(book_id);
                ratings.add(rating);
                System.out.print(ratings);
                line=br.readLine();
            }
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
        }
        br.close();
        return ratings;
    }

    public void cargaUsers() throws IOException {
        try {
            User user = new User();
            LinkedList usersList = user.cargaUsers();
        } catch (IOException i) {
            System.out.print("No se pudieron obtener los usuarios");
        }
    }

}
