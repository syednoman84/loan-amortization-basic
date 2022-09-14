package com.example.loanamortizationbasic.service;

import com.example.loanamortizationbasic.entity.AmortizationSchedule;
import com.example.loanamortizationbasic.responseDTO.LoanResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AmortizationScheduleService {
    public LoanResponseDTO generateSchedule(double originalLoanAmount, double interestRate, int totalYears) {
        double loanAmount = originalLoanAmount;
        double annualInterestRate = interestRate;
        int numYears = totalYears;

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

        return new LoanResponseDTO(monthlyPayment, totalPayment, schedule);
    }
}
