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

    //I don't think it is the best idea using "LoanIterable" for converting Loan collections to arrays and then back to collections.
    // "Butter on top of a butter" :)
    // You should be fine passing collections directly.
    public LoanIterable(List<Loan> loans){
        this.loans = loans.toArray(new Loan[loans.size()]);
        length = loans.size();
    }


    @Override
    public Iterator<Loan> iterator() {
        //As Intellij is already telling you, this local variable could be inlined (aka. returned directly).
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
