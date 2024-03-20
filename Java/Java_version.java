import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


public class Main {

    public static Map<Object, Object> getOldestMovie(ArrayList<Map<Object, Object>> listOfMovies) {
        Map<Object, Object> oldestMovie = listOfMovies.getFirst();
        for (Map<Object, Object> movie : listOfMovies) {
            if ((Integer) movie.get("year") < (Integer) oldestMovie.get("year")) {
                oldestMovie = movie;
            }
        }
        return oldestMovie;
    }

    public static Map<Object, Object> getNewestMovie(ArrayList<Map<Object, Object>> listOfMovies) {
        Map<Object, Object> newestMovie = listOfMovies.getFirst();
        for (Map<Object, Object> movie : listOfMovies) {
            if ((Integer) movie.get("year") > (Integer) newestMovie.get("year")) {
                newestMovie = movie;
            }
        }
        return newestMovie;
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
        }
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
