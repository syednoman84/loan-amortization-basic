package com.example.loanamortizationbasic.controller;

import com.example.loanamortizationbasic.entity.AmortizationSchedule;
import com.example.loanamortizationbasic.entity.Loan;
import com.example.loanamortizationbasic.responseDTO.LoanResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/loanAmortization")
public class LoanAmortizationController {

    @PostMapping("/generate")
    public ResponseEntity<LoanResponseDTO> generateAmortizationSchedule(@RequestBody Loan loan) {

        double loanAmount = loan.getLoanAmount();
        double annualInterestRate = loan.getInterestRate();
        int numYears = loan.getYears();

        double monthlyInterestPaid, monthlyPrincipalPaid, newBalance;
        double monthlyInterestRate, monthlyPayment, totalPayment;
        int month;
        int numMonths = numYears * 12;

        // Calculate monthly interest
        monthlyInterestRate = annualInterestRate / 1200;

        // Calculate monthly payment
        monthlyPayment = loanAmount * monthlyInterestRate /
                (1 - 1 / Math.pow(1 + monthlyInterestRate, numMonths));

        // Calculate total payment
        totalPayment = monthlyPayment * numMonths;

        List<AmortizationSchedule> schedule = new ArrayList<AmortizationSchedule>();

        // Calculate interest paid, principal paid and new remaining balance for each month
        for (month = 1; month <= numMonths; month++) {
            monthlyInterestPaid = loanAmount * monthlyInterestRate;
            monthlyPrincipalPaid = monthlyPayment - monthlyInterestPaid;
            newBalance = loanAmount - monthlyPrincipalPaid;

            // adding monthly schedule record
            schedule.add(new AmortizationSchedule(month, monthlyInterestPaid, monthlyPrincipalPaid, newBalance));

            // Update the balance after each month
            loanAmount = newBalance;
        }
        return new ResponseEntity<>(new LoanResponseDTO(monthlyPayment, totalPayment, schedule), HttpStatus.OK);

    }

//    @PostMapping("/generate")
//    public ResponseEntity<LoanResponseDTO> generateAmortizationSchedule(@RequestBody Loan loan) {
//        double monthlyPayment = 200;
//        double totalPayment = 10000;
//
//        List<AmortizationSchedule> schedule = new ArrayList<AmortizationSchedule>();
//        schedule.add(new AmortizationSchedule(1, 200, 1000, 7000));
//        schedule.add(new AmortizationSchedule(2, 300, 2000, 8000));
//        schedule.add(new AmortizationSchedule(3, 400, 3000, 9000));
//
//        return new ResponseEntity<>(new LoanResponseDTO(monthlyPayment, totalPayment, schedule), HttpStatus.OK);
//    }
}
