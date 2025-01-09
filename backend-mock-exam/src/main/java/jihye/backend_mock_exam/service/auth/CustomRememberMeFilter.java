package jihye.backend_mock_exam.service.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

import java.io.IOException;

public class CustomRememberMeFilter extends RememberMeAuthenticationFilter {

    public CustomRememberMeFilter(AuthenticationManager authenticationManager, RememberMeServices rememberMeServices) {
        super(authenticationManager, rememberMeServices);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String rememberMe = httpRequest.getParameter("rememberMe");

        if ("on".equals(rememberMe)) {
            httpRequest.setAttribute("springSecurityRememberMe", "true");
        }

        super.doFilter(request, response, chain);
    }
}
