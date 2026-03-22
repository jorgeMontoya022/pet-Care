package petcare.com.petcare.entity;

/**
 * Representa la tabla DUEÑOS en Oracle.
 */
public class DuenoEntity {
    private Long idDueno;// NUMBER en Oracle (Primary Key)
    private String cedula;
    private String nombre;     // VARCHAR2
    private String telefono;   // VARCHAR2
    private String correo;     // VARCHAR2

    // Constructor vacío (Requerido)
    public DuenoEntity() {
    }

    // Constructor con campos (Útil para crear objetos rápido)
    public DuenoEntity(Long idDueno, String nombre, String telefono, String correo, String cedula) {
        this.idDueno = idDueno;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.cedula = cedula;
    }

    // Getters y Setters
    public Long getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(Long idDueno) {
        this.idDueno = idDueno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}