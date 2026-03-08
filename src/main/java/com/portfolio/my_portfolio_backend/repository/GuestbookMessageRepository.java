package com.portfolio.my_portfolio_backend.repository;
import com.portfolio.my_portfolio_backend.model.GuestbookMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GuestbookMessageRepository extends CrudRepository<GuestbookMessage, Long> {

    // Spring Data crea la consulta SQL automáticamente gracias al nombre del metodo.
    // Traerá los mensajes filtrados por estado (ej: "APPROVED") y ordenados del más nuevo al más antiguo.
    List<GuestbookMessage> findByStatusOrderByCreatedAtDesc(String status);
}