package com.bogdanjmk.railwaybookingsystem.utils;

import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.bogdanjmk.railwaybookingsystem.exception.ApiException;
import com.bogdanjmk.railwaybookingsystem.model.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;

import java.io.OutputStream;

import static java.time.LocalTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class ExceptionsUtils {
    public static void processError(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        if (response.isCommitted()) return;
        HttpResponse httpResponse;
        if (exception instanceof ApiException || exception instanceof DisabledException || exception instanceof LockedException || exception instanceof BadCredentialsException || exception instanceof InvalidClaimException || exception instanceof TokenExpiredException) {
            httpResponse = getHttpResponse(response, exception.getMessage(), BAD_REQUEST);
        } else {
            httpResponse = getHttpResponse(response, "An error has occurred. Please try again.", INTERNAL_SERVER_ERROR);
        }
        writeResponse(response, httpResponse);
        log.info(request.toString());
        log.error(exception.getMessage());
    }

    private static void writeResponse(HttpServletResponse response, HttpResponse httpResponse) {
        if (response.isCommitted()) return;

        OutputStream outputStream;
        try {
            outputStream = response.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(outputStream, httpResponse);
            outputStream.flush();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static HttpResponse getHttpResponse(HttpServletResponse response, String message, HttpStatus httpStatus) {
        HttpResponse httpResponse = HttpResponse.builder()
                .timeStamp(now().toString())
                .reason(message)
                .httpStatus(httpStatus)
                .statusCode(httpStatus.value())
                .build();

        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(httpStatus.value());

        return httpResponse;
    }
}
