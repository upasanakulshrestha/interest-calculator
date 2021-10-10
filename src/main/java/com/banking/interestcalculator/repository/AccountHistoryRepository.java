package com.banking.interestcalculator.repository;

import com.banking.interestcalculator.model.Account;
import com.banking.interestcalculator.model.AccountBalance;
import com.banking.interestcalculator.model.AccountHistory;
import org.reactivestreams.Publisher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AccountHistoryRepository extends ReactiveCrudRepository<AccountHistory,Integer> {
    //Publisher<AccountHistory> findByAccountIds(Integer i);
    //Mono<AccountBalance> findByAccountId(Integer accountId);
}
