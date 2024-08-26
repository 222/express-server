package com.express.server.filter;

import com.express.server.common.ErrorCodes;
import com.express.server.common.LoginSession;
import com.express.server.properties.TokenProperties;
import com.express.server.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author youping.tan
 * @date 2024/7/30 10:53
 */
@Component
public class LoginSessionFilter extends AbstractCommonFilter {

    @Resource
    private TokenProperties tokenProperties;

    @Override
    public boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String uri = request.getRequestURI();
        return uri.startsWith("/open/");
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = request.getHeader("X-API-TOKEN");
            boolean result = JwtUtils.verifyToken(token, tokenProperties.getSecret());
            if (result) {
                LoginSession loginSession = JwtUtils.parseToken(token, tokenProperties.getSecret());
                RequestContext.add(loginSession);
                filterChain.doFilter(request, response);
            } else {
                resp(response, ErrorCodes.LOGIN_EXPIRED);
            }
        } finally {
            RequestContext.remove();
        }
    }
}
