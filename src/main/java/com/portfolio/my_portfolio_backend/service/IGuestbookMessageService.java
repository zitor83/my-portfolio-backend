package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.model.GuestbookMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IGuestbookMessageService {

    GuestbookMessage save(GuestbookMessage guestbookMessage);
    Optional<GuestbookMessage> findById(Long id);
    List<GuestbookMessage> findAll();
    void deleteById(Long id);
    List<GuestbookMessage> findApprovedMessages();
    Page<GuestbookMessage> findApprovedMessages(Pageable pageable);
}
