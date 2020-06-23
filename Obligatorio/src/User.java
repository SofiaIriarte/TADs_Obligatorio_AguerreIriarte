import tads.LinkedList.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import tads.LinkedList.LinkedList;

public class User implements Comparable{

    private Long user_id;
    private LinkedList<Book> ListaReservados;
    private LinkedList<Rating> ListaRatings;

    /*public User(long user_id, LinkedList<Book> listaReservados, LinkedList<Rating> ratings) {
        this.user_id = user_id;
        ListaReservados = listaReservados;
        this.ListaRatings = ratings;
    }*/

    public User(long user_id) {
        this.user_id = user_id;
        ListaReservados = new LinkedList<>();
        ListaRatings = new LinkedList<>();
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public LinkedList<Book> getListaReservados() {
        return ListaReservados;
    }

    public void setListaReservados(LinkedList<Book> listaReservados) {
        ListaReservados = listaReservados;
    }

    public LinkedList<Rating> getRatings() {
        return ListaRatings;
    }

    public void setRatings(LinkedList<Rating> ratings) {
        this.ListaRatings = ratings;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.parseInt(String.valueOf(user_id));
    }
}