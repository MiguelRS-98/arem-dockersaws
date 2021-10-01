package co.edu.escuelaing.json;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.bson.Document;
/**
 * Clase encargada de ingresar los datos del usuario.
 * @author Miguel Angel Rodriguez Siachoque
 */
public class ConnectionJson 
{
    private MongoClient client = (MongoClient) MongoClients.create("mongodb+srv://admin:admin@cluster0.zyezf.mongodb.net/user?retryWrites=true&w=majority");
    private MongoDatabase database = client.getDatabase("user");
    private MongoCollection<Document> collection = database.getCollection("cadenas");
    /**
     * Metodo principal que ingresa la informacion en formato JSON,
     * @param input Registro realizado por el usuario.
     */
    public void addDataJson (String input) 
    {
        Document userInput = new Document();
        userInput.append("cadena", input);
        userInput.append("date", new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(Calendar.getInstance().getTime()));
        collection.insertOne(userInput);
    }
}