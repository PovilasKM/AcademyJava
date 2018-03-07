package it.academy.service;

import it.academy.domain.Loan;
import it.academy.domain.LoanRiskType;

import java.util.Comparator;

public class LoanComparator  implements Comparator{
    @Override
    public int compare(Object oo1, Object oo2) {
        Loan o1 = (Loan) oo1;
        Loan o2 = (Loan) oo2;
        if(o1.getRiskType() == LoanRiskType.HIGH_RISK && o2.getRiskType() !=LoanRiskType.HIGH_RISK) return 1;
        if(o2.getRiskType() == LoanRiskType.HIGH_RISK && o1.getRiskType() !=LoanRiskType.HIGH_RISK) return -1;

        if(o1.getTotalLoanCost().compareTo(o2.getTotalLoanCost()) == 1) return 1;
        if(o1.getTotalLoanCost().compareTo(o2.getTotalLoanCost()) == -1) return -1;

        if(o1.getCreationDate().before(o2.getCreationDate())) return 1;
        if(o2.getCreationDate().before(o1.getCreationDate())) return -1;

        return 0;
    }
}
