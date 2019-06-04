package pl.bykowski.springbootjwtb1;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwetFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer "))
            throw new ServletException("header not exist");
        String token = header.substring(7);


        Algorithm algorithm = Algorithm.HMAC512("Jan123");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        if (jwt.getClaim("roles").asString().equals("user")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else
        {
            throw new ServletException("Forbidden!!!");
        }
    }
}
