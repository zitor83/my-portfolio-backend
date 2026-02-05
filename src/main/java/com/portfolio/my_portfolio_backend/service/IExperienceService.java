package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.model.Experience;

import java.util.List;
import java.util.Optional;

public interface IExperienceService {
    Experience save(Experience experience);
    Optional<Experience> findById(Long id);
    List<Experience> findAll();
    void deleteById(Long id);
    List<Experience> findExperienceByPersonalInfoId(Long personalInfoId);
}
