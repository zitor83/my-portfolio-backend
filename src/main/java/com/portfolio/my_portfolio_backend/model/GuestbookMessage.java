package com.portfolio.my_portfolio_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("guestbook_messages")
public class GuestbookMessage {

    @Id
    private Long id;
    private String name;
    private String message;
    private String status; // Guardará "PENDING", "APPROVED" o "REJECTED"
    private LocalDateTime createdAt;
}