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

    public boolean createPerson(BufferedReader reader, OutputDatabaseController outCtrl){
        String name;
        String birthDate;
        String nationality;

        System.out.println("Creating person...");
        System.out.println("Insert person name:");
        name = getUserInput(reader);

        //Check if this person already exists, should be primary key but too late to change that now
        Person pers = outCtrl.findPersonByName(name);
        if(pers.getNationality() != null){
            System.out.println("Person already exists. Choose new option");
            return true;
        }

        System.out.println("Insert person birth date:");
        birthDate = getUserInput(reader);
        System.out.println("Insert person nationality:");
        nationality = getUserInput(reader);

        return createEntry(new Person(birthDate, name, nationality));
    }
}
