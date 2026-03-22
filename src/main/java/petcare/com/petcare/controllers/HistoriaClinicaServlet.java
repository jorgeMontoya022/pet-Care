package petcare.com.petcare.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import petcare.com.petcare.dto.HistoriaClinicaDto;
import petcare.com.petcare.services.HistoriaClinicaService;
import petcare.com.petcare.services.serviceImp.HistoriaClinicaServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/historias")
public class HistoriaClinicaServlet extends HttpServlet {

    private HistoriaClinicaService historiaClinicaService;

    @Override
    public void init() throws ServletException {
        try {
            this.historiaClinicaService = new HistoriaClinicaServiceImpl();
        } catch (SQLException e) {
            throw new ServletException("Error al conectar con la base de datos.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String accion = req.getParameter("accion");

        try {
            if ("nueva".equals(accion)) {
                // Mostrar formulario para agregar entrada al historial
                String idMascota = req.getParameter("idMascota");
                req.setAttribute("idMascota", idMascota);
                req.getRequestDispatcher("/WEB-INF/views/historias/formRegistrar.jsp")
                        .forward(req, resp);

            } else if ("eliminar".equals(accion)) {
                Long id = Long.parseLong(req.getParameter("id"));
                historiaClinicaService.eliminar(id);
                // Regresar al historial de la misma mascota
                String idMascota = req.getParameter("idMascota");
                resp.sendRedirect(req.getContextPath() + "/historias?idMascota=" + idMascota);

            } else {
                // Listar historial de una mascota específica
                Long idMascota = Long.parseLong(req.getParameter("idMascota"));
                List<HistoriaClinicaDto> lista = historiaClinicaService.buscarPorMascota(idMascota);
                req.setAttribute("historias", lista);
                req.setAttribute("idMascota", idMascota);
                req.getRequestDispatcher("/WEB-INF/views/historias/listar.jsp")
                        .forward(req, resp);
            }

        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos.", e);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/historias/listar.jsp")
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long idMascota = Long.parseLong(req.getParameter("idMascota"));

        HistoriaClinicaDto dto = new HistoriaClinicaDto();
        dto.setDescripcion(req.getParameter("descripcion"));
        dto.setMedicacion(req.getParameter("medicacion"));

        try {
            historiaClinicaService.registrar(dto, idMascota);
            resp.sendRedirect(req.getContextPath() + "/historias?idMascota=" + idMascota);

        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos.", e);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("idMascota", idMascota);
            req.getRequestDispatcher("/WEB-INF/views/historias/formRegistrar.jsp")
                    .forward(req, resp);
        }
    }
}
