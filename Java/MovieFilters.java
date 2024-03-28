import java.util.*;

public class MovieFilters {
    public static Movie getOldestMovie(ArrayList<Movie> listOfMovies) {
        Movie oldestMovie = listOfMovies.getFirst();
        for (Movie movie : listOfMovies) {
            if (movie.getYear() < oldestMovie.getYear()) {
                oldestMovie = movie;
            }
        }
        return oldestMovie;
    }

    public static Movie getNewestMovie(ArrayList<Movie> listOfMovies) {
        Movie newestMovie = listOfMovies.getFirst();
        for (Movie movie : listOfMovies) {
            if (movie.getYear() > newestMovie.getYear()) {
                newestMovie = movie;
            }
        }
        return newestMovie;
    }

    public static Movie getMostActorsInMovies(ArrayList<Movie> listOfMovies) {
        Movie mostActorsMovie = listOfMovies.getFirst();
        for (Movie movie : listOfMovies) {
            if ((movie.getActors()).size() > (mostActorsMovie.getActors()).size()) {
                mostActorsMovie = movie;
            }
        }
        return mostActorsMovie;
    }

    public static Movie getMostAwardsMovie(ArrayList<Movie> listOfMovies) {
        Movie mostAwardsMovie = listOfMovies.getFirst();
        for (Movie movie : listOfMovies) {
            if (movie.getAwards().size() > (mostAwardsMovie.getAwards()).size()) {
                mostAwardsMovie = movie;
            }
        }
        return mostAwardsMovie;
    }

    public static Movie getLessAwardsMovie(ArrayList<Movie> listOfMovies) {
        Movie lessAwardedMovie = listOfMovies.getFirst();
        for (Movie movie : listOfMovies) {
            if ((movie.getAwards()).size() < (lessAwardedMovie.getAwards()).size()) {
                lessAwardedMovie = movie;
            }
        }
        return lessAwardedMovie;
    }

    public static Movie getLongestMovie(ArrayList<Movie> listOfMovies) {
        Movie longestMovie = listOfMovies.getFirst();
        for (Movie movie : listOfMovies) {
            if (movie.getRunTime() > longestMovie.getRunTime()) {
                longestMovie = movie;
            }
        }
        return longestMovie;
    }

    public static Movie getShortestMovie(ArrayList<Movie> listOfMovies) {
        Movie shortestMovie = listOfMovies.getFirst();
        for (Movie movie : listOfMovies) {
            if (movie.getRunTime() < shortestMovie.getRunTime()) {
                shortestMovie = movie;
            }
        }
        return shortestMovie;
    }

    public static ArrayList<Movie> getBestMakeupMovies(ArrayList<Movie> listOfMovies) {
        ArrayList<Movie> arrayOfBestMakeup = new ArrayList<>();
        for (Movie movie : listOfMovies) {
            if (movie.getAwards().contains("Academy Award - Best Makeup")) {
                arrayOfBestMakeup.add(movie);
            }
        }
        return arrayOfBestMakeup;
    }

    public static HashMap<String, ArrayList<Movie>> getMapOfMovieCountry(ArrayList<Movie> listOfMovies) {
        HashMap<String, ArrayList<Movie>> countryMovies = new HashMap<>();
        for (Movie movie : listOfMovies) {
            if (!(countryMovies.containsKey(movie.getCountry()))) {
                ArrayList<Movie> movies = new ArrayList<>() {{
                    add(movie);
                }};
                countryMovies.put(movie.getCountry(), movies);
            } else {
                countryMovies.get(movie.getCountry()).add(movie);
            }
        }
        return countryMovies;
    }

    public static ArrayList<String> getTop5Movies(ArrayList<User> listOfUsers) {
        HashMap<String, Integer> moviesCount = new HashMap<>();
        ArrayList<String> finalList = new ArrayList<>();
        for (User user : listOfUsers) {
            ArrayList<String> favoriteMovies = user.getFavoriteMovies();
            for (String movie : favoriteMovies) {
                if (!(moviesCount.containsKey(movie))) {
                    moviesCount.put(movie, Collections.frequency(favoriteMovies, movie));
                } else {
                    moviesCount.replace(movie, moviesCount.get(movie) + 1);
                }
            }
        }
        ArrayList<Integer> myArray = new ArrayList<>();
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : moviesCount.entrySet()) {
            myArray.add(entry.getValue());
        }
        myArray.sort(Collections.reverseOrder());
        for (Integer data : myArray) {
            for (Map.Entry<String, Integer> entry : moviesCount.entrySet()) {
                if (entry.getValue().equals(data)) {
                    sortedMap.put(entry.getKey(), data);
                }
            }
        }
        int i = 0;
        Set<String> keys = sortedMap.keySet();
        for (String key : keys) {
            finalList.add(key);
            if (i == 4) {
                break;
            }
            i++;
        }
        return finalList;
    }

    public static Integer getYearWithMostMovies(ArrayList<Movie> movies) {
        LinkedHashMap<Integer, ArrayList<String>> yearsMovie = new LinkedHashMap<>();
        for (Movie movie : movies) {
            if (!(yearsMovie.containsKey(movie.getYear()))) {
                yearsMovie.put((movie.getYear()), new ArrayList<>() {{
                    add(String.valueOf(movie.getTitle()));
                }});
            } else {
                yearsMovie.get(movie.getYear()).add(String.valueOf(movie.getTitle()));
            }
        }
        int maxYear = 0;
        int yearWithMostMovies = 0;
        for (Map.Entry<Integer, ArrayList<String>> year : yearsMovie.entrySet()) {
            if (maxYear < year.getValue().size()) {
                maxYear = year.getValue().size();
                yearWithMostMovies = year.getKey();
            }
        }
        return yearWithMostMovies;
    }

    public static void getMostGenreInFavorite(ArrayList<User> listOfUsers, ArrayList<Movie> movies) {
        for (User user : listOfUsers) {
            Map<String, Integer> genreFavoriteMovies = new HashMap<>();
            for (String movie : user.getFavoriteMovies()) {
                for (Movie movieToCheck : movies) {
                    if (movie.equals(movieToCheck.getTitle())) {
                        for (String genre : movieToCheck.getGenre()) {
                            if (!(genreFavoriteMovies.containsKey(genre))) {
                                genreFavoriteMovies.put(genre, 1);
                            } else {
                                genreFavoriteMovies.replace(genre, genreFavoriteMovies.get(genre) + 1);
                            }
                        }
                    }
                }
            }
            int maxGenre = 0;
            String topGenre = null;
            for (Map.Entry<String, Integer> genre : genreFavoriteMovies.entrySet()) {
                if (maxGenre < genre.getValue()) {
                    maxGenre = genre.getValue();
                    topGenre = genre.getKey();
                }
            }
            System.out.println(STR."\{user.getName()} \{topGenre}");
        }
    }


}
