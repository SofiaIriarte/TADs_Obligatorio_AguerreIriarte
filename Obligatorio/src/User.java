import tads.LinkedList.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class User {

    private long user_id;
    private BufferedReader br = null;
    private String to_ReadFile = "src\\docs\\to_read.csv";
    private String line = "";
    private String cvsSplitBy = ",";

    public LinkedList cargaUsers() throws IOException {
        LinkedList user = new LinkedList();
        try {
            br = new BufferedReader(new FileReader(to_ReadFile));
            line=br.readLine();
            String[] datos = line.split(cvsSplitBy);
            user_id= Long.parseLong(datos[0]);
            user.addFirst(user_id);
            line=br.readLine();
            while (line != null) {
                datos = line.split(cvsSplitBy);
                user_id= Long.parseLong(datos[0]);
                user.add(user_id);
                line=br.readLine();
            }
        } catch (IOException e) {
            System.out.print("Error al cargar los usuarios, intente de nuevo");
        }
        br.close();
        return user;
    }


}
