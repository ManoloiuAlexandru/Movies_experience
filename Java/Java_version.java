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
        for (Map.Entry<Object, Object> entry : responseFromServer.entrySet()) {
            ArrayList<Map<Object, Object>> movies = (ArrayList<Map<Object, Object>>) entry.getValue();
            System.out.println(MovieFilters.getOldestMovie(movies));
            System.out.println(MovieFilters.getNewestMovie(movies));
            System.out.println(MovieFilters.getMostActorsInMovies(movies));
            System.out.println(MovieFilters.getMostAwardsMovie(movies));
            System.out.println(MovieFilters.getLessAwardsMovie(movies));
            System.out.println(MovieFilters.getLongestMovie(movies));
            System.out.println(MovieFilters.getShortestMovie(movies));
            System.out.println(MovieFilters.getBestMakeupMovies(movies));
            System.out.println(MovieFilters.getMapOfMovieCountry(movies));
        }
        ArrayList<Map<String, String>> listOfUsers = getMaps();
        Security.saltThePasswords(listOfUsers);
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
        System.out.println(DataValidation.passwordCheck(listOfUsers.get(1).get("password")));
        Security.encryptEmail(listOfUsers);
        System.out.println(DataValidation.cardValidation(listOfUsers.getFirst().get("card_number")));
        System.out.println(DataValidation.validateEmail(listOfUsers.getFirst().get("email")));
        System.out.println(DataValidation.checkForDuplicateEmail("sduygrwurgwurqwuru@yahoo.com", listOfUsers));
    }

    private static ArrayList<Map<String, String>> getMaps() {
        ArrayList<Map<String, String>> listOfUsers = new ArrayList<>();
        Map<String, String> entry = new HashMap<>() {{
            put("nume", "Ion");
            put("password", "df3213d");
            put("email", "AAASS@yahoo.com");
            put("card_number", "2223577120017656");
        }};
        listOfUsers.add(entry);
        entry = new HashMap<>() {{
            put("nume", "Vasile");
            put("email", "s.dfg@.343.4_34@yah.oo.com");
            put("password", "Ad5kfkgfJHH90hjh8y");
            put("card_number", "4847352989063094");
        }};
        listOfUsers.add(entry);
        entry = new HashMap<>() {{
            put("nume", "Mircea");
            put("email", "dsfsdgfdf@yahoo.com");
            put("password", "df322fd3n32K13d");
            put("card_number", "4847352989087694");
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
