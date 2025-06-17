package com.exercise.restaurant.filter;

import com.exercise.restaurant.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class JwtAuthFilter implements ContainerRequestFilter {

    Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);
    @Autowired
    private JwtService jwtService;

    public JwtAuthFilter() {
        jwtService = new JwtService();
    }


//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//        String token = null;
//        String username = null;
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            token = authHeader.substring(7);
//            username = jwtService.extractUsername(token);
//        }
//
//        if (username != null && jwtService.validateToken(token, username)) {
//        }
//
//        filterChain.doFilter(request, response);
//    }

    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {
        String authHeader = ctx.getHeaderString("Authorization");
        logger.info("authHeader :: " + authHeader);

        String token = null;
        String username = null;
logger.info("getUriInfo" + ctx.getUriInfo().getPath());
if(ctx.getUriInfo().getPath().contains("signin")){
    return;
}
        logger.info("getBaseUri--" + ctx.getUriInfo().getBaseUri());
logger.info("authHeader" + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }
        logger.info("username" + username);
        if(authHeader!= null && jwtService.validateToken(token, username)){
            ;
        }else {
            ctx.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity("Cannot access")
                    .build());
        }
    }
}
