package com.portfolio.my_portfolio_backend.repository;

import com.portfolio.my_portfolio_backend.model.Experience;

import java.util.List;
import java.util.Optional;

public interface IExperienceRepository {
    Experience save(Experience experience);
    Optional<Experience> findById(Long id);
    List<Experience> findAll();
    void deleteById(Long id);
    List<Experience> findByPersonalInfoId(Long personalInfoId);

}
