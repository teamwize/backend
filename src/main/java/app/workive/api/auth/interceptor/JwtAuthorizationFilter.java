package app.workive.api.auth.interceptor;


import app.workive.api.auth.service.AuthenticationService;
import app.workive.api.auth.service.TokenService;
import app.workive.api.base.config.SecurityConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final AuthenticationService detailsService;
    private final TokenService tokenService;

    @Value("${app.auth.jwt.access-token.cookie-name}")
    private String accessTokenCookieName;
    @Value("${app.auth.jwt.header-name}")
    private String jwtHeaderName;
    @Value("${app.auth.api-key.header-name}")
    private String apiKeyHeaderName;


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return SecurityConfig.shouldBeIgnored(request.getServletPath()) || apiKeyHeaderExist(request);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            var jwt = getJwtToken(request, true);
            if (!StringUtils.hasText(jwt) || !tokenService.validateToken(jwt)) {
                return;
            }
            var subject = tokenService.getSubjectFromToken(jwt);
            var userDetails = detailsService.loadUserByUsername(subject);
            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private boolean apiKeyHeaderExist(HttpServletRequest request) {
        return StringUtils.hasText(request.getHeader(apiKeyHeaderName));
    }

    private String getJwtToken(HttpServletRequest request, boolean fromCookie) {
        if (fromCookie) {
            return getJwtFromCookie(request);
        }
        return tokenService.getJwtFromRequest(request.getHeader(jwtHeaderName));
    }

    private String getJwtFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null || request.getCookies().length == 0) {
            return null;
        }
        var cookies = request.getCookies();
        for (var cookie : cookies) {
            if (accessTokenCookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}