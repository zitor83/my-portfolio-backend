package com.portfolio.my_portfolio_backend.repository;

import com.portfolio.my_portfolio_backend.model.Education;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EducationRepositoryImpl implements IEducationRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Education> educationRowMapper = (rs, numRow) -> {
        Education education = new Education();

        education.setId(rs.getLong("id"));
        education.setDegree(rs.getString("degree"));
        education.setInstitution(rs.getString("institution"));
        education.setStartDate(rs.getObject("start_date", LocalDate.class));
        education.setEndDate(rs.getObject("end_date", LocalDate.class)); // Puede ser nulo
        education.setDescription(rs.getString("description"));
        education.setPersonalInfoId(rs.getLong("personal_info_id"));
        return education;
    };

    @Override
    public Education save(Education education) {
        if (education.getId() == null) {
            String sql = "Insert into educations " +
                    "(degree, institution,start_date, end_date, description, personal_info_id)" +
                    " Values ( ?, ?, ?, ?, ?, ? )";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {

                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});

                ps.setString(1, education.getDegree());
                ps.setString(2, education.getInstitution());
                ps.setDate(3, java.sql.Date.valueOf(education.getStartDate()));
                if (education.getEndDate() != null) {
                    ps.setDate(4, java.sql.Date.valueOf(education.getEndDate()));
                } else {
                    ps.setNull(4, java.sql.Types.DATE);
                }
                ps.setString(5, education.getDescription());
                ps.setLong(6, education.getPersonalInfoId());

                return ps;

            }, keyHolder);
            education.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        } else {
            String sql = "UPDATE educations set degree = ?, institution = ?, start_date = ?, end_date = ?, description = ?, personal_info_id = ? where id = ?";
            jdbcTemplate.update(sql,
                    education.getDegree(),
                    education.getInstitution(),
                    education.getStartDate(),
                    education.getEndDate(),
                    education.getDescription(),
                    education.getPersonalInfoId(),
                    education.getId()
            );
        }
        return education;

    }

    @Override
    public Optional<Education> findById(Long id) {
        String sql = "Select * from educations where id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, educationRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    @Override
    public List<Education> findAll() {
        String sql = "Select * from educations";
        return jdbcTemplate.query(sql, educationRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "Delete from educations where id = ?";
        jdbcTemplate.update(sql, id);


    }

    @Override
    public List<Education> findByPersonalInfoId(Long personalInfoId) {
        String sql = "Select * from educations where personal_info_id = ?";

        return jdbcTemplate.query(sql, educationRowMapper, personalInfoId);
    }
}
