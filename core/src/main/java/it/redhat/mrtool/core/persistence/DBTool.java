package it.redhat.mrtool.core.persistence;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class DBTool {
    private static DBTool theInstance;
    DB database;

    public static DBTool getInstance(){
        if( theInstance == null ){
            theInstance = new DBTool();
        }
        return theInstance;
    }

    private DBTool(){
        try {
            database = new MongoClient().getDB("mrtool");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public DBCollection getCollection(String collection){
        return database.getCollection(collection);
    }

    public void shutdown(){
        database = null;
    }
}
