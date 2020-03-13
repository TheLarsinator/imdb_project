package moviedb;

import moviedb.tables.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class InputDatabaseController extends DatabaseController {

    public InputDatabaseController(){
        super();
    }

    public int createMediaType(BufferedReader reader, OutputDatabaseController outCtrl){
        String title;
        String releaseDate;
        String storyline;
        String genre;
        String companyName;
        int soundtrackID;
        String mediaType = "";

        System.out.println("Creating new mediaitem:");
        System.out.println("What is the title?");
        title = getUserInput(reader);
        System.out.println("What is the release date?");
        releaseDate = getUserInput(reader);
        System.out.println("Short summary of the movie:");
        storyline = getUserInput(reader);
        System.out.println("What genre does the mediaitem belong to?");
        genre = getUserInput(reader);
        System.out.println("Which company has release the mediaitem?");
        companyName = getUserInput(reader);

        while(mediaType == "") {
            System.out.println("Is it a:");
            System.out.println("1: Movie");
            System.out.println("2: Series");
            System.out.println("3: Episode");

            int a = Integer.parseInt(getUserInput(reader));
            switch(a){
                case 1:{
                    mediaType = "Movie";
                    System.out.println("What is the production format of the movie? Ie. Cinema, streaming etc.");
                    String producedFor = getUserInput(reader);
                    System.out.println("Movie lenght in minutes?");
                    int length = Integer.parseInt(getUserInput(reader));
                    Movie mov = new Movie(title, releaseDate, storyline, genre, mediaType, producedFor, length);
                    createEntry(mov);
                    return mov.mediaItemID;
                }
                case 2:{
                    mediaType = "Series";
                    System.out.println("What is the starting year of the series?");
                    int startYear = Integer.parseInt(getUserInput(reader));
                    Series series = new Series(title, releaseDate, storyline, genre, mediaType, startYear);
                    createEntry(series);
                    return series.mediaItemID;
                }
                case 3:{
                    mediaType = "Episode";
                    break;
                }
            }
        }
        return -1;
    }




    public Person createPerson(BufferedReader reader, OutputDatabaseController outCtrl){
        String name;
        String birthDate;
        String nationality;

        System.out.println("Creating person...");
        System.out.println("Insert person name:");
        name = getUserInput(reader);

        //Check if this person already exists, should be primary key but too late to change that now
        Person person = outCtrl.findPersonByName(name);
        if(person.getNationality() != null){
            System.out.println("Person already exists. Choose new option");
            return person;
        }

        System.out.println("Insert person birth date:");
        birthDate = getUserInput(reader);
        System.out.println("Insert person nationality:");
        nationality = getUserInput(reader);
        person = new Person(birthDate, name, nationality);
        createEntry(person);
        return person;
    }

    public void givePersonActingRoleInMediaItem(BufferedReader reader, OutputDatabaseController outCtrl, int mediaItemID){
        Person actor = createPerson(reader, outCtrl);

        System.out.println("What is " + actor.getName() + "'s role?");
        String role = getUserInput(reader);

        ActorInMedia aim = new ActorInMedia(actor.getName(), mediaItemID, role);
        createEntry(aim);
    }

    public void givePersonProductionRoleInMediaItem(BufferedReader reader, OutputDatabaseController outCtrl, int mediaItemID){
        Person producer = createPerson(reader, outCtrl);

        System.out.println("What is " + producer.getName() + "'s role?");
        int role = 0;
        while(role == 0) {
            System.out.println("1: Director");
            System.out.println("2: Writer");

            int a = Integer.parseInt(getUserInput(reader));
            if(a == 1 || a == 2){
                role = a;
            }
            else{
                continue;
            }
        }

        ProducerInMedia pim = new ProducerInMedia(producer.getName(), mediaItemID, role == 1 ? "Director" : "Writer" );
        createEntry(pim);
    }
}
