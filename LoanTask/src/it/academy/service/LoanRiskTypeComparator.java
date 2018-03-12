package it.academy.service;

import it.academy.domain.LoanRiskType;

import java.util.Comparator;

public class LoanRiskTypeComparator implements Comparator<LoanRiskType> {
    @Override
    public int compare(LoanRiskType o1, LoanRiskType o2) {
        if(o1 == LoanRiskType.HIGH_RISK && o2 != LoanRiskType.HIGH_RISK) return -1;
        if(o1 != LoanRiskType.HIGH_RISK && o2 == LoanRiskType.HIGH_RISK) return 1;

        if(o1 == LoanRiskType.NORMAL_RISK && o2 != LoanRiskType.NORMAL_RISK) return -1;
        if(o1 != LoanRiskType.NORMAL_RISK && o2 == LoanRiskType.NORMAL_RISK) return 1;

        return 0;
    }
}
