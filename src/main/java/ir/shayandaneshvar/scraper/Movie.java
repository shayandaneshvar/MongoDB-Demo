package ir.shayandaneshvar.scraper;

public class Movie {
    private String name;
    private Integer year;
    private Double rating;
    private String cover;

    public Movie(String name, String year, String rating, String cover) {
        this.name = name;
        setYear(year);
        setRating(rating);
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(String year) {
        try {
            this.year = Integer.parseInt(year);
        } catch (Exception e) {
            this.year = 0;
        }
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(String rating) {
        try {
            this.rating = Double.parseDouble(rating);
        } catch (Exception e) {
            this.rating = 0d;
        }
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
