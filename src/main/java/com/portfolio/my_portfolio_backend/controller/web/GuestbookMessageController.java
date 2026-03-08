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
    private final ModerationService moderationService; // Para actualizar el estado después de la IA

    @PostMapping("/send")
    public String sendMessage(@Valid @ModelAttribute("guestbookMessageDto") GuestbookMessageDto dto,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {

        // 1. Si hay errores de validación (@NotBlank, @Size)
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Por favor, revisa los datos del formulario.");
            return "redirect:/#guestbook"; // Redirigimos al ancla de la sección
        }

        // 2. Trampa para bots (Honeypot) - extraField está en tu DTO
        if (dto.getExtraField() != null && !dto.getExtraField().isEmpty()) {
            // Fingimos que todo fue bien para engañar al bot, pero no guardamos nada
            return "redirect:/#guestbook";
        }

        try {
            // 3. Mapeamos a Entidad
            GuestbookMessage entity = GuestbookMessageMapper.toEntity(dto);

            // 4. Guardamos en BD (estado PENDING por defecto gracias al Mapper)
            final GuestbookMessage savedMsg = guestbookService.save(entity);

            // 5. Llamamos a la IA en segundo plano
          moderationService.checkContent(dto.getMessage()).thenAccept(aiResult -> {
                String finalStatus = aiResult.contains("APPROVED") ? "APPROVED" : "REJECTED";

                // Actualizamos el estado usando el servicio
                savedMsg.setStatus(finalStatus);
                guestbookService.save(savedMsg);
            });

            redirectAttributes.addFlashAttribute("successMessage", "¡Gracias! Tu mensaje ha sido enviado y está siendo procesado por mi sistema de IA.");
            return "redirect:/#guestbook";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al enviar el mensaje: " + e.getMessage());
            return "redirect:/#guestbook";
        }
    }
}