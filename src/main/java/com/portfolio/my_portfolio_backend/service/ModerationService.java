package com.portfolio.my_portfolio_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

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

    /**
     * Evalúa síncronamente el nombre y mensaje del comentario.
     * Retorna "APPROVED" si es apropiado, "REJECTED" si contiene contenido inapropiado,
     * o "PENDING" si hay error de red (fallback seguro).
     *
     * @param name El nombre del autor del comentario
     * @param messageText El cuerpo del mensaje
     * @return "APPROVED", "REJECTED" o "PENDING"
     */
    public String checkContent(String name, String messageText) {

        // Unimos los datos para que la IA los vea claramente
        String contentToCheck = "Nombre: " + name + "\nMensaje: " + messageText;

        Map<String, Object> body = Map.of(
                "model", "llama-3.1-8b-instant",
                "messages", List.of(
                        Map.of("role", "system", "content",
                                "Eres un moderador de comentarios para un portfolio web. Tu único objetivo es bloquear spam, " +
                                "insultos, palabras malsonantes, ataques personales o código malicioso TANTO en el nombre del autor " +
                                "como en el mensaje.\n\n" +
                                "Los comentarios cortos, entusiastos, con abreviaturas o palabras en MAYÚSCULAS están TOTALMENTE " +
                                "PERMITIDOS si son positivos o neutrales (ej: '¡EXCELENTE!', 'Muy bueno', ':)', 'Esto es genial!!!', etc).\n\n" +
                                "Responde ÚNICAMENTE con la palabra 'APPROVED' si tanto el nombre como el mensaje son respetuosos, " +
                                "o 'REJECTED' si CUALQUIERA de los dos contiene insultos, ataques, burlas, spam o contenido malicioso."),
                        Map.of("role", "user", "content", contentToCheck)
                ),
                "temperature", 0.0
        );

        try {
            var response = restClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .body(Map.class);

            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
            String result = message.get("content").toString().trim();

            // Validamos que la respuesta sea válida
            if ("APPROVED".equalsIgnoreCase(result) || "REJECTED".equalsIgnoreCase(result)) {
                return result.toUpperCase();
            } else {
                // Si la IA responde algo inesperado, retornamos PENDING (fallback seguro)
                System.err.println("Respuesta inesperada de Groq: " + result);
                return "PENDING";
            }

        } catch (Exception e) {
            System.err.println("Error llamando a Groq: " + e.getMessage());
            // Fallback seguro: si hay error de red, guardamos como PENDING
            return "PENDING";
        }
    }
}
