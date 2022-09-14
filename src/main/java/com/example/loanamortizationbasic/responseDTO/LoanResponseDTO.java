package com.example.loanamortizationbasic.responseDTO;

import com.example.loanamortizationbasic.entity.AmortizationSchedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LoanResponseDTO {
    double monthlyPayment;
    double totalPayment;
    List<AmortizationSchedule> schedule;
}
