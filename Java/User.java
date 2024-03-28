import java.util.ArrayList;

public class User {
    private final String name;
    private final String password;
    private final String email;
    private final String cardNumber;
    private final ArrayList<String> favoriteMovies;
    private String salt;
    private String hash;

    public User(String name, String password, String email, String cardNumber, ArrayList<String> favoriteMovies) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.cardNumber = cardNumber;
        this.favoriteMovies = favoriteMovies;

    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
