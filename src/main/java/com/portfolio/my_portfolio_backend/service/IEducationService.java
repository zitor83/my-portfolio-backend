package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.model.Education;

import java.util.List;
import java.util.Optional;

public interface IEducationService {
    Education save(Education education);
    Optional<Education> findById(Long id);
    List<Education> findAll();
    void deleteById(Long id);
    List<Education> findEducationsByPersonalInfoId(Long personalInfoId);
}
