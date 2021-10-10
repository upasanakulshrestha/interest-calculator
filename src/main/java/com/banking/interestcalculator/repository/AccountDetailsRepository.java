package com.banking.interestcalculator.repository;

import com.banking.interestcalculator.model.AccountDetails;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountDetailsRepository extends ReactiveCrudRepository<AccountDetails,Integer> {
    Flux<AccountDetails> findByBsb(int bsb);

    Mono<AccountDetails> findByAccountId(Integer accountId);
}
