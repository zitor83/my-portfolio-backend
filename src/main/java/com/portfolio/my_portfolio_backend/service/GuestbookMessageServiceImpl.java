package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.model.GuestbookMessage;
import com.portfolio.my_portfolio_backend.repository.GuestbookMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestbookMessageServiceImpl implements IGuestbookMessageService {

    private final GuestbookMessageRepository repository;

    @Override
    public GuestbookMessage save(GuestbookMessage guestbookMessage) {
        return repository.save(guestbookMessage);
    }

    @Override
    public Optional<GuestbookMessage> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<GuestbookMessage> findAll() {
        // Casteamos el Iterable a List
        return (List<GuestbookMessage>) repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    @Override
    public List<GuestbookMessage> findApprovedMessages() {
        return repository.findByStatusOrderByCreatedAtDesc("APPROVED");
    }
    @Override
    public Page<GuestbookMessage> findApprovedMessages(Pageable pageable) {
        return repository.findByStatusOrderByCreatedAtDesc("APPROVED", pageable);
    }
}