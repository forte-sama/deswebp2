import freemarker.template.Configuration;
import org.h2.jdbc.JdbcStatement;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.*;
import java.util.Arrays;
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

        //freemarker template engine
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarker = new FreeMarkerEngine();
        freeMarker.setConfiguration(configuration);

        //Rutas
        get("/", (request, response) -> {
            response.redirect("/home");
            return "";
        });

        get("/home", (request, response) -> {
            HashMap<String,Object> data = new HashMap<>();

            data.put("estudiantes",DB.obtenerTodosLosEstudiantes());

            return new ModelAndView(data,"home.ftl");
        }, freeMarker);

        get("/edit/:matricula", (request, response) -> {
            HashMap<String,Object> data = new HashMap<>();
            return new ModelAndView(data,"edit_create.ftl");
        }, freeMarker);

        get("/new", (request, response) -> {
            HashMap<String,Object> data = new HashMap<>();
            return new ModelAndView(data,"edit_create.ftl");
        }, freeMarker);

        get("/view/:matricula", (request, response) -> {
            HashMap<String,Object> data = new HashMap<>();

            String rawMatricula = request.params("matricula");

            try {
                int mat = Integer.parseInt(rawMatricula);

                Estudiante est = DB.obtenerEstudiante(mat);

                if(est != null) {
                    data.put("estudiante",est);
                }
                else {
                    data.put("error_msg","No hay un estudiante con esa matricula");
                }
            }
            catch (NumberFormatException e) {
                //redireccionar a home con msg de error
                response.redirect("/home");

                return new ModelAndView(new HashMap<>(),"error.ftl");
            }

            return new ModelAndView(data,"view.ftl");
        }, freeMarker);

        get("/delete/:matricula",(request, response) -> "Aqui se deberia borrar un estudiante");

        //Rutas de ejemplo
        get("/sample2",(request, response) -> {
            //template stuff
            Map<String, Object> data = new HashMap<>();

            data.put("estudiantes",DB.obtenerTodosLosEstudiantes());

            return new ModelAndView(data,"sample.ftl");
        }, freeMarker);

        /**
        get("/sample", (req, res) -> {
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
        }, freeMarker);
        */
    }
}