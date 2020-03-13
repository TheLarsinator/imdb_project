package moviedb;

import moviedb.tables.ActorInMedia;
import moviedb.tables.Person;
import moviedb.tables.ProducerInMedia;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class InputDatabaseController extends DatabaseController {

    public InputDatabaseController(){
        super();
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
