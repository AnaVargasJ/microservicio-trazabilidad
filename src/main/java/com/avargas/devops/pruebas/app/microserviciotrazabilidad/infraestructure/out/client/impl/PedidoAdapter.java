package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.client.impl;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.api.IClientAdapter;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.PedidoModel;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.commons.constants.EndPointApi;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception.ClientException;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.client.IGenericHttpClient;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception.Exception.DATA_ERROR_CLIENT;

@RequiredArgsConstructor
public class PedidoAdapter implements IClientAdapter {

    private final IGenericHttpClient httpClient;

    @Value("${microservicioPedido}")
    private String baseUrl;

    private static final String KEY_RESPUESTA = "respuesta";

    @Override
    public List<PedidoModel> obtenerPedidosPorRestaurante(String token, Long idRestaurante) {
        String url = this.baseUrl + EndPointApi.FILTRAR_PEDIDOS_ID_RESTAURANTE;
        String finalUrl = UriComponentsBuilder.fromHttpUrl(url).buildAndExpand(idRestaurante).toUriString();
        Map<String, String> headers = Map.of(HttpHeaders.AUTHORIZATION, token);
        Map<String, Object> response = httpClient.sendRequest(finalUrl, HttpMethod.GET, null, headers);

        if (response == null || !response.containsKey(KEY_RESPUESTA))
            throw new ClientException(DATA_ERROR_CLIENT.getMessage());

        List<Map<String, Object>> rawList = (List<Map<String, Object>>) response.get(KEY_RESPUESTA);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        mapper.registerModule(module);

        return rawList.stream()
                .map(item -> mapper.convertValue(item, PedidoModel.class))
                .collect(Collectors.toList());
    }
}
