package it.academy.domain;

import java.util.Iterator;
import java.util.List;

public class LoanIterable implements Iterable<Loan>{

    private Loan[] loans;
    public int length;

    public LoanIterable(Loan[] loans) {
        this.loans = loans;
        length = loans.length;
    }

    public LoanIterable(List<Loan> loans){
        this.loans = loans.toArray(new Loan[loans.size()]);
        length = loans.size();
    }


    @Override
    public Iterator<Loan> iterator() {
        Iterator<Loan> loanIterator = new Iterator<Loan>() {

            private int iteratorCounter = 0;

            @Override
            public void remove() {

            }

            @Override
            public boolean hasNext() {
                return iteratorCounter < loans.length;
            }

            @Override
            public Loan next() {
                return loans[iteratorCounter++];
            }
        };

        return loanIterator;
    }
}
