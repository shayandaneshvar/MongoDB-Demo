package ir.shayandaneshvar.scraper;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;
import java.util.stream.Collectors;

public class MongoDBMovieDao {
    private final MongoClient mongoClient = new MongoClient("localhost", 27017);
    private final MongoCredential credential = MongoCredential.createCredential(
            "tester", "movies", "mypassword".toCharArray());
    private final MongoDatabase database = mongoClient.getDatabase("movies");

    public MongoDBMovieDao() {
        database.getCollection("imdb").drop();
        database.createCollection("imdb");
    }

    public void saveAll(List<Movie> movies) {
        List<Document> toBeSaved = movies
                .stream()
                .map(mov -> new Document("title", mov.getName())
                        .append("year", mov.getYear())
                        .append("rating", mov.getRating())
                        .append("coverUrl", mov.getCover()))
                .collect(Collectors.toList());
        database.getCollection("imdb").insertMany(toBeSaved);

    }
}
