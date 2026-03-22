package petcare.com.petcare.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import petcare.com.petcare.dto.VeterinarioDto;
import petcare.com.petcare.services.VeterinarioService;
import petcare.com.petcare.services.serviceImp.VeterinarioServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private VeterinarioService veterinarioService;

    @Override
    public void init() throws ServletException {
        try {
            this.veterinarioService = new VeterinarioServiceImpl();
        } catch (SQLException e) {
            throw new ServletException("Error al conectar con la base de datos.", e);
        }
    }

    // GET: mostrar formulario de login
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Si ya tiene sesión activa, redirigir al inicio
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("veterinarioLogueado") != null) {
            resp.sendRedirect(req.getContextPath() + "/inicio");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    // POST: procesar credenciales
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String tarjetaProfesional = req.getParameter("tarjetaProfesional");
        String contrasena = req.getParameter("contrasena");

        try {
            VeterinarioDto veterinario = veterinarioService.login(tarjetaProfesional, contrasena);

            // Guardar veterinario en sesión
            HttpSession session = req.getSession();
            session.setAttribute("veterinarioLogueado", veterinario);
            session.setMaxInactiveInterval(30 * 60); // sesión expira en 30 minutos

            resp.sendRedirect(req.getContextPath() + "/inicio");

        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos.", e);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
