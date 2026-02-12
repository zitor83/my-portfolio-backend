package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.service.IEducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/education")
@RequiredArgsConstructor
public class EducationController {

    private final IEducationService educationService;
}
