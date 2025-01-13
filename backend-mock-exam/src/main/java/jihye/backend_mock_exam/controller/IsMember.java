package jihye.backend_mock_exam.controller;

import jihye.backend_mock_exam.controller.auth.RoleConst;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class IsMember {

    public boolean isMember() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (RoleConst.ROLE_USER.equals(authority.getAuthority())) {
                return true;
            }
        }

        return false;
    }

}
