import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


public class Main {

    public static Map<Object, Object> getOldestMovie(ArrayList<Map<Object, Object>> listOfMovies) {
        Map<Object, Object> oldestMovie = listOfMovies.getFirst();
        for (Map<Object, Object> movie : listOfMovies) {
            if ((int) movie.get("year") < (int) oldestMovie.get("year")) {
                oldestMovie = movie;
            }
        }
        return oldestMovie;
    }

    public static Map<Object, Object> getNewestMovie(ArrayList<Map<Object, Object>> listOfMovies) {
        Map<Object, Object> newestMovie = listOfMovies.getFirst();
        for (Map<Object, Object> movie : listOfMovies) {
            if ((int) movie.get("year") > (int) newestMovie.get("year")) {
                newestMovie = movie;
            }
        }
        return newestMovie;
    }

    public static Map<Object, Object> getMostActorsInMovies(ArrayList<Map<Object, Object>> listOfMovies) {
        Map<Object, Object> mostActorsMovie = listOfMovies.getFirst();
        for (Map<Object, Object> movie : listOfMovies) {
            if (((ArrayList<?>) movie.get("actors")).size() > ((ArrayList<?>) mostActorsMovie.get("actors")).size()) {
                mostActorsMovie = movie;
            }
        }
        return mostActorsMovie;
    }

    public static Map<Object, Object> getMostAwardsMovie(ArrayList<Map<Object, Object>> listOfMovies) {
        Map<Object, Object> mostAwardsMovie = listOfMovies.getFirst();
        for (Map<Object, Object> movie : listOfMovies) {
            if (((ArrayList<?>) movie.get("awards")).size() > ((ArrayList<?>) mostAwardsMovie.get("awards")).size()) {
                mostAwardsMovie = movie;
            }
        }
        return mostAwardsMovie;
    }

    public static Map<Object, Object> getLessAwardsMovie(ArrayList<Map<Object, Object>> listOfMovies) {
        Map<Object, Object> lessAwardedMovie = listOfMovies.getFirst();
        for (Map<Object, Object> movie : listOfMovies) {
            if (((ArrayList<?>) movie.get("awards")).size() < ((ArrayList<?>) lessAwardedMovie.get("awards")).size()) {
                lessAwardedMovie = movie;
            }
        }
        return lessAwardedMovie;
    }

    public static Map<Object, Object> getLongestMovie(ArrayList<Map<Object, Object>> listOfMovies) {
        Map<Object, Object> longestMovie = listOfMovies.getFirst();
        for (Map<Object, Object> movie : listOfMovies) {
            if ((int) movie.get("runtime") > (int) longestMovie.get("runtime")) {
                longestMovie = movie;
            }
        }
        return longestMovie;
    }

    public static Map<Object, Object> getShortestMovie(ArrayList<Map<Object, Object>> listOfMovies) {
        Map<Object, Object> shortestMovie = listOfMovies.getFirst();
        for (Map<Object, Object> movie : listOfMovies) {
            if ((int) movie.get("runtime") < (int) shortestMovie.get("runtime")) {
                shortestMovie = movie;
            }
        }
        return shortestMovie;
    }

    public static ArrayList<Map<Object, Object>> getBestMakeupMovies(ArrayList<Map<Object, Object>> listOfMovies) {
        ArrayList<Map<Object, Object>> arrayOfBestMakeup = new ArrayList<>();
        for (Map<Object, Object> movie : listOfMovies) {
            if (((ArrayList<String>) movie.get("awards")).contains("Academy Award - Best Makeup")) {
                arrayOfBestMakeup.add(movie);
            }
        }
        return arrayOfBestMakeup;
    }

