package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.model.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectService {
    List<Project> findAll();

    Project save(Project project);

    void deleteById(Long id);

    Optional<Project> findById(Long id);

}
