import freemarker.template.Configuration;
import org.h2.jdbc.JdbcStatement;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {
    public static void main(String[] args) {

        //indicar ruta de archivos publicos.
        staticFileLocation("/public");
        //agregar pantalla de debug. Solo en desarrollo.
        enableDebugScreen();

        //TODO Buscar nueva forma de usar configuracion de freemarker
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        get("/", (req, res) -> {
            //bd stuff
            StringBuilder s = new StringBuilder("");

            Connection conexion = DriverManager.getConnection("jdbc:h2:~/practica5", "sa", "");
            Statement stm = conexion.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM estudiantes");

            if(!rs.isBeforeFirst()) s.append(" vacio");

            while(rs.next()) {
                s.append("<br/>" + rs.getString("nombre"));
            }

            //template stuff
            Map<String, String> data = new HashMap<>();
            data.put("texto_ejemplo",s.toString());

            return new ModelAndView(data,"sample.ftl");
        }, freeMarkerEngine);
    }
}