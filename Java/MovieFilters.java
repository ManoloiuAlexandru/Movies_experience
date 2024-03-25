import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MovieFilters {
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

}
