import tads.LinkedList.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Rating {

    private int rating;
    private long user_id;
    private int book_id;

    public Rating(int rating, long user_id, int book_id) {
        this.rating = rating;
        this.user_id = user_id;
        this.book_id = book_id;
    }

    public Rating() {
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
}
