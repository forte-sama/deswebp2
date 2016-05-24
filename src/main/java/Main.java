import org.h2.jdbc.JdbcStatement;

import java.sql.*;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        get("/", (req, res) -> {
            Connection conexion = DriverManager.getConnection("jdbc:h2:~/practica5", "sa", "");
            // add application code here

            StringBuilder s = new StringBuilder(" vacio");

            Statement stm = conexion.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM estudiantes");

            while(rs.next()) {
                s.append(rs.getString("nombre"));
            }

            conexion.close();

            return "Inicio CRUD Estudiantes" + s.toString();
        });
    }
}