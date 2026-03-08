package com.portfolio.my_portfolio_backend.controller.web;

import com.portfolio.my_portfolio_backend.dto.GuestbookMessageDto;
import com.portfolio.my_portfolio_backend.mapper.GuestbookMessageMapper;
import com.portfolio.my_portfolio_backend.model.GuestbookMessage;
import com.portfolio.my_portfolio_backend.service.ModerationService;
import com.portfolio.my_portfolio_backend.service.IGuestbookMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/guestbook")
@RequiredArgsConstructor
public class GuestbookMessageController {

    private final IGuestbookMessageService guestbookService;
    private final ModerationService moderationService;

    /**
     * Procesa el envío de un comentario al Libro de Visitas.
     *
     * Flujo:
     * 1. Valida el DTO (NotBlank, Size, etc.)
     * 2. Verifica el honeypot contra bots
     * 3. LLAMA A LA IA SÍNCRONAMENTE para validar nombre + mensaje
     * 4. Si REJECTED: No guarda nada, muestra error
     * 5. Si APPROVED o PENDING: Mapea, guarda y muestra éxito
     */
    @PostMapping("/send")
    public String sendMessage(@Valid @ModelAttribute("guestbookMessageDto") GuestbookMessageDto dto,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {

        // 1. Si hay errores de validación (@NotBlank, @Size)
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Por favor, revisa los datos del formulario.");
            return "redirect:/#guestbook";
        }

        // 2. Trampa para bots (Honeypot)
        if (dto.getExtraField() != null && !dto.getExtraField().isEmpty()) {
            // Fingimos que todo fue bien para engañar al bot, pero no guardamos nada
            return "redirect:/#guestbook";
        }

        try {
            // 3. LLAMAMOS A LA IA SÍNCRONAMENTE antes de guardar
            String moderationResult = moderationService.checkContent(dto.getName(), dto.getMessage());

            // 4. Si la IA rechaza el contenido
            if ("REJECTED".equals(moderationResult)) {
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Tu comentario no puede ser publicado porque contiene contenido inapropiado. " +
                        "Por favor, revisa tu mensaje y envía de nuevo.");
                return "redirect:/#guestbook";
            }

            // 5. Si APPROVED o PENDING (fallback por fallo de red): Guardamos el mensaje
            GuestbookMessage entity = GuestbookMessageMapper.toEntity(dto, moderationResult);
            guestbookService.save(entity);

            redirectAttributes.addFlashAttribute("successMessage",
                    moderationResult.equals("APPROVED")
                            ? "¡Gracias! Tu comentario ha sido publicado exitosamente."
                            : "¡Gracias! Tu comentario está siendo procesado.");
            return "redirect:/#guestbook";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al enviar el mensaje: " + e.getMessage());
            return "redirect:/#guestbook";
        }
    }
}
