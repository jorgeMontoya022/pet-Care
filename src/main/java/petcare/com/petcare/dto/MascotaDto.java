package petcare.com.petcare.dto;

public class MascotaDto {
    private Long idMascota;      // Para saber a quién editar
    private String nombre;
    private String especie;
    private String raza;
    private Integer edad;
    private String cedulaDueno;  // Para buscar al dueño al registrar
    private String nombreDueno;  // Para mostrar "Firulais - Dueño: Juan Perez"
    private String telefonoDueno;
    private String correoDueno;

    public MascotaDto() {

    }

    public Long getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Long idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCedulaDueno() {
        return cedulaDueno;
    }

    public void setCedulaDueno(String cedulaDueno) {
        this.cedulaDueno = cedulaDueno;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public String getTelefonoDueno() { return telefonoDueno; }
    public void setTelefonoDueno(String telefonoDueno) { this.telefonoDueno = telefonoDueno; }

    public String getCorreoDueno() { return correoDueno; }
    public void setCorreoDueno(String correoDueno) { this.correoDueno = correoDueno; }
}
