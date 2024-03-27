import java.util.ArrayList;
import java.util.Map;

public class Movie {
    private final String title;
    private final Integer year;
    private final String director;
    private final ArrayList<String> genre;
    private final Double imdbRating;
    private final ArrayList<String> actors;
    private final Integer runTime;
    private final String country;
    private ArrayList<String> awards;

    public Movie(String title, Integer year, String director, ArrayList<String> genre, Double imdbRating, ArrayList<String> actors, Integer runTime, String country, ArrayList<Object> awards) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.genre = genre;
        this.imdbRating = imdbRating;
        this.actors = actors;
        this.runTime = runTime;
        this.country = country;
        setAwards(awards);
    }

    public void setAwards(ArrayList<Object> awards) {
        ArrayList<String> correctAwards = new ArrayList<>();
        for (Object award : awards) {
            if (award instanceof Map<?, ?>) {
                String processedAward = STR."\{((Map<?, ?>) award).get("name")} - \{((Map<?, ?>) award).get("category")}";
                correctAwards.add(processedAward);
            } else if (award instanceof String) {
                correctAwards.add(String.valueOf(award));
            }
        }
        this.awards = correctAwards;
    }

    public Integer getYear() {
        return year;
    }

    public String getCountry() {
        return country;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public ArrayList<String> getAwards() {
        return awards;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return STR."Movie{title='\{title}\{'\''}, year=\{year}, director='\{director}\{'\''}, genre=\{genre}, imdbRating=\{imdbRating}, actors=\{actors}, runTime=\{runTime}, country='\{country}\{'\''}, awards=\{awards}\{'}'}";
    }

}
