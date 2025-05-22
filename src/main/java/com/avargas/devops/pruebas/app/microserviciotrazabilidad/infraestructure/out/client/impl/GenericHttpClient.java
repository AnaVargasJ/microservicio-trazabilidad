package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.client.impl;


import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception.ClientException;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception.JsonParseClientException;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.client.IGenericHttpClient;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception.Exception.JSON_EXCEPTION;
import static com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception.Exception.SOLICITUD_ERROR;


public class GenericHttpClient implements IGenericHttpClient {

    private final WebClient.Builder webClientBuilder;

    public GenericHttpClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Map<String, Object> sendRequest(String url, HttpMethod method, Map<String, Object> body,
                                           Map<String, String> headers) {

        WebClient.RequestBodySpec requestSpec = webClientBuilder
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .method(method)
                .uri(url);

        WebClient.RequestHeadersSpec<?> headersSpec = body != null
                ? requestSpec.bodyValue(body)
                : requestSpec;

        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                headersSpec = headersSpec.header(entry.getKey(), entry.getValue());
            }
        }

        try {
            String rawResponse = headersSpec
                    .exchangeToMono(response -> response.bodyToMono(String.class))
                    .block();

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

            SimpleModule module = new SimpleModule();
            module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            mapper.registerModule(module);
            Map<String, Object> responseMap;

            try {
                responseMap = mapper.readValue(rawResponse, Map.class);
            } catch (Exception jsonEx) {
                throw new JsonParseClientException(JSON_EXCEPTION.getMessage() + jsonEx.getMessage(), jsonEx);
            }

            return responseMap;

        } catch (Exception e) {
            throw new JsonParseClientException(SOLICITUD_ERROR.getMessage() + e.getMessage(), e);

        }
    }
}

