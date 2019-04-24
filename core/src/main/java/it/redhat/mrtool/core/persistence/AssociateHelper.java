package it.redhat.mrtool.core.helpers;

import com.mongodb.*;
import it.redhat.mrtool.core.persistence.DBTool;
import it.redhat.mrtool.model.Associate;

public class AssociateHelper {
    public final static String COLLECTION_NAME = "associates";

    public Associate get(String uuid){
        DBObject query = new BasicDBObject("uuid", uuid);
        DBObject dbObject = DBTool.getInstance().getCollection(COLLECTION_NAME).find(query).one();
        return getAssociate(dbObject);
    }

    public Associate getByRedHatID(String rhid){
        DBObject query = new BasicDBObject("rhid", rhid);
        DBObject dbObject = DBTool.getInstance().getCollection(COLLECTION_NAME).find(query).one();
        return getAssociate(dbObject);
    }

    public int insert(Associate associate){
        DBCollection collection = DBTool.getInstance().getCollection(COLLECTION_NAME);
        WriteResult result = collection.insert(getDocument(associate));
        return result.getN();
    }

    public int deleteByRedHatID(String rhid){
        DBObject query = new BasicDBObject("rhid", rhid);
        WriteResult writeResult = DBTool.getInstance().getCollection(COLLECTION_NAME).remove(query);
        return writeResult.getN();
    }

    private DBObject getDocument(Associate associate){
        DBObject dbObject = new BasicDBObject("uuid", associate.getUuid())
                .append("name", associate.getName())
                .append("rhid", associate.getRedhatId())
                    .append("costCenter", associate.getCostCenter())
                .append("email", associate.getEmail());
        return dbObject;
    }

    private Associate getAssociate(DBObject dbObject){
        return new Associate()
                .setUuid((String)dbObject.get("uuid"))
                .setName((String)dbObject.get("name"))
                .setRedhatId((String)dbObject.get("rhid"))
                .setCostCenter((String)dbObject.get("costCenter"))
                .setEmail((String)dbObject.get("email"));
    }
}