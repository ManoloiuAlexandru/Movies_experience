import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Main {


    public static void main(String[] args) {
        Map<Object, Object> responseFromServer;
        try {
            URL url = new URL("http://127.0.0.1:8080/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException(STR."HttpResponseCode: \{responseCode}");
            } else {

                responseFromServer = getJsonObject(url);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayList<User> listOfUsers = getMaps();
        ArrayList<Movie> listOfMovies = new ArrayList<>();
//        Security.saltThePasswords(listOfUsers);
//        Scanner myObj = new Scanner(System.in);
//        String userName = myObj.nextLine();
//        String enteredPassword = myObj.nextLine();
//        for (User user : listOfUsers) {
//            if (user.getName().equals(userName)) {
//                if ((enteredPassword + user.getSalt().hashCode()).equals(user.getHash())) {
//                    System.out.println("Correct Password");
//                }
//            }
//        }
        System.out.println(DataValidation.passwordCheck(String.valueOf(listOfUsers.get(1).getPassword())));
        Security.encryptEmail(listOfUsers);
        System.out.println(DataValidation.cardValidation(String.valueOf(listOfUsers.getFirst().getCardNumber())));
        System.out.println(DataValidation.validateEmail(String.valueOf(listOfUsers.getFirst().getEmail())));
        System.out.println(DataValidation.checkForDuplicateEmail("sduygrwurgwurqwuru@yahoo.com", listOfUsers));
        System.out.println(MovieFilters.getTop5Movies(listOfUsers));
        for (Map.Entry<Object, Object> entry : responseFromServer.entrySet()) {
            ArrayList<Map<Object, Object>> movies = (ArrayList<Map<Object, Object>>) entry.getValue();
            for (Map<Object, Object> movie : movies) {
                listOfMovies.add(new Movie((String) movie.get("title"), (Integer) movie.get("year"), String.valueOf(movie.get("director")),
                        (ArrayList<String>) (movie.get("genre")), (Double) movie.get("imdb_rating"), (ArrayList<String>) movie.get("actors"),
                        (Integer) movie.get("runtime"), String.valueOf(movie.get("country")), (ArrayList<Object>) movie.get("awards"), (String) movie.get("ISBN")));
            }
        }
//        System.out.println(MovieFilters.getOldestMovie(listOfMovies));
//        System.out.println(MovieFilters.getNewestMovie(listOfMovies));
//        System.out.println(MovieFilters.getMostActorsInMovies(listOfMovies));
//        System.out.println(MovieFilters.getMostAwardsMovie(listOfMovies));
//        System.out.println(MovieFilters.getLessAwardsMovie(listOfMovies));
//        System.out.println(MovieFilters.getLongestMovie(listOfMovies));
//        System.out.println(MovieFilters.getShortestMovie(listOfMovies));
//        System.out.println(MovieFilters.getBestMakeupMovies(listOfMovies));
//        System.out.println(MovieFilters.getMapOfMovieCountry(listOfMovies));
//        System.out.println(MovieFilters.getYearWithMostMovies(listOfMovies));
        MovieFilters.getMostGenreInFavorite(listOfUsers, listOfMovies);
    }

    private static ArrayList<User> getMaps() {
        ArrayList<User> listOfUsers = new ArrayList<>();
        User entry = new User("Ion", "df3213d", "AAA@SS@yahoo.com", "2223577120017656", new ArrayList<>() {{
            add("The Shawshank Redemption");
            add("The Godfather");
        }});
        listOfUsers.add(entry);
        entry = new User("Vasile", "Ad5kfkgfJHH90hjh8y", "s.dfg@.343.4_34@yah.oo.com", "4847352989063094", new ArrayList<>() {{
            add("Spirited Away");
            add("The Lord of the Rings: The Fellowship of the Ring");
            add("Good Will Hunting");
            add("The Matrix");
        }});
        listOfUsers.add(entry);
        entry = new User("Mircea", "df322fd3n32K13d", "dsfsdgfdf@yahoo.com", "4847352989087694", new ArrayList<>() {{
            add("The Shawshank Redemption");
            add("The Godfather");
            add("The Lord of the Rings: The Fellowship of the Ring");
            add("Parasite");
            add("Good Will Hunting");
            add("The Matrix");
        }});
        listOfUsers.add(entry);
        return listOfUsers;
    }

    private static Map<Object, Object> getJsonObject(URL url) throws IOException {
        StringBuilder inline = new StringBuilder();
        Scanner scanner = new Scanner(url.openStream());

        while (scanner.hasNext()) {
            inline.append(scanner.nextLine());
        }

        scanner.close();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(String.valueOf(inline), new TypeReference<>() {
        });

    }
}
