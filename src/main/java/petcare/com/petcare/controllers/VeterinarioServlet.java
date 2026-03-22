package petcare.com.petcare.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import petcare.com.petcare.dto.VeterinarioDto;
import petcare.com.petcare.services.VeterinarioService;
import petcare.com.petcare.services.serviceImp.VeterinarioServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/veterinarios")
public class VeterinarioServlet extends HttpServlet {

    private VeterinarioService veterinarioService;

    @Override
    public void init() throws ServletException {
        try {
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
            if ("nuevo".equals(accion)) {
                req.getRequestDispatcher("/WEB-INF/views/veterinarios/formRegistrar.jsp")
                        .forward(req, resp);

            } else if ("editar".equals(accion)) {
                Long id = Long.parseLong(req.getParameter("id"));
                VeterinarioDto vet = veterinarioService.buscarPorId(id);
                req.setAttribute("veterinario", vet);
                req.getRequestDispatcher("/WEB-INF/views/veterinarios/formEditar.jsp")
                        .forward(req, resp);

            } else if ("eliminar".equals(accion)) {
                Long id = Long.parseLong(req.getParameter("id"));
                veterinarioService.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/veterinarios");

            } else {
                List<VeterinarioDto> lista = veterinarioService.listarTodos();
                req.setAttribute("veterinarios", lista);
                req.getRequestDispatcher("/WEB-INF/views/veterinarios/listar.jsp")
                        .forward(req, resp);
            }

        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos.", e);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/veterinarios/listar.jsp")
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String accion = req.getParameter("accion");

        VeterinarioDto dto = new VeterinarioDto();
        dto.setNombre(req.getParameter("nombre"));
        dto.setEspecialidad(req.getParameter("especialidad"));
        dto.setTarjetaProfesional(req.getParameter("tarjetaProfesional"));

        try {
            if ("actualizar".equals(accion)) {
                dto.setIdVeterinario(Long.parseLong(req.getParameter("idVeterinario")));
                veterinarioService.actualizar(dto);
            } else {
                veterinarioService.registrar(dto);
            }
            resp.sendRedirect(req.getContextPath() + "/veterinarios");

        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos.", e);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("veterinario", dto);
            String vista = "actualizar".equals(accion)
                    ? "/WEB-INF/views/veterinarios/formEditar.jsp"
                    : "/WEB-INF/views/veterinarios/formRegistrar.jsp";
            req.getRequestDispatcher(vista).forward(req, resp);
        }
    }
}