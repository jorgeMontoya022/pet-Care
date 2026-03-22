package petcare.com.petcare.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import petcare.com.petcare.dto.MascotaDto;
import petcare.com.petcare.services.MascotaService;
import petcare.com.petcare.services.serviceImp.MascotaServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/mascotas")
public class MascotaServlet extends HttpServlet {

    private MascotaService mascotaService;

    @Override
    public void init() throws ServletException {
        try {
            this.mascotaService = new MascotaServiceImpl();
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
                req.getRequestDispatcher("/WEB-INF/views/mascotas/formRegistrar.jsp")
                        .forward(req, resp);

            } else if ("editar".equals(accion)) {
                Long id = Long.parseLong(req.getParameter("id"));
                MascotaDto mascota = mascotaService.buscarPorId(id);
                req.setAttribute("mascota", mascota);
                req.getRequestDispatcher("/WEB-INF/views/mascotas/formEditar.jsp")
                        .forward(req, resp);

            } else if ("eliminar".equals(accion)) {
                Long id = Long.parseLong(req.getParameter("id"));
                mascotaService.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/mascotas");

            } else if ("buscarPorDueno".equals(accion)) {
                String cedula = req.getParameter("cedula");
                List<MascotaDto> lista = mascotaService.buscarPorCedulaDueno(cedula);
                req.setAttribute("mascotas", lista);
                req.setAttribute("cedulaBuscada", cedula);
                req.getRequestDispatcher("/WEB-INF/views/mascotas/listar.jsp")
                        .forward(req, resp);

            } else {
                List<MascotaDto> lista = mascotaService.listarTodas();
                req.setAttribute("mascotas", lista);
                req.getRequestDispatcher("/WEB-INF/views/mascotas/listar.jsp")
                        .forward(req, resp);
            }

        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos.", e);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/mascotas/listar.jsp")
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String accion = req.getParameter("accion");

        MascotaDto dto = new MascotaDto();
        dto.setNombre(req.getParameter("nombre"));
        dto.setEspecie(req.getParameter("especie"));
        dto.setRaza(req.getParameter("raza"));
        dto.setCedulaDueno(req.getParameter("cedulaDueno"));

        String edadStr = req.getParameter("edad");
        if (edadStr != null && !edadStr.isBlank()) {
            dto.setEdad(Integer.parseInt(edadStr));
        }

        try {
            if ("actualizar".equals(accion)) {
                dto.setIdMascota(Long.parseLong(req.getParameter("idMascota")));
                mascotaService.actualizar(dto);
            } else {
                mascotaService.registrar(dto);
            }
            resp.sendRedirect(req.getContextPath() + "/mascotas");

        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos.", e);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("mascota", dto);
            String vista = "actualizar".equals(accion)
                    ? "/WEB-INF/views/mascotas/formEditar.jsp"
                    : "/WEB-INF/views/mascotas/formRegistrar.jsp";
            req.getRequestDispatcher(vista).forward(req, resp);
        }
    }
}