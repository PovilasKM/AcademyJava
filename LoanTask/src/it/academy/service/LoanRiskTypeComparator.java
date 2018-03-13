package it.academy.service;

import it.academy.domain.LoanRiskType;

import java.util.Comparator;

//Idea of having a separate comparator for "LoanRiskType" is correct
public class LoanRiskTypeComparator implements Comparator<LoanRiskType> {
    @Override
    public int compare(LoanRiskType o1, LoanRiskType o2) {
        //This is not a best approach. You are actually hardcoding enum values here. Imagine if additional values would be added to
        // "LoanRiskType" (ex.: NONE_RISK, NORMAL_TO_HIGH_RISK, etc.), so you would have to change this comparator every time change
        // to enum is done. The worst part is, that after "LoanRiskType" is changed, your code would still compile and work, but it
        // will output incorrect data.
        //Sadly, there is no 100% bulletproof way of implementing enum comparison, fortunately, there are some better methods than
        // this.
        //Please see my examples: https://github.com/andrewmic/enum-comparator-example
        if(o1 == LoanRiskType.HIGH_RISK && o2 != LoanRiskType.HIGH_RISK) return -1;
        if(o1 != LoanRiskType.HIGH_RISK && o2 == LoanRiskType.HIGH_RISK) return 1;

        if(o1 == LoanRiskType.NORMAL_RISK && o2 != LoanRiskType.NORMAL_RISK) return -1;
        if(o1 != LoanRiskType.NORMAL_RISK && o2 == LoanRiskType.NORMAL_RISK) return 1;

        return 0;
    }
}
