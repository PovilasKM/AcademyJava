package it.academy.initializer;

import it.academy.domain.Loan;

public interface DomainInitializer {

    Loan[] initializeLoans();

}