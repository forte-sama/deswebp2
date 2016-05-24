import org.h2.jdbc.JdbcStatement;

import java.sql.*;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        get("/", (req, res) -> {
            Connection conexion = DriverManager.getConnection("jdbc:h2:~/practica5", "sa", "");
            // add application code here

            StringBuilder s = new StringBuilder("");

            Statement stm = conexion.createStatement();

            stm.executeUpdate("INSERT INTO estudiantes VALUES(00000001,'missigno','missigno','missigno')");

            ResultSet rs = stm.executeQuery("SELECT * FROM estudiantes");

            if(!rs.isBeforeFirst()) s.append(" vacio");

            while(rs.next()) {
                s.append("<br/>" + rs.getString("nombre"));
            }

            conexion.close();

            return "Inicio CRUD Estudiantes " + s.toString();
        });
    }
}