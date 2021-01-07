package org.mavenworkforce.configs.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONObject;
import org.mavenworkforce.pojos.responses.error.ErrorData;
import org.mavenworkforce.pojos.responses.error.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

//        ErrorResponse errorResponse = new ErrorResponse(new ErrorData("Authentication Failed. Please check token or get a new token.", "AUTHENTICATION_FAILURE"), Instant.now());
//        String payload = new ObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writeValueAsString(errorResponse);
        JSONObject errorObject = new JSONObject();
        errorObject.put("errorMessage", "Authentication Failed. Please check token or get a new token.");
        errorObject.put("errorCode", "AUTHENTICATION_FAILURE");
        JSONObject errorResponseObject = new JSONObject();
        errorResponseObject.put("response", "ERROR");
        errorResponseObject.put("error", errorObject);
        errorResponseObject.put("timestamp", Instant.now());

        String payload = errorResponseObject.toString();
        response.getWriter().write(payload);
    }
}
