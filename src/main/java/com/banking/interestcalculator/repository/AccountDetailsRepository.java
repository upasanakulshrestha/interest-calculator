package com.banking.interestcalculator.repository;

import com.banking.interestcalculator.model.Account;
import com.banking.interestcalculator.model.AccountDetails;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountDetailsRepository extends ReactiveCrudRepository<AccountDetails,Integer> {
    Flux<AccountDetails> findByBsb(int bsb);

    Mono<AccountDetails> findByAccountId(Integer accountId);

    @Query("select Case when Exists (select * from account_details where account_id = $1) then TRUE else FALSE End")
    Mono<Boolean> existsByAccountId(Integer accountId);
}
