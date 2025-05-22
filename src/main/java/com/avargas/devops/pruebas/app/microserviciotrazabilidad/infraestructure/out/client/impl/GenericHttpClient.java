package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.client.impl;


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
                responseMap = new HashMap<>();
                responseMap.put("mensaje", "Error al parsear la respuesta JSON");
                responseMap.put("respuesta_cruda", rawResponse);
                responseMap.put("error_detalle", jsonEx.getMessage());
            }

            return responseMap;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error en la solicitud HTTP: " + e.getMessage());
        }
    }
}

