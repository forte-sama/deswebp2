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

            int int_mat = -1;
            String matricula = request.params("matricula");

            boolean numeroMatriculaValido = true;

            try {
                int_mat = Integer.parseInt(matricula);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                numeroMatriculaValido = false;
            }

            boolean matriculaValida = true;

            if(numeroMatriculaValido) {
                Estudiante est = DB.obtenerEstudiante(int_mat);

                if(est != null) {
                    data.put("matricula","" + est.getMatricula());
                    data.put("nombres",est.getNombres());
                    data.put("apellidos",est.getApellidos());
                    data.put("telefono",est.getTelefono());
                }
                else {
                    matriculaValida = false;
                }
            }
            else {
                matriculaValida = false;
            }

            if (!matriculaValida) {
                data.put("action","error");
                data.put("msg_type","error");
                data.put("msg","No hay un estudiante con la matricula: " + int_mat);
            }

            return new ModelAndView(data,"edit_create.ftl");
        }, freeMarker);

        post("/edit/", (request, response) -> {
            HashMap<String,Object> data = new HashMap<>();
            data.put("action","edit");

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
                Estudiante est = DB.obtenerEstudiante(int_mat);

                if(est == null) {
                    matriculaValida = false;
                }
            } catch (NumberFormatException e) {
                matriculaValida = false;
            }

            if(datosValidos && matriculaValida) {
                if(DB.actualizarEstudiante(int_mat,nombres,apellidos,telefono)) {
                    response.redirect("/home");
                }
                else {
                    exito = false;
                }
            }
            else {
                exito = false;
            }

            if(!exito) {
                data.put("matricula",matricula);
                data.put("nombres",nombres);
                data.put("apellidos",apellidos);
                data.put("telefono",matricula);

                data.put("msg","Intento fallido, hubo algun error.");
                data.put("msg_type","error");
            }

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

        get("/delete/:matricula",(request, response) -> {
            HashMap<String,Object> data = new HashMap<>();

            String matricula = request.params("matricula");

            int int_mat = -1;

            boolean exito = true;

            try {
                int_mat = Integer.parseInt(matricula);

                if(DB.borrarEstudiante(int_mat)) {
                    data.put("msg_type","success");
                    data.put("msg", "Estudiante borrado con exito.");
                }
                else{
                    exito = false;
                }
            } catch(NumberFormatException e) {
                exito = false;
            }

            if(!exito) {
                data.put("msg_type","error");
                data.put("msg","Hubo un error borrando al estudiante con matricula " + matricula + "... Gracias a Dios.");
            }

            return new ModelAndView(data,"delete.ftl");
        }, freeMarker);
    }

    private static boolean validarDatos(String nombres, String apellidos, String telefono) {
        boolean nombresValidos = !nombres.isEmpty() && nombres.length() <= 100;
        boolean apellidosValidos = !apellidos.isEmpty() && apellidos.length() <= 100;
        boolean telefonoValido = !telefono.isEmpty() && telefono.length() <= 50;

        return nombresValidos && apellidosValidos && telefonoValido;
    }
}