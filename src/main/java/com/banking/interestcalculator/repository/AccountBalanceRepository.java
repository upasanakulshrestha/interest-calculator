package com.banking.interestcalculator.repository;

import com.banking.interestcalculator.model.AccountBalance;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AccountBalanceRepository extends ReactiveCrudRepository<AccountBalance,Integer> {
    Mono<AccountBalance> findByAccountId(Integer accountId);
}
