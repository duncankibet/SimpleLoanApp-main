/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collins.loan.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author KEN19283
 */
@Data
@Entity
@Table(name = "loan")
public class LoanModel implements Serializable {
  private static final long serialVersionUID = 1L;
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "mobile")
    private String mobile;
    @Column(name = "location")
    private String location;
    @Column(name = "loan_limit")
    private long loan_limit;
    @Column(name = "loan_percentage")
    private double loan_percentage;
    @Column(name = "date")
    private String date;
    @Column(name = "duedate")
    private String duedate;
    @Column(name = "amount_requested")
    private long amount_requested;
    @Column(name = "amount_due")
    private long amount_due;
     @Column(name = "status")
    private String status;
    
    

    

   
   
    

}
