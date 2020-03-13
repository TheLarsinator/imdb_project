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

        System.out.println("Welcome to LV Movie Database");
        System.out.println("Use the commandline to interact with the system");
        System.out.println("Date format: YYYY-MM-DD, must be followed!");

        while(true) {
            System.out.println("What do you want to do?");
            System.out.println("1: Find all roles of an actor");
            System.out.println("2: Find all movies for an actor");
            System.out.println("3: Find the most productive company for a genre");
            System.out.println("4: Insert new Movie, Series or Episode");
            System.out.println("5: Leave a review on an Episode");
            System.out.println("6: Exit");

            try {
                choice = Integer.valueOf(reader.readLine());
            } catch (IOException e) {
                System.out.println("Input format error. Try again");
                continue;
            }
            switch(choice){
                case 1:{
                    System.out.println("Which actor do you want to list all roles for?");
                    outCtrl.listAllRolesForAPerson(DatabaseController.getUserInput(reader));
                    break;
                }
                case 2:{
                    System.out.println("Which actor do you want to list all movies for?");
                    outCtrl.listAllMoviesForAnActor(DatabaseController.getUserInput(reader));
                    break;
                }
                case 3:{
                    System.out.println("Which genre do you want to find the most productive company for?");
                    outCtrl.findCompanyWithMostMediaItemsInGenre(DatabaseController.getUserInput(reader));
                    break;
                }
                case 4:{
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
                    break;
                }
                case 5:{
                    System.out.println("What is your username?");
                    String username = DatabaseController.getUserInput(reader);
                    User user = inCtrl.createOrFindUser(reader, username);
                    System.out.println("Select Series, enter the ID:");
                    outCtrl.printAllSeriesWithIdAndTitle(reader);
                    int seriesID = Integer.parseInt(DatabaseController.getUserInput(reader));
                    System.out.println("Select Episode, enter the ID:");
                    outCtrl.printAllEpisodesInSeriesWithIdAndTitle(reader, seriesID);
                    int episodeID = Integer.parseInt(DatabaseController.getUserInput(reader));
                    System.out.println("Rate episode from 1 to 10");
                    int rating = Integer.parseInt(DatabaseController.getUserInput(reader));
                    System.out.println("Leave a comment");
                    String comment = DatabaseController.getUserInput(reader);
                    MediaReview review = new MediaReview(username, episodeID, rating, comment);
                    inCtrl.createEntry(review);
                    break;
                }
                case 6:{
                    System.out.println("Thanks for using LVMDB, see you again!");
                    return;
                }
            }
        }
    }
}
