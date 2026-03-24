package petcare.com.petcare.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import petcare.com.petcare.dto.CitaDto;
import petcare.com.petcare.dto.MascotaDto;
import petcare.com.petcare.dto.VeterinarioDto;
import petcare.com.petcare.services.CitaService;
import petcare.com.petcare.services.MascotaService;
import petcare.com.petcare.services.VeterinarioService;
import petcare.com.petcare.services.serviceImp.CitaServiceImpl;
import petcare.com.petcare.services.serviceImp.MascotaServiceImpl;
import petcare.com.petcare.services.serviceImp.VeterinarioServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/citas")
public class CitaServlet extends HttpServlet {
    private CitaService citaService;
    private MascotaService mascotaService;
    private VeterinarioService veterinarioService;

    @Override
    public void init() throws ServletException {
        try {
            this.citaService = new CitaServiceImpl();
            this.mascotaService = new MascotaServiceImpl();
            this.veterinarioService = new VeterinarioServiceImpl();
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
                // Cargar listas para los selectores del formulario
                List<MascotaDto> mascotas = mascotaService.listarTodas();
                List<VeterinarioDto> veterinarios = veterinarioService.listarTodos();
                req.setAttribute("mascotas", mascotas);
                req.setAttribute("veterinarios", veterinarios);
                req.getRequestDispatcher("/WEB-INF/views/citas/formRegistrar.jsp")
                        .forward(req, resp);

            } else if ("cambiarEstado".equals(accion)) {
                Long idCita = Long.parseLong(req.getParameter("id"));
                String estado = req.getParameter("estado");
                citaService.cambiarEstado(idCita, estado);
                resp.sendRedirect(req.getContextPath() + "/citas");

            } else if ("eliminar".equals(accion)) {
                Long id = Long.parseLong(req.getParameter("id"));
                citaService.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/citas");

            } else {
                List<CitaDto> lista = citaService.listarTodas();
                req.setAttribute("citas", lista);
                req.getRequestDispatcher("/WEB-INF/views/citas/listar.jsp")
                        .forward(req, resp);
            }

        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos.", e);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/citas/listar.jsp")
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        CitaDto dto = new CitaDto();
        dto.setIdCita(Long.parseLong(req.getParameter("idMascota")));
        dto.setIdVeterinario(Long.parseLong(req.getParameter("idVeterinario")));
        dto.setMotivo(req.getParameter("motivo"));
        dto.setEstado("PROGRAMADA");

        // Convertir fecha y hora del formulario a LocalDateTime
        String fechaHora = req.getParameter("fechaHora"); // formato: "2024-12-25T10:30"
        dto.setFechaHora(LocalDateTime.parse(fechaHora, DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        try {
            citaService.registrar(dto);
            resp.sendRedirect(req.getContextPath() + "/citas");

        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos.", e);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/citas/formRegistrar.jsp")
                    .forward(req, resp);
        }
    }
}
