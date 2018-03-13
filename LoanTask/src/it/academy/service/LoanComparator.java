package it.academy.service;

import it.academy.domain.Loan;
import it.academy.util.LoanUtil;

import java.util.Comparator;

public class LoanComparator implements Comparator<Loan> {
    @Override
    public int compare(Loan o1, Loan o2) {

        //You don't need to compare all of those fields. You should compare them 1 at a time and if comparison returns <> 0, you
        // should return this result, else continue with a next field.
        //Currently, you are actually waisting resources.
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
