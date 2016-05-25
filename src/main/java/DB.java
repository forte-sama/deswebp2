import java.sql.*;

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

    public static void mostrarEstudiantesPorConsola() {
        try {
            ResultSet rs = DBManager().executeQuery("SELECT * FROM estudiantes");

            while(rs.next()) {
                System.out.println(rs.getString("matricula") + " : " + rs.getString("nombre"));
            }

        }
        catch (SQLException e) {
            System.out.println("Hubo algo raro con esa sentencia SQL...");
            System.out.println("O con la conexion a la base de datos...");
        }
    }
}
