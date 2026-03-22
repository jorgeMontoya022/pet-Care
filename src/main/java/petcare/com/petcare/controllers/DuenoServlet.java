package petcare.com.petcare.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import petcare.com.petcare.dto.DuenoDto;
import petcare.com.petcare.services.DuenoService;
import petcare.com.petcare.services.serviceImp.DuenoServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/duenos")
public class DuenoServlet extends HttpServlet {

    private DuenoService duenoService;

    @Override
    public void init() throws ServletException {
        try {
            this.duenoService = new DuenoServiceImpl();
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
                req.getRequestDispatcher("/WEB-INF/views/duenos/formRegistrar.jsp")
                        .forward(req, resp);

            } else if ("editar".equals(accion)) {
                String cedula = req.getParameter("cedula");
                DuenoDto dueno = duenoService.buscarPorCedula(cedula);
                req.setAttribute("dueno", dueno);
                req.getRequestDispatcher("/WEB-INF/views/duenos/formEditar.jsp")
                        .forward(req, resp);

            } else if ("eliminar".equals(accion)) {
                String cedula = req.getParameter("cedula");
                duenoService.eliminar(cedula);
                resp.sendRedirect(req.getContextPath() + "/duenos");

            } else {
                List<DuenoDto> lista = duenoService.listarTodos();
                req.setAttribute("duenos", lista);
                req.getRequestDispatcher("/WEB-INF/views/duenos/listar.jsp")
                        .forward(req, resp);
            }

        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos.", e);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/duenos/listar.jsp")
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String accion = req.getParameter("accion");

        DuenoDto dto = new DuenoDto();
        dto.setCedula(req.getParameter("cedula"));
        dto.setNombre(req.getParameter("nombre"));
        dto.setTelefono(req.getParameter("telefono"));
        dto.setCorreo(req.getParameter("correo"));

        try {
            if ("actualizar".equals(accion)) {
                duenoService.actualizar(dto);
            } else {
                duenoService.registrar(dto);
            }
            resp.sendRedirect(req.getContextPath() + "/duenos");

        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos.", e);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("dueno", dto);
            String vista = "actualizar".equals(accion)
                    ? "/WEB-INF/views/duenos/formEditar.jsp"
                    : "/WEB-INF/views/duenos/formRegistrar.jsp";
            req.getRequestDispatcher(vista).forward(req, resp);
        }
    }
}