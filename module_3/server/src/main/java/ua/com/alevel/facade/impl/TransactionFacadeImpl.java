package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.api.dto.request.TransactionRequestDto;
import ua.com.alevel.api.dto.response.TransactionResponseDto;
import ua.com.alevel.facade.TransactionFacade;
import ua.com.alevel.persistence.entity.Transaction;
import ua.com.alevel.service.TransactionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionFacadeImpl implements TransactionFacade {

    private final TransactionService transactionService;

    public TransactionFacadeImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void create(TransactionRequestDto request) {
        Transaction transaction = new Transaction();
        transaction.setAccounts(request.getAccountId());
        transaction.setCategories(request.getCategoryId());
        transaction.setAmount(request.getAmount());
        transactionService.create(transaction);
    }

    @Override
    public void update(TransactionRequestDto request, Long id) {
        Transaction transaction = new Transaction();
        transaction.setAccounts(request.getAccountId());
        transaction.setCategories(request.getCategoryId());
        transaction.setAmount(request.getAmount());
        transactionService.update(transaction);
    }

    @Override
    public void delete(Long id) {
        transactionService.delete(id);
    }

    @Override
    public TransactionResponseDto findById(Long id) {
        return new TransactionResponseDto(transactionService.findById(id));
    }

    @Override
    public List<TransactionResponseDto> findAll() {
        return transactionService.findAll().stream()
                .map(TransactionResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionResponseDto> findAllByAccountId(Long accountId) {
        return transactionService.findAllByAccountId(accountId).stream()
                .map(TransactionResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionResponseDto> findAllByCategoryId(Long categoryId) {
        return transactionService.findAllByCategoryId(categoryId).stream()
                .map(TransactionResponseDto::new)
                .collect(Collectors.toList());
    }
}