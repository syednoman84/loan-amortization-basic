package com.example.loanamortizationbasic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AmortizationSchedule {
    int paymentNumber;
    double interest;
    double principal;
    double balance;
}
