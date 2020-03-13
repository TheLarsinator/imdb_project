package moviedb;

import moviedb.tables.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class InputDatabaseController extends DatabaseController {

    public InputDatabaseController(){
        super();
    }

    public boolean createPerson(BufferedReader reader){
        String name;
        String birthDate;
        String nationality;

        System.out.println("Creating person...");
        System.out.println("Insert person name:");
        name = getUserInput(reader);
        System.out.println("Insert person birth date:");
        birthDate = getUserInput(reader);
        System.out.println("Insert person nationality:");
        nationality = getUserInput(reader);

        return createEntry(new Person(birthDate, name, nationality));
    }


    private String getUserInput(BufferedReader reader){
        try{
            String out = reader.readLine();
            return out;
        } catch (IOException e){
            System.out.println("Failed to read user input");
            return "";
        }
    }
}
