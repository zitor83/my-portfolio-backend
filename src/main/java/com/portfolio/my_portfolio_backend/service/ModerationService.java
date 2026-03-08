package com.portfolio.my_portfolio_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class ModerationService {

    private final RestClient restClient;

    // Inyectamos la API Key desde el application.properties
    public ModerationService(@Value("${groq.api.key}") String apiKey) {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.groq.com/openai/v1/chat/completions")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    @Async // Ejecuta esto en segundo plano para no hacer esperar al usuario
    public CompletableFuture<String> checkContent(String text) {
        // Preparamos el cuerpo de la petición (JSON)
        Map<String, Object> body = Map.of(
                "model", "llama3-8b-8192", // Modelo rápido y gratuito de Groq
                "messages", List.of(
                        Map.of("role", "system", "content", "Eres un moderador estricto. Responde SOLO con la palabra 'APPROVED' si el siguiente mensaje es apto para un portfolio profesional, o responde 'REJECTED' si es spam, contiene insultos, código malicioso o es inapropiado."),
                        Map.of("role", "user", "content", text)
                ),
                "temperature", 0.0 // Queremos respuestas deterministas, sin creatividad
        );

        try {
            // Hacemos la llamada POST a la API de Groq
            var response = restClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .body(Map.class);

            // Extraemos el texto de la respuesta del JSON devuelto
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
            String result = message.get("content").toString().trim();

            return CompletableFuture.completedFuture(result);

        } catch (Exception e) {
            // Si Groq se cae o falla la API, lo dejamos en PENDING para revisar manualmente después
            System.err.println("Error llamando a Groq: " + e.getMessage());
            return CompletableFuture.completedFuture("PENDING");
        }
    }
}