public class Rating {

    private int rating;
    private User user_id;
    private Book book_id;

    public Rating(int rating, User user_id, Book book_id) {
        this.rating = rating;
        this.user_id = user_id;
        this.book_id = book_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Book getBook_id() {
        return book_id;
    }

    public void setBook_id(Book book_id) {
        this.book_id = book_id;
    }
}