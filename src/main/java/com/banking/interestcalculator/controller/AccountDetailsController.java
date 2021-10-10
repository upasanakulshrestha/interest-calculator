package com.banking.interestcalculator.controller;

import com.banking.interestcalculator.dto.AccountHistoryDTO;
import com.banking.interestcalculator.model.Account;
import com.banking.interestcalculator.model.AccountDetails;
import com.banking.interestcalculator.model.AccountHistory;
import com.banking.interestcalculator.model.DailyAccountRequest;
import com.banking.interestcalculator.service.AccountService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountDetailsController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AccountDetails> create(@RequestBody AccountDetails account){
        return accountService.createAccount(account);
    }

    @GetMapping
    public Flux<AccountDetails> getAllAccountDetails(){
        return accountService.getAllAccount();
    }

    @GetMapping("/{accountId}")
    public Mono<ResponseEntity<AccountDetails>> getAccountById(@PathVariable Integer accountId){
        Mono<AccountDetails> account = accountService.findByAccountId(accountId);
        return account.map( u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/bsb/{bsb}")
    public Flux<AccountDetails> getAccountsByBsb(@PathVariable int bsb) {
        return accountService.findAccountsByBsb(bsb);
    }

    @PostMapping("/dailyRequest")
    public Flux<Account> updateDaily1(@RequestBody DailyAccountRequest dailyAccountRequest){
        return accountService.calculateInterestService1(dailyAccountRequest.getAccount(), dailyAccountRequest);
    }


    /*@PostMapping("/daily1")
    public Flux<AccountHistoryDTO> updateAccounts1(@RequestBody DailyAccountRequest dailyAccountRequest) {
        return accountService.calculateDailyInterestAndStore2(dailyAccountRequest.getAccount(), dailyAccountRequest);
                *//*.map(accountHistory -> ResponseEntity.ok(accountHistory))
                .defaultIfEmpty(ResponseEntity.badRequest().build());*//*
    }

    @PostMapping("/daily2")
    public Flux<Account> updateDaily(@RequestBody DailyAccountRequest dailyAccountRequest){
        return accountService.calculateInterestService(dailyAccountRequest.getAccount(), dailyAccountRequest);
    }*/

}
