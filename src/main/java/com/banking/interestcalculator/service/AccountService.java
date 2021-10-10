package com.banking.interestcalculator.service;

import com.banking.interestcalculator.dto.AccountHistoryDTO;
import com.banking.interestcalculator.model.*;
import com.banking.interestcalculator.repository.AccountBalanceRepository;
import com.banking.interestcalculator.repository.AccountDetailsRepository;
import com.banking.interestcalculator.repository.AccountHistoryRepository;
import io.swagger.models.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class AccountService {

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;

    @Autowired
    private AccountHistoryRepository accountHistoryRepository;

    public Mono<AccountDetails> createAccount(AccountDetails account) {
        return accountDetailsRepository.save(account);
        /*AccountBalance accountBalance = new AccountBalance();
        accountBalance.setAmount(0d);
        accountBalance.setAccountId(account.getAccountId());
        accountBalanceRepository.save(accountBalance);
        return accountDetailsRepository.findByAccountId(account.getAccountId());*/
    }

    public Flux<AccountDetails> getAllAccount() {
        return accountDetailsRepository.findAll();
    }

    public Mono<AccountDetails> findByAccountId(Integer accountId) {
        return accountDetailsRepository.findByAccountId(accountId);
    }

    public Flux<AccountDetails> findAccountsByBsb(int bsb) {
        return accountDetailsRepository.findByBsb(bsb);
    }

    /*public Flux<AccountDetails> findByAccountIds(List<Integer> accountIds) {
        return Flux.fromIterable(accountIds)
                .parallel()
                .runOn(Schedulers.elastic())
                .flatMap(i -> accountDetailsRepository.findByAccountId(i))
                .ordered((u1, u2) -> u2.getAccountId() - u1.getAccountId());
    }*/

    public Mono<List<Integer>> calculateDailyInterestAndStore(List<Account> accounts, LocalDate balanceDate) {
        double dailyInterest=1;

        List<Integer> accountIds = accounts.stream().map(Account::getAccountId).collect(Collectors.toList());

        /*return accountHistoryRepository.findAllById(accountIds.parallelStream().collect(Collectors.toList()))
                .flatMap(accountHistory ->  {
                    accountHistory.setTrnType("Interest");
                    accountHistory.setCreditDebitIndicator('C');
                    accountHistory.setAmount(dailyInterest);
                    accountHistory.setCreditedDate(balanceDate);
                    return accountHistoryRepository.save(accountHistory);
                });*/


         /*return Flux.fromIterable(accountIds)
               .parallel()
                .runOn(Schedulers.elastic())
                .flatMap(i -> accountHistoryRepository.findByAccountIds(i))
                .ordered((u1, u2) -> u2.getAccountId() - u1.getAccountId());*/
        for(Account account: accounts) {

            AccountHistory accountHistory = new AccountHistory();
            accountHistory.setAccountId(account.getAccountId());
            accountHistory.setAmount(dailyInterest);
            accountHistory.setBsb(account.getBsb());
            accountHistory.setCreditDebitIndicator('C');
            accountHistory.setCreditedDate(balanceDate);
            accountHistory.setTrnType("Interest");

            accountHistoryRepository.save(accountHistory);
            /*accountBalanceRepository.findByAccountId(account.getAccountId())
                    .flatMap(accountBalance -> {
                        accountBalance.setAmount(dailyInterest+account.getAmount());
                        return accountBalanceRepository.save(accountBalance);
                    });*/
        }
        return Mono.just(accountIds);


    }

    public Flux<AccountHistoryDTO> calculateDailyInterestAndStore1(List<Account> accounts, DailyAccountRequest request) {
        Flux<Account> accountFlux = Flux.fromIterable(accounts);
        /*Flux<Account> accountFlux = Flux.create((FluxSink<Account> fluxSlink) -> {
            IntStream.range(0,5).peek(i -> System.out.println("Going to use "+i))
                    .forEach(fluxSlink::next);
            fluxSlink.next();
        });*/
        //accountFlux.subscribe(System.out::println);

        return Flux.zip(accountFlux, Flux.just(request), DTOBiFunction);
    }

    private BiFunction<Account, DailyAccountRequest, AccountHistoryDTO> DTOBiFunction = (x1, x2) -> AccountHistoryDTO.builder()
            .accountId(x1.getAccountId())
            .bsb(x1.getBsb())
            .creditedDate(x2.getBalanceDate())
            .trnType("Interest")
            .creditDebitIndicator('C')
            .amount(calculateInterest(x1.getAmount())).build();

    private double calculateInterest(double amount) {
        double dailyInterest = 0.01;
        double gainedInterest = amount * dailyInterest/100;
        return amount + gainedInterest;
    }
}
