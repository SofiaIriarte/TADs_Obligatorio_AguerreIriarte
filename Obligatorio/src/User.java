import tads.LinkedList.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class User {

    private long user_id;

    public User(long user_id) {
        this.user_id = user_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
