package moviedb;

import moviedb.tables.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

public class MovieDatabase {

    public static void main(String[] args){
        InputDatabaseController inCtrl = new InputDatabaseController();
        OutputDatabaseController outCtrl = new OutputDatabaseController();
        int choice;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            System.out.println("What do you want to do?");
            System.out.println("1: Insert Person");
            System.out.println("2: Insert Movie");
            System.out.println("3: Exit");

            try {
                choice = Integer.valueOf(reader.readLine());
            } catch (IOException e) {
                System.out.println("Input format error. Try again");
                continue;
            }
            switch(choice){
                case 1:{
                    inCtrl.createPerson(reader, outCtrl);
                    break;
                }
                case 2:{
                    int mediaItemID = inCtrl.createMediaType(reader, outCtrl);
                    String userChoice = "";
                    System.out.println("Now, register actors");
                    while(!userChoice.equals("no")){
                        inCtrl.givePersonActingRoleInMediaItem(reader, outCtrl, mediaItemID);
                        System.out.println("If you want to add another Actor, type yes, if all actors are added, type no!");
                        userChoice = DatabaseController.getUserInput(reader);
                    }
                    userChoice = "";
                    System.out.println("Now, register producers");
                    while(!userChoice.equals("no")){
                        inCtrl.givePersonProductionRoleInMediaItem(reader, outCtrl, mediaItemID);
                        System.out.println("If you want to add a producer, type yes, if all actors are added, type no!");
                        userChoice = DatabaseController.getUserInput(reader);
                    }
                }
            }
        }

/*        Person pers = new Person("1982-04-09", "Jay Baruchel", "Canadian");
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
        System.out.println("Done!");*/
    }
}
