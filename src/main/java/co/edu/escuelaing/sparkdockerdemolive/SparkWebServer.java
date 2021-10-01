package co.edu.escuelaing.sparkdockerdemolive;

import co.edu.escuelaing.connectiondb.ConnectionDB;
import co.edu.escuelaing.json.ConnectionJson;
import static spark.Spark.*;
import com.google.gson.Gson;
/**
 * Clase principal de la conexon con los servicios de nube.
 * @author Miguel Angel Rodriguez Siachoque
 */
public class SparkWebServer 
{
    private static ConnectionDB dataBase = new ConnectionDB();
    private static final ConnectionJson dataJson = new ConnectionJson();
    /**
     * Metodo principal que conecta la parte logica con la pagina web.
     * @param args Argumentos de conexion.
     */
    public static void main(String[] args)
    {
        staticFiles.location("/public");
        port(getPort());
        get("/logService", (request,response) -> {response.type("application/json");
        dataJson.addDataJson(request.queryParams("cadena"));
        return new Gson().toJson(dataBase.getData());});
    }
    /**
     * Metodo encargado de generar el puerto propuesto para el funcionamiento.
     * @return Puerto del servidor.
     */
    private static int getPort() 
    {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }   
}