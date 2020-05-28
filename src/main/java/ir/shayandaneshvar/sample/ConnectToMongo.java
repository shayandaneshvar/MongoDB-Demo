package ir.shayandaneshvar.sample;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.function.Consumer;

public class ConnectToMongo {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoCredential credential = MongoCredential.createCredential("tester"
                , "test", "mypassword".toCharArray());
        System.out.println("Credentials: " + credential);
        MongoDatabase database = mongoClient.getDatabase("test");
        database.createCollection("animals");
        database.listCollectionNames().forEach((Consumer<? super String>) System.out::println);
        database.getCollection("animals").drop();
        System.out.println("-----------------");
        database.listCollectionNames().forEach((Consumer<? super String>) System.out::println);
        System.out.println("-----------------");
        deleteIfExists(database);
        database.createCollection("people");
        database.createCollection("presentations");
        Document document = new Document("title", "Introduction to MongoDB")
                .append("course", "Databases")
                .append("professor", "Dr.Farzi")
                .append("presenters", Arrays.asList("S.Daneshvar", "H" +
                        ".Pourallahverdi"));
        database.getCollection("presentations").insertOne(document);
        database.getCollection("presentations").find()
                .forEach((Consumer<? super Document>) System.out::println);
//        database.getCollection("presentations").deleteOne(Filters.eq(
//                "title", "Introduction to MongoDB"));
    }

    private static void deleteIfExists(MongoDatabase database) {
        database.getCollection("people").drop();
        database.getCollection("presentations").drop();
    }
}
