public class Rating {

    private Integer rating;
    private User user;
    private Book book;

    public Rating(Integer rating, User user, Book book) {
        this.rating = rating;
        this.user = user;
        this.book = book;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}