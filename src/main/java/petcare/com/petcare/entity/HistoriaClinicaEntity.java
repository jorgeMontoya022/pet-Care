package petcare.com.petcare.entity;

import java.sql.Date;

public class HistoriaClinicaEntity {

    private Long idHistorial;
    private Long idMascota;      // FK
    private String descripcion;  // Lo que se encontró en la revisión
    private String medicacion;   // Qué se le recetó
    private java.sql.Date fecha; // Fecha de la anotación


    public HistoriaClinicaEntity() {

    }

    public HistoriaClinicaEntity(Long idHistorial, Long idMascota, String descripcion, String medicacion, Date fecha) {
        this.idHistorial = idHistorial;
        this.idMascota = idMascota;
        this.descripcion = descripcion;
        this.medicacion = medicacion;
        this.fecha = fecha;
    }

    public Long getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Long idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Long getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Long idMascota) {
        this.idMascota = idMascota;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMedicacion() {
        return medicacion;
    }

    public void setMedicacion(String medicacion) {
        this.medicacion = medicacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
