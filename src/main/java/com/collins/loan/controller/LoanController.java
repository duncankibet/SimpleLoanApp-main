/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collins.loan.controller;

import com.collins.loan.model.LoanModel;
import com.collins.loan.service.LoanService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author KEN19283
 */
@RestController
@RequestMapping(value = "/loan", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoanController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);


    @Autowired
    LoanService loanService;
    private final MediaType mediaType = MediaType.APPLICATION_JSON;

    @GetMapping("/list")
    public @ResponseBody CompletableFuture<ResponseEntity> list() {
       
        return loanService.getAllLoans().<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetLoanFailure);

        
    }
   
    private static Function<Throwable, ResponseEntity<? extends List<LoanModel>>> handleGetLoanFailure = throwable -> {
        LOGGER.error("Failed to read records: {}", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };
     @PostMapping("/add_customer")
    public LoanModel addUser(@RequestBody LoanModel loanModel) throws Exception {
        if(loanModel.getLoan_limit()>25000|| loanModel.getLoan_limit()<=0){
            throw new Exception("failed");
        }
        loanService.save(loanModel);
        return loanModel;
    }
    

//    @PostMapping("/add_loan")
//    public LoanModel createLoan(@RequestBody LoanModel loanModel) throws Exception {
//        if(loanModel.getAmount_requested()>25000|| loanModel.getAmount_requested()<=0){
//            throw new Exception("Invalid loan request Please try a figure between 1 and 25000");
//        }
//        loanService.saveOrUpdate(loanModel);
//        return loanModel;
//    }

    @PutMapping("add_loan/{id}")
    public LoanModel updateLoan(@PathVariable long id, @RequestBody LoanModel loan)throws Exception {
        if(loan.getAmount_requested()>25000|| loan.getAmount_requested()<=0){
            throw new Exception("failed");
        }
        loanService.saveOrUpdate(id,loan);
    return loanService.getLoanById(id);
    }

    @GetMapping("view/{id}")
    public LoanModel viewLoan(@PathVariable long id) {

        LoanModel personDetails = loanService.getLoanById(id);

        return personDetails;
    }

    @DeleteMapping("delete/{id}")
    public LoanModel delete(@PathVariable long id) {

        loanService.deleteLoan(id);
        return new LoanModel();

    }

}
