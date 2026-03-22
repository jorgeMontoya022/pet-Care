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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        try {
            if ("editar".equals(accion)) {
                String cedula = request.getParameter("cedula");
                DuenoDto dueno = duenoService.buscarPorCedula(cedula);
                request.setAttribute("dueno", dueno);
                request.getRequestDispatcher("/WEB-INF/views/duenos/formEditar.jsp")
                        .forward(request, response);
            } else if ("eliminar".equals(accion)) {
                String cedula = request.getParameter("cedula");
                duenoService.eliminar(cedula);
                response.sendRedirect(request.getContextPath() + "/duenos");

            } else {
                List<DuenoDto> lista = duenoService.listarTodos();
                request.setAttribute("duenos", lista);
                request.getRequestDispatcher("/WEB-INF/views/duenos/listar.jsp")
                        .forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos.", e);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/duenos/listar.jsp")
                    .forward(request, response);
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
