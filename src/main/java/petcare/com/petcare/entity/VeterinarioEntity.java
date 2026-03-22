package petcare.com.petcare.entity;

public class VeterinarioEntity {

    private Long idVeterinario;
    private String nombre;
    private String especialidad; // General, Cirujano, etc.
    private String tarjetaProfesional;

    private String contrasena;


    public VeterinarioEntity() {

    }

    public VeterinarioEntity(Long idVeterinario, String nombre, String especialidad, String tarjetaProfesional) {
        this.idVeterinario = idVeterinario;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.tarjetaProfesional = tarjetaProfesional;
    }

    public Long getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(Long idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getTarjetaProfesional() {
        return tarjetaProfesional;
    }

    public void setTarjetaProfesional(String tarjetaProfesional) {
        this.tarjetaProfesional = tarjetaProfesional;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
