package jihye.backend_mock_exam.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jihye.backend_mock_exam.domain.user.Guest;
import jihye.backend_mock_exam.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class UserInfoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HttpSession session = request.getSession(false);
        
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            request.setAttribute("user", user);
        }

        if (session != null && session.getAttribute("guest") != null) {
            Guest guest = (Guest) session.getAttribute("guest");
            request.setAttribute("user", guest);
        }

        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/exam") || requestURI.startsWith("/memo")) {
            if (request.getAttribute("user") == null) {
                response.sendRedirect("/auth/sign-in");
                return false;
            }
        }

        return true;
    }
}

