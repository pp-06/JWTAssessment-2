package com.MongoDBAssessment.MongoDBAssessment.configuration;

import com.MongoDBAssessment.MongoDBAssessment.service.TokenService;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class JwtFilter extends GenericFilterBean {

    private final TokenService tokenService;

    JwtFilter(TokenService tokenService)
    {
        this.tokenService= tokenService;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;



        // Get the token from the Authorization header
        String token = httpServletRequest.getHeader("Authorization");
        // Handle preflight requests (OPTIONS)
        if ("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod()))
        {
            httpServletResponse.sendError(HttpServletResponse.SC_OK, "Success");
            return;
        }
        // Check if the request is allowed without a token
        if (allowReqWithoutToken(httpServletRequest))
        {
            // Allow the request to proceed
            httpServletResponse.setStatus(HttpServletResponse.SC_OK, "Success");
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
        else
        {
            // Extract the user ID from the token and set it as a request attribute
            ObjectId userId = new ObjectId(tokenService.getUserIdToken(token));
            httpServletRequest.setAttribute("userId",userId);
            // Allow the request to proceed
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
    // Check if the request is allowed without a token
    public boolean allowReqWithoutToken(HttpServletRequest httpServletRequest){
        if (httpServletRequest.getRequestURI().contains("admin"))
        {
            return  true;
        }
        return  false;
    }

}
