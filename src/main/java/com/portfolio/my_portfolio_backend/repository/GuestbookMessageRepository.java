package com.portfolio.my_portfolio_backend.repository;
import com.portfolio.my_portfolio_backend.model.GuestbookMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GuestbookMessageRepository extends CrudRepository<GuestbookMessage, Long>,
                                                     PagingAndSortingRepository<GuestbookMessage, Long> {

    // Spring Data crea la consulta SQL automáticamente gracias al nombre del metodo.
    // Traerá los mensajes filtrados por estado (ej: "APPROVED") y ordenados del más nuevo al más antiguo.
    List<GuestbookMessage> findByStatusOrderByCreatedAtDesc(String status);

    // Método con paginación
    Page<GuestbookMessage> findByStatusOrderByCreatedAtDesc(String status, Pageable pageable);
}