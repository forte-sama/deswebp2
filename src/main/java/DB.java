import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by forte on 24/05/16.
 */
public class DB {
    private static Connection h2con;

    private DB() {}

    private static Statement DBManager() throws SQLException {
        if(h2con == null) {
            h2con = DriverManager.getConnection("jdbc:h2:~/practica5", "sa", "");
        }

        return h2con.createStatement();
    }

    public static Set<Estudiante> obtenerTodosLosEstudiantes() {
        ArrayList<Estudiante> resp = new ArrayList<>();

        try {
            ResultSet rs = DBManager().executeQuery("SELECT * FROM estudiantes");

            while(rs.next()) {
                resp.add(new Estudiante(rs.getInt("matricula"),
                                        rs.getString("nombre"),
                                        rs.getString("apellidos"),
                                        rs.getString("telefono")));
            }

            return new HashSet<>(resp);
        }
        catch (SQLException e) {
            System.out.println("Hubo algo raro con esa sentencia SQL...");
            System.out.println("O con la conexion a la base de datos...");
        }

        return new HashSet<>(resp);
    }
}
