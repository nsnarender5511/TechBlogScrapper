package org.narender.Web;

import com.mysql.cj.exceptions.ClosedOnExpiredPasswordException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebFilter(urlPatterns = "/fetchBlog")
public class JwtFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class.getName());
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
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);

            return;
        }
        String jwtToken = token.substring(7);
        //String username = JwtUtil.extractUsername(jwtToken);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
