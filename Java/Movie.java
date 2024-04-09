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

    private String ISBNCode;

    public Movie(String title, Integer year, String director, ArrayList<String> genre, Double imdbRating, ArrayList<String> actors, Integer runTime, String country, ArrayList<Object> awards, String ISBNCode) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.genre = genre;
        this.imdbRating = imdbRating;
        this.actors = actors;
        this.runTime = runTime;
        this.country = country;
        setAwards(awards);
        setISBNCode(ISBNCode);
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

    public void setISBNCode(String ISBNCode) {
        if (ISBNCode.length() == 10) {
            this.ISBNCode = ISBN10(ISBNCode);
        } else if (ISBNCode.length() == 13) {
            this.ISBNCode = ISBN13(ISBNCode);
        }
    }

    private String ISBN13(String isbnCode) {
        int sumOfCf = 0;
        int counter = 1;
        String ISBNCodeOriginal = isbnCode;
        String copyOfCode;
        if (isbnCode.charAt(isbnCode.length() - 1) == 'X' || isbnCode.length() == 13) {
            while (!isbnCode.isEmpty()) {
                if (isbnCode.charAt(0) == 'X') {
                    sumOfCf += 10;
                } else {
                    sumOfCf += counter * Character.digit(isbnCode.charAt(0), 10);
                }
                copyOfCode = isbnCode.substring(1);
                isbnCode = copyOfCode;
                if (counter == 1) {
                    counter = 3;
                } else {
                    counter = 1;
                }
            }
            if (sumOfCf % 10 == 0) {
                return ISBNCodeOriginal;
            } else {
                return "";
            }
        }
        return "";
    }

    private String ISBN10(String ISBNCode) {
        if (ISBNCode.length() != 10) {
            return "";
        }
        int sumOfCf = 0;
        int counter = 10;
        String ISBNCodeOriginal = ISBNCode;
        String copyOfCode;
        if (ISBNCode.charAt(ISBNCode.length() - 1) == 'X' || ISBNCode.length() == 10) {
            while (!ISBNCode.isEmpty()) {
                if (ISBNCode.charAt(0) == 'X') {
                    sumOfCf += 10;
                } else {
                    sumOfCf += counter * Character.digit(ISBNCode.charAt(0), 10);
                }
                copyOfCode = ISBNCode.substring(1);
                ISBNCode = copyOfCode;
                counter--;
            }
            if (sumOfCf % 11 != 0) {
                return "";
            } else {
                return ISBNCodeOriginal;
            }
        }
        return "";
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
