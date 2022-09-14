package com.example.loanamortizationbasic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Loan {
    double loanAmount;
    int years;
    double interestRate;
}
