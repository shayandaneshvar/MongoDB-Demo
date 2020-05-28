package ir.shayandaneshvar.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scraper {
    public static void main(String[] args) throws IOException {
        MongoDBMovieDao dao = new MongoDBMovieDao();
        dao.saveAll(getTop250());
        System.err.println("Done!");
    }

    public static List<Movie> getTop250() throws IOException {
        List<Movie> movies = new ArrayList<>();
        final String imdb =
                "https://www.imdb.com/chart/top";
        final Document document = Jsoup.connect(imdb).get();
        for (Element element :
                document.select("table.chart.full-width tr")) {
            String title = element.select(".titleColumn a").text();
            String year = element.select(".titleColumn span").text()
                    .replace("(", "").replace(")", "");
            String rating = element.select(".imdbRating").text();
            String image = element.select(".posterColumn a img").attr("src");
            var movie = new Movie(title, year, rating, image);
            movies.add(movie);
            System.err.println(title + " " + rating + " " + year);
        }
        return movies;
    }
}
