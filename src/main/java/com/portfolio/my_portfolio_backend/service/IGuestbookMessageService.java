package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.model.GuestbookMessage;


import java.util.List;
import java.util.Optional;

public interface IGuestbookMessageService {

    GuestbookMessage save(GuestbookMessage guestbookMessage);
    Optional<GuestbookMessage> findById(Long id);
    List<GuestbookMessage> findAll();
    void deleteById(Long id);
}
