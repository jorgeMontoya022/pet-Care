package petcare.com.petcare.entity;

/**
 * Representa la tabla MASCOTAS
 */
public class MascotaEntity {
    private Long idMascota;    // NUMBER (Primary Key)
    private String nombre;     // VARCHAR2
    private String especie;    // VARCHAR2 (Perro, Gato, etc.)
    private String raza;       // VARCHAR2
    private Integer edad;      // NUMBER
    private Long idDueno;      // NUMBER (Foreign Key hacia DueñoEntity)

    public MascotaEntity() {
    }

    public MascotaEntity(Long idMascota, String nombre, String especie, String raza, Integer edad, Long idDueno) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.idDueno = idDueno;
    }

    // Getters y Setters
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

    public Long getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(Long idDueno) {
        this.idDueno = idDueno;
    }
}