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
        return toEntity(dto, "PENDING");
    }

    /**
     * Mapea DTO a Entidad con estado específico.
     * Usado en el flujo de moderación síncrona para establecer APPROVED, REJECTED o PENDING.
     *
     * @param dto El DTO del formulario
     * @param status El estado a asignar ("APPROVED", "REJECTED" o "PENDING")
     * @return Entidad GuestbookMessage lista para guardar
     */
    public static GuestbookMessage toEntity(GuestbookMessageDto dto, String status) {
        if (dto == null) {
            return null;
        }
        GuestbookMessage entity = new GuestbookMessage();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setMessage(dto.getMessage());

        // Asignamos el estado especificado
        entity.setStatus(status);
        entity.setCreatedAt(LocalDateTime.now());

        return entity;
    }
}