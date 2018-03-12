package it.academy.service;

import it.academy.domain.Loan;
import it.academy.util.LoanUtil;

import java.util.Comparator;

public class LoanComparator implements Comparator<Loan> {
    @Override
    public int compare(Loan o1, Loan o2) {

        int riskTypeCompareResult = new LoanRiskTypeComparator().compare(o1.getRiskType(), o2.getRiskType());
        int totalCostCompareResult = o2.getTotalLoanCost().compareTo(o1.getTotalLoanCost());
        int creationDateCompareResult = o1.getCreationDate().compareTo(o2.getCreationDate());

        if (riskTypeCompareResult != 0) {
            return riskTypeCompareResult;
        }

        if (totalCostCompareResult != 0) {
            return totalCostCompareResult;
        }

        return creationDateCompareResult;
    }
}
