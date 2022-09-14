package com.example.loanamortizationbasic.controller;

import com.example.loanamortizationbasic.entity.AmortizationSchedule;
import com.example.loanamortizationbasic.entity.Loan;
import com.example.loanamortizationbasic.responseDTO.LoanResponseDTO;
import com.example.loanamortizationbasic.service.AmortizationScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AmortizationScheduleService amortizationScheduleService;

    @PostMapping("/generate")
    public ResponseEntity<LoanResponseDTO> generateAmortizationSchedule(@RequestBody Loan loan) {

        LoanResponseDTO finalAmortizationSchedule = amortizationScheduleService.generateSchedule(loan.getLoanAmount(),
                loan.getInterestRate(),
                loan.getYears());

        return new ResponseEntity<>(finalAmortizationSchedule, HttpStatus.OK);
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
