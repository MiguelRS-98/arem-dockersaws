package co.edu.escuelaing.connectiondb;

import com.mongodb.client.MongoClients;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Iterator;
import org.bson.Document;
/**
 * Clase que conecta la parte logica con la base de datos MongoDB.
 * @author Miguel Angel Rodriguez Siachoque
 */
public class ConnectionDB 
{	
    private MongoClient client = (MongoClient) MongoClients.create("mongodb+srv://admin:admin@cluster0.zyezf.mongodb.net/user?retryWrites=true&w=majority");
    private MongoDatabase database = client.getDatabase("user");
    private MongoCollection<Document> collection = database.getCollection("cadenas");
    /**
     * Metodo encargado de obtener los 10 datos a mostrar al usuario.
     * @return Lista de los ultimos 10 registros.
     */
    @SuppressWarnings("empty-statement")
    public ArrayList<String> getData () 
    {
        int totalData = (int) collection.countDocuments();
        ArrayList<String> arrayJson = new ArrayList<String>();
        FindIterable<Document> collectionStrings = collection.find();
        Iterator<Document> iCollections = collectionStrings.iterator();
        int i = 0, contStrings = 0, longShowData = 10;
        while (iCollections.hasNext() && contStrings < longShowData) {
            if ((totalData - longShowData) <= i) {
                contStrings += 1;
                String dataUser = iCollections.next().toJson();
                dataUser = "{" + dataUser.substring(dataUser.indexOf("cadena") - 1, dataUser.length());
                arrayJson.add(dataUser.replaceAll("\"", ""));
                System.out.println(dataUser);
            } else {
                iCollections.next();
                i++;
            }
        }
        return arrayJson;
    }
}