    public static HashMap<String, ArrayList<Map<Object, Object>>> getMapOfMovieCountry(ArrayList<Map<Object, Object>> listOfMovies) {
        HashMap<String, ArrayList<Map<Object, Object>>> countryMovies = new HashMap<>();
        for (Map<Object, Object> movie : listOfMovies) {
            if (!(countryMovies.containsKey(movie.get("country")))) {
                ArrayList<Map<Object, Object>> movies = new ArrayList<>() {{
                    add(movie);
                }};
                countryMovies.put((String) movie.get("country"), movies);
            } else {
                countryMovies.get((String) movie.get("country")).add(movie);
            }
        }
        return countryMovies;
    }

    public static String generateRandomSalt() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public static void saltThePasswords(ArrayList<Map<String, String>> listOfUsers) {
        for (Map<String, String> user : listOfUsers) {
            user.put("salt", generateRandomSalt());
            user.put("hash", user.get("password") + String.valueOf(user.get("salt")).hashCode());
        }
    }

    public static void encryptEmail(ArrayList<Map<String, String>> listOfUsers) {
        int shifting = 5;
        for (Map<String, String> user : listOfUsers) {
            String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String email = user.get("email").split("@")[0];
            StringBuilder encryptedEmail = new StringBuilder();
            for (int i = 0; i < email.length(); i++) {
                if (alphabet.contains(String.valueOf(email.charAt(i))))
                    encryptedEmail.append(alphabet.charAt((alphabet.indexOf(email.charAt(i)) + shifting) % 52));

                else
                    encryptedEmail.append(email.charAt(i));

            }
            System.out.println(encryptedEmail);
        }
    }

    public static boolean passwordCheck(String password) {
        boolean upper = false, lower = false, number = false;
        for (int i = 0; i < password.length(); i++) {
            if (StringUtils.isAllUpperCase(String.valueOf(password.charAt(i)))) {
                upper = true;
            } else if (StringUtils.isAllLowerCase(String.valueOf(password.charAt(i)))) {
                lower = true;
            } else if (StringUtils.isNumeric(String.valueOf(password.charAt(i)))) {
                number = true;
            }
        }
        return upper && lower && number && password.length() >= 8;
    }

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
        for (Map.Entry<Object, Object> entry : responseFromServer.entrySet()) {
            ArrayList<Map<Object, Object>> movies = (ArrayList<Map<Object, Object>>) entry.getValue();
            System.out.println(getOldestMovie(movies));
            System.out.println(getNewestMovie(movies));
            System.out.println(getMostActorsInMovies(movies));
            System.out.println(getMostAwardsMovie(movies));
            System.out.println(getLessAwardsMovie(movies));
            System.out.println(getLongestMovie(movies));
            System.out.println(getShortestMovie(movies));
            System.out.println(getBestMakeupMovies(movies));
            System.out.println(getMapOfMovieCountry(movies));
        }
        ArrayList<Map<String, String>> listOfUsers = getMaps();
        saltThePasswords(listOfUsers);
        Scanner myObj = new Scanner(System.in);
        String userName = myObj.nextLine();
        String enteredPassword = myObj.nextLine();
        for (Map<String, String> user : listOfUsers) {
            if (user.get("nume").equals(userName)) {
                if ((enteredPassword + user.get("salt").hashCode()).equals(user.get("hash"))) {
                    System.out.println("Correct Password");
                }
            }
        }
        System.out.println(passwordCheck(listOfUsers.get(1).get("password")));
        encryptEmail(listOfUsers);
    }

    private static ArrayList<Map<String, String>> getMaps() {
        ArrayList<Map<String, String>> listOfUsers = new ArrayList<>();
        Map<String, String> entry = new HashMap<>() {{
            put("nume", "Ion");
            put("password", "df3213d");
            put("email", "AAASS@yahoo.com");
        }};
        listOfUsers.add(entry);
        entry = new HashMap<>() {{
            put("nume", "Vasile");
            put("email", "sfsgsddsfg@yahoo.com");
            put("password", "Ad5kfkgfJHH90hjh8y");
        }};
        listOfUsers.add(entry);
        entry = new HashMap<>() {{
            put("nume", "Mircea");
            put("email", "dsfsdgfdf@yahoo.com");
            put("password", "df322fd3n32K13d");
        }};
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
