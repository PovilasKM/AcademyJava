package it.academy.initializer;

import it.academy.domain.Loan;
import it.academy.domain.LoanRiskType;
import it.academy.util.DateUtil;

import java.math.BigDecimal;

public class Task1DomainInitializer implements DomainInitializer {


    @Override
    public Loan[] initializeLoans() {
        Loan[] loans = new Loan[10];

        loans[0] = new Loan();

        loans[0].setCreationDate(DateUtil.getDateFromString("2010-01-01"));
        loans[0].setInterestRate(new BigDecimal(15.0));
        loans[0].setPrice(new BigDecimal(1000));
        loans[0].setRiskType(LoanRiskType.HIGH_RISK);
        loans[0].setTermInYears(2);
        loans[0].setName("Loan 0");

        loans[1] = new Loan();

        loans[1].setCreationDate(DateUtil.getDateFromString("2017-01-01"));
        loans[1].setInterestRate(new BigDecimal(17.0));
        loans[1].setPrice(new BigDecimal(10000));
        loans[1].setRiskType(LoanRiskType.HIGH_RISK);
        loans[1].setTermInYears(5);
        loans[1].setName("Loan 1");

        loans[2] = new Loan();

        loans[2].setCreationDate(DateUtil.getDateFromString("2010-01-01"));
        loans[2].setInterestRate(new BigDecimal(15.0));
        loans[2].setPrice(new BigDecimal(2000000));
        loans[2].setRiskType(LoanRiskType.HIGH_RISK);
        loans[2].setTermInYears(5);
        loans[2].setName("Loan 2");

        loans[3] = new Loan();

        loans[3].setCreationDate(DateUtil.getDateFromString("2015-01-01"));
        loans[3].setInterestRate(new BigDecimal(12.0));
        loans[3].setPrice(new BigDecimal(1900));
        loans[3].setRiskType(LoanRiskType.LOW_RISK);
        loans[3].setTermInYears(2);
        loans[3].setName("Loan 3");

        loans[4] = new Loan();

        loans[4].setCreationDate(DateUtil.getDateFromString("2014-01-01"));
        loans[4].setInterestRate(new BigDecimal(20.0));
        loans[4].setPrice(new BigDecimal(500));
        loans[4].setRiskType(LoanRiskType.LOW_RISK);
        loans[4].setTermInYears(1);
        loans[4].setName("Loan 4");

        loans[5] = new Loan();

        loans[5].setCreationDate(DateUtil.getDateFromString("2013-01-01"));
        loans[5].setInterestRate(new BigDecimal(15.0));
        loans[5].setPrice(new BigDecimal(3000));
        loans[5].setRiskType(LoanRiskType.NORMAL_RISK);
        loans[5].setTermInYears(2);
        loans[5].setName("Loan 5");

        loans[6] = new Loan();

        loans[6].setCreationDate(DateUtil.getDateFromString("2014-01-01"));
        loans[6].setInterestRate(new BigDecimal(11.0));
        loans[6].setPrice(new BigDecimal(1000000));
        loans[6].setRiskType(LoanRiskType.NORMAL_RISK);
        loans[6].setTermInYears(10);
        loans[6].setName("Loan 6");

        loans[7] = new Loan();

        loans[7].setCreationDate(DateUtil.getDateFromString("2015-01-01"));
        loans[7].setInterestRate(new BigDecimal(15.0));
        loans[7].setPrice(new BigDecimal(4000));
        loans[7].setRiskType(LoanRiskType.NORMAL_RISK);
        loans[7].setTermInYears(2);
        loans[7].setName("Loan 7");

        loans[8] = new Loan();

        loans[8].setCreationDate(DateUtil.getDateFromString("2016-01-01"));
        loans[8].setInterestRate(new BigDecimal(5.0));
        loans[8].setPrice(new BigDecimal(1000));
        loans[8].setRiskType(LoanRiskType.NORMAL_RISK);
        loans[8].setTermInYears(2);
        loans[8].setName("Loan 8");

        loans[9] = new Loan();

        loans[9].setCreationDate(DateUtil.getDateFromString("2017-01-01"));
        loans[9].setInterestRate(new BigDecimal(19.0));
        loans[9].setPrice(new BigDecimal(2500));
        loans[9].setRiskType(LoanRiskType.NORMAL_RISK);
        loans[9].setTermInYears(3);
        loans[9].setName("Loan 9");

        return loans;
    }
}
