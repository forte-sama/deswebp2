/**
 * Created by forte on 24/05/16.
 */
public class Estudiante {
    private int matricula;
    private String nombres;
    private String apellidos;
    private String telefono;

    private Estudiante() { }

    public Estudiante(int matricula, String nombres, String apellidos, String telefono) {
        this.matricula = matricula;
        this.telefono = telefono;
        this.apellidos = apellidos;
        this.nombres = nombres;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
