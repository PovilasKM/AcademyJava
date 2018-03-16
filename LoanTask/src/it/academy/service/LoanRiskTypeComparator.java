package it.academy.service;

import it.academy.domain.LoanRiskType;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LoanRiskTypeComparator implements Comparator<LoanRiskType> {

    private final Map<LoanRiskType, Integer> LOAN_RISK_TYPE_RANKS = new HashMap<LoanRiskType, Integer>() {{
        put(LoanRiskType.LOW_RISK, 0);
        put(LoanRiskType.NORMAL_RISK, 1);
        put(LoanRiskType.HIGH_RISK, 2);
    }};

    @Override
    public int compare(LoanRiskType loanRiskTypeToCompare, LoanRiskType loanRiskTypeToCompareWith) {
        return getRank(loanRiskTypeToCompare).compareTo(getRank(loanRiskTypeToCompareWith));
    }

    private Integer getRank(LoanRiskType loanRiskType) {
        Integer rank = LOAN_RISK_TYPE_RANKS.get(loanRiskType);
        if (rank == null) {
            throw new IllegalArgumentException("LoanRiskType = " + loanRiskType + " not implemented yet!");
        }
        return rank;
    }
}
