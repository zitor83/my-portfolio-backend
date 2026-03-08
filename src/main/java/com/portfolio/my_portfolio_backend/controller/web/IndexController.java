package com.portfolio.my_portfolio_backend.controller.web;

import com.portfolio.my_portfolio_backend.dto.GuestbookMessageDto;
import com.portfolio.my_portfolio_backend.model.GuestbookMessage;
import com.portfolio.my_portfolio_backend.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final IPersonalInfoService personalInfoService;
    private final IEducationService educationService;
    private final IExperienceService experienceService;
    private final ISkillService skillService;
    private final IProjectService projectService;
    private final IGuestbookMessageService guestbookMessageService;

    @GetMapping("/")
    public String showIndex(@RequestParam(name = "page", defaultValue = "0") int page,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        System.out.println("Mostrando la página principal...");

        // --- SOLUCIÓN AL ERROR DE SESIÓN CSRF ---
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrfToken != null) {
            csrfToken.getToken();
        }
        // Si el modelo contiene el mensaje de exito, le decimos al navegador que recargue la pagina en 1
        // segundos para limpiar el formulario y evitar
        if (model.containsAttribute("successMessage")) {
            response.setHeader("Refresh", "1; URL=/#guestbook");
        }
        // ----------------------------------------

        model.addAttribute("personalInfo", personalInfoService.findAll().getFirst());
        model.addAttribute("experienceList", experienceService.findAll());
        model.addAttribute("educationList", educationService.findAll());
        model.addAttribute("skills", skillService.findAll());
        model.addAttribute("projectsList", projectService.findAll());

        // Paginación: 3 mensajes por página
        Pageable pageable = PageRequest.of(page, 3);
        Page<GuestbookMessage> messagesPage = guestbookMessageService.findApprovedMessages(pageable);

        model.addAttribute("guestbookMessages", messagesPage.getContent());
        model.addAttribute("currentPage", messagesPage.getNumber());
        model.addAttribute("totalPages", messagesPage.getTotalPages());
        model.addAttribute("hasNext", messagesPage.hasNext());
        model.addAttribute("hasPrevious", messagesPage.hasPrevious());

        // El DTO vacío para el formulario (vital para que Thymeleaf no dé error)
        model.addAttribute("guestbookMessageDto", new GuestbookMessageDto());

        return "index";
    }
}