package moviedb;

import moviedb.tables.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

public class OutputDatabaseController extends DatabaseController {

    public OutputDatabaseController(){
        super();
    }

    public Person findPersonByName(String name){
        Person outPers = new Person(name);
        outPers.initialize(conn);
        System.out.println("Finding person status:");
        System.out.println(outPers.getName());
        System.out.println(outPers.getNationality());
        return outPers;
    }
}
