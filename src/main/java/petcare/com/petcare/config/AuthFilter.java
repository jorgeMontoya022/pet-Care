package petcare.com.petcare.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();

        boolean esPublica = uri.equals(contextPath + "/login")
                            || uri.equals(contextPath + "/logout")
                            || uri.equals(contextPath + "/")
                            || uri.equals(contextPath + "/index.jsp")
                            || uri.startsWith(contextPath + "/resources/")
                            || uri.startsWith(contextPath + "/css/")
                            || uri.startsWith(contextPath + "/js/");

        if (esPublica) {
            chain.doFilter(request, response); //dejar pasar
            return;
        }

        //Verificar sesión activa
        HttpSession session = req.getSession(false);
        boolean logueado = session != null
                           && session.getAttribute("veterinarioLogueado") != null;

        if (logueado) {
            chain.doFilter(request, response); //tiene sesión, dejar pasar
        } else {
            resp.sendRedirect(contextPath + "/login"); //sin sesión al login
        }


    }
}
