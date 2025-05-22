package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.security.auth;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.response.ResponseDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception.TokenInvalidoException;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.client.IGenericHttpClient;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.security.jwt.TokenJwtConfig;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.security.model.UsuarioAutenticado;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.commons.constants.EndPointApi.URL_BUSCAR_POR_CORREO;
import static com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception.Exception.ERROR_CONSULTAR_USUARIO;
import static com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.security.jwt.TokenJwtConfig.CONTENT_TYPE;

@Slf4j
@Profile("!test")
public class JwtValidationFilter extends BasicAuthenticationFilter {


    private final IGenericHttpClient loginClient;

    @Value("${microserviciopropietarios}")
    private  String urlPropietarios;

    private static final String ROLE_PREFIX = "ROLE_";
    private static final String HEADER_ID_USUARIO = "idUsuario";
    private static final String HEADER_ROL = "rol";
    private static final String HEADER_NOMBRE = "nombre";
    private static final String RESPONSE_KEY = "respuesta";

    private static final String ERROR_TOKEN_EXPIRADO = "El token ha vencido";
    private static final String ERROR_TOKEN_INVALIDO = "Token inválido: ";
    private static final int CODIGO_EXITO = 200;


    public JwtValidationFilter(AuthenticationManager authManager, IGenericHttpClient loginClient) {
        super(authManager);
        this.loginClient = loginClient;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(TokenJwtConfig.HEADER_AUTHORIZATION);
        if (header == null || !header.startsWith(TokenJwtConfig.PREFIX_TOKEN)) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(TokenJwtConfig.PREFIX_TOKEN, "");
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            if (isTokenExpired(decodedJWT.getExpiresAt())) {
                reject(response, ERROR_TOKEN_EXPIRADO, HttpStatus.UNAUTHORIZED);
                return;
            }

            String correo = decodedJWT.getSubject();
            Map<String, Object> usuario = consultarUsuarioPorCorreo(correo, header);

            Long id = Long.valueOf(usuario.get(HEADER_ID_USUARIO).toString());
            String rol = ((Map<String, Object>) usuario.get(HEADER_ROL)).get(HEADER_NOMBRE).toString();

            Collection<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(ROLE_PREFIX + rol));

            UsuarioAutenticado usuarioAutenticado = new UsuarioAutenticado(
                    id,
                    correo,
                    null,
                    authorities
            );

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(usuarioAutenticado, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);

        } catch (Exception ex) {
            log.error("Error en validación de token: {}", ex.getMessage());
            reject(response, ERROR_TOKEN_INVALIDO + ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    private Map<String, Object> consultarUsuarioPorCorreo(String correo, String tokenHeader) {
        String url = this.urlPropietarios + URL_BUSCAR_POR_CORREO;
        Map<String, String> headers = Map.of(HttpHeaders.AUTHORIZATION, tokenHeader);

        Map<String, Object> response = loginClient.sendRequest(
                url.replace("{correo}", correo), HttpMethod.GET, null, headers
        );

        if (response == null || ((Number) response.get("codigo")).intValue() != CODIGO_EXITO) {
            throw new TokenInvalidoException(ERROR_CONSULTAR_USUARIO + correo);
        }

        return (Map<String, Object>) response.get(RESPONSE_KEY);
    }

    private boolean isTokenExpired(Date exp) {
        return exp.before(new Date());
    }

    private void reject(HttpServletResponse response, String mensaje, HttpStatus status) throws IOException {
        ResponseDTO error = ResponseDTO.builder()
                .mensaje(mensaje)
                .codigo(status.value())
                .build();
        response.setStatus(status.value());
        response.setContentType(CONTENT_TYPE);
        response.getWriter().write(new ObjectMapper().writeValueAsString(error));
    }
}