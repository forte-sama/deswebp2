import freemarker.template.Configuration;
import org.jetbrains.annotations.Contract;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

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

            data.put("msg",request.headers("msg"));

            data.put("estudiantes",DB.obtenerTodosLosEstudiantes());

            return new ModelAndView(data,"home.ftl");
        }, freeMarker);

        get("/edit/:matricula", (request, response) -> {
            HashMap<String,Object> data = new HashMap<>();

            data.put("action","edit");

            return new ModelAndView(data,"edit_create.ftl");
        }, freeMarker);

        get("/new", (request, response) -> {
            HashMap<String,Object> data = new HashMap<>();

            data.put("action","new");

            return new ModelAndView(data,"edit_create.ftl");
        }, freeMarker);

        post("/new",(request, response) -> {
            HashMap<String,Object> data = new HashMap<>();
            data.put("action","new");

            String matricula = request.queryParams("matricula");
            String nombres   = request.queryParams("nombres");
            String apellidos = request.queryParams("apellidos");
            String telefono  = request.queryParams("telefono");

            boolean exito           = true;
            boolean datosValidos    = validarDatos(nombres,apellidos,telefono);
            boolean matriculaValida = true;

            int int_mat = -1;

            try {
                int_mat = Integer.parseInt(matricula);
                matriculaValida = DB.nuevaMatriculaValida(int_mat);
            } catch (NumberFormatException e) {
                matriculaValida = false;
            }

            if(datosValidos && matriculaValida) {
                if(DB.crearEstudiante(int_mat,nombres,apellidos,telefono)) {
                    data.put("msg", "Estudiante creado con exito.");
                    data.put("msg_type", "success");
                }
                else {
                    exito = false;
                }
            }
            else {
                exito = false;
            }

            if(!exito) {
                data.put("msg","Intento fallido, hubo algun error.");
                data.put("msg_type","error");
            }

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
                    data.put("error_msg","No hay un estudiante con matricula: " + mat);
                }
            } catch (NumberFormatException e) {
                data.put("error_msg","No hay un estudiante con matricula: " + rawMatricula);
            }

            return new ModelAndView(data,"view.ftl");
        }, freeMarker);

        get("/delete/:matricula",(request, response) -> "Aqui se deberia borrar un estudiante");
    }

    private static boolean validarDatos(String nombres, String apellidos, String telefono) {
        boolean nombresValidos = !nombres.isEmpty() && nombres.length() <= 100;
        boolean apellidosValidos = !apellidos.isEmpty() && apellidos.length() <= 100;
        boolean telefonoValido = !telefono.isEmpty() && telefono.length() <= 50;

        return nombresValidos && apellidosValidos && telefonoValido;
    }
}