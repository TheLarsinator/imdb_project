package moviedb;

import moviedb.tables.*;

import java.sql.Date;

public class MovieDatabase {

    public static void main(String[] args){
        System.out.println("Hello world!");
        DatabaseController ctrl = new DatabaseController();

        Person pers = new Person("1982-04-09", "Jay Baruchel", "Canadian");
        Movie movie = new Movie("How to train your dragon", "2010-03-26", "Toothless is a cute dragon", "Family", "Cinema", 98);
        Series series = new Series("Game of Thrones", "2009-04-01", "Who will sit on the throne?", "fantasy", 2009);
        ctrl.createEntry(pers);
        ctrl.createEntry(movie);
        ctrl.createEntry(series);
        series.endSeries(2019);
        ctrl.updateEntry(series);

        ActorInMedia actor = new ActorInMedia(pers.personID, movie.mediaItemID, "Hiccup");
        ctrl.createEntry(actor);

        System.out.println(actor.role);
        System.out.println(pers.personID);
        System.out.println(movie.mediaItemID);
        System.out.println("Done!");
    }
}
