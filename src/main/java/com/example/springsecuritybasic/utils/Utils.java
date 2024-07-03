package com.example.springsecuritybasic.utils;

import com.example.springsecuritybasic.exception.utils.UtilsJsonToObjectException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Method;

@Service
public class Utils {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Utils.class);

    public static <T> T jsonToObject(String jsonString, Class<T> classType) throws UtilsJsonToObjectException {

        T o = null;
        try {
            o = new ObjectMapper().readValue(jsonString, classType);
        } catch (IOException e) {
            //JsonParseException, JsonMappingException, estas duas sao io tb.
            logger.info("E, sT: " + e, e);
            throw new UtilsJsonToObjectException("Json to Object", e);
        }

        return o;
    }

    public static String toJsonString_(Object o) {

        String json = "";
        try {
            json = new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            //n fazer throw nesta_ pa codigo dos controllers ficar em condiçoes.
            logger.info("E, sT: " + e, e);
            logger.info("Exception parsing json to PrintOutput: " + e.toString());
        }

        return json;
    }

    /**
     * Logs the details of a REST API HTTP response (reference:
     * <a href="https://bitbucket.digitalsign.pt/projects/DSPT0/repos/dspt0/browse/style/Java/DSPT0%20Java%20Spring%20Boot%20Coding%20Style%20Guide.md">DSPT0 Java Spring Boot Coding Style Guide.</a>).
     * This method checks if the body of the response entity is not null and attempts to log the response
     * with a body using either a custom 'toStringHash' method if present, or falls back to JSON serialization.
     * If the response entity has headers, these are included in the log. If the body is null, it logs the
     * response without body information.
     * <p>
     * The 'toStringHash' method is reflected upon and used for logging the body if available. Otherwise,
     * a standard JSON serialization is used for logging the body. If the response contains headers, they are
     * logged along with the body. If the body is null, only the response status is logged.
     *
     * @param logger         The Logger instance used for logging the response details.
     * @param responseEntity The ResponseEntity object containing the response information.
     * @param <T>            The type of the body within the ResponseEntity.
     */
    public <T> void logResponse(Logger logger, ResponseEntity<T> responseEntity) {
        if (responseEntity.getBody() != null) {
            String bodyAsString;
            try {
                // Try to find the toStringHash method
                Method toStringHashMethod = responseEntity.getBody().getClass().getMethod("toStringHash");
                // If method exists and is accessible, use it to convert the body
                bodyAsString = (String) toStringHashMethod.invoke(responseEntity.getBody());
            } catch (Exception e) {
                // If there's any issue (method not found, invocation issue), fall back to JSON serialization
                bodyAsString = toJsonString_(responseEntity.getBody());
            }

            if (!responseEntity.getHeaders().isEmpty()) {
                // logResponseWithHeadersAndBody - output serialized resource json instead of toString
                logger.info("{} - {} - {}", responseEntity.getStatusCode(), responseEntity.getHeaders(), bodyAsString);
            } else {
                // logResponseWithBody
                logger.info("{} - {}", responseEntity.getStatusCode(), bodyAsString);
            }
        } else {
            // logResponseWithoutOutputInfo
            logger.info("{}", responseEntity);
        }
    }
}
