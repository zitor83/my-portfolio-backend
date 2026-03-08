package com.portfolio.my_portfolio_backend.mapper;



import com.portfolio.my_portfolio_backend.dto.GuestbookMessageDto;
import com.portfolio.my_portfolio_backend.model.GuestbookMessage;

import java.time.LocalDateTime;

public class GuestbookMessageMapper {

    public static GuestbookMessageDto toDto(GuestbookMessage entity) {
        if (entity == null) {
            return null;
        }
        GuestbookMessageDto dto = new GuestbookMessageDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setMessage(entity.getMessage());
        // No mapeamos status ni createdAt al DTO del formulario porque el usuario no los toca
        return dto;
    }

    public static GuestbookMessage toEntity(GuestbookMessageDto dto) {
        if (dto == null) {
            return null;
        }
        GuestbookMessage entity = new GuestbookMessage();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setMessage(dto.getMessage());

        // Asignamos valores por defecto para un nuevo mensaje
        entity.setStatus("PENDING");
        entity.setCreatedAt(LocalDateTime.now());

        return entity;
    }
}