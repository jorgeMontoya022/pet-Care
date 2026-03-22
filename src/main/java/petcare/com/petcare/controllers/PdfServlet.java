package petcare.com.petcare.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import petcare.com.petcare.dto.HistoriaClinicaDto;
import petcare.com.petcare.dto.MascotaDto;
import petcare.com.petcare.services.HistoriaClinicaService;
import petcare.com.petcare.services.PdfService;
import petcare.com.petcare.services.serviceImp.HistoriaClinicaServiceImpl;
import petcare.com.petcare.services.serviceImp.MascotaServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/pdf/historia")
public class PdfServlet extends HttpServlet {

    private HistoriaClinicaService historiaService;
    private MascotaServiceImpl mascotaService;
    private PdfService pdfService;

    @Override
    public void init() throws ServletException {
        try {
            this.historiaService = new HistoriaClinicaServiceImpl();
            this.mascotaService  = new MascotaServiceImpl();
            this.pdfService      = new PdfService();
        } catch (SQLException e) {
            throw new ServletException("Error al conectar con la base de datos.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long idMascota = Long.parseLong(req.getParameter("idMascota"));

        try {
            MascotaDto mascota = mascotaService.buscarPorId(idMascota);
            List<HistoriaClinicaDto> historias = historiaService.buscarPorMascota(idMascota);

            // Decirle al navegador que la respuesta es un PDF descargable
            resp.setContentType("application/pdf");
            resp.setHeader("Content-Disposition",
                    "attachment; filename=\"historia_" + mascota.getNombre() + ".pdf\"");

            pdfService.generarHistoriaClinica(mascota, historias, resp.getOutputStream());

        } catch (SQLException e) {
            throw new ServletException("Error al generar el PDF.", e);
        } catch (Exception e) {
            throw new ServletException("Error inesperado.", e);
        }
    }
}