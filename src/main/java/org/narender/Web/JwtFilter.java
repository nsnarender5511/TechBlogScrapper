package org.narender.Web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mysql.cj.exceptions.ClosedOnExpiredPasswordException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;

@WebFilter(urlPatterns = "/none")
public class JwtFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class.getName());
    private static final String SECRET = "mySecretKey";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        logger.info("going to verify token");
        String token = ((HttpServletRequest) request).getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            //chain.doFilter(request, response)
            logger.error("null token");
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);

            return;
        }
        String jwtToken = token.substring(7);

        if(verifyJwtToken(jwtToken)) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_OK);
        } else {
            logger.info("Invalid token");
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        //String username = JwtUtil.extractUsername(jwtToken);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean verifyJwtToken(String token) {
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC512(SECRET))
                    .build()
                    .verify(token);
            Date expiryDate = jwt.getExpiresAt();
            Date currentDate = new Date();

            return expiryDate.after(currentDate);
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
