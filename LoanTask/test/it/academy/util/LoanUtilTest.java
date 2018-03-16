package it.academy.util;

import it.academy.domain.Loan;
import it.academy.domain.LoanRiskType;
import it.academy.domain.VehicleLoan;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

public class LoanUtilTest {

    Loan[] loans;

    @Before
    public void setup(){
        loans = new Loan[11];

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

        loans[10] = new Loan();

        loans[10] = new VehicleLoan();

        loans[10].setCreationDate(DateUtil.getDateFromString("2014-01-01"));
        loans[10].setInterestRate(new BigDecimal(11.0));
        loans[10].setPrice(new BigDecimal(19000));
        loans[10].setRiskType(LoanRiskType.HIGH_RISK);
        loans[10].setTermInYears(4);
        ((VehicleLoan)loans[10]).setManufactured(DateUtil.getDateFromString("2014-01-01"));
        ((VehicleLoan)loans[10]).setMaximumAge(5);
        ((VehicleLoan)loans[10]).setModel("ZAZ 3000");
        loans[10].setName("VehicleLoan 6");
    }

    @Test
    public void calculateLoanTotalCostTest(){
        assertEquals(new BigDecimal(1150.00).setScale(2, RoundingMode.HALF_UP), LoanUtil.calculateTotalLoanCost(loans[0]));
    }

    @Test
    public void calculateInterestTest(){
        assertEquals(new BigDecimal(150.00).setScale(2, RoundingMode.HALF_UP), LoanUtil.calculateInterest(loans[0]));
    }

    @Test
    public void calculateInterestWithZeroTest(){
        loans[0].setInterestRate(BigDecimal.ZERO);
        assertEquals(new BigDecimal(0).setScale(2, RoundingMode.HALF_UP), LoanUtil.calculateInterest(loans[0]));
    }

    @Test
    public void isValidTest(){
        assertFalse(LoanUtil.isValid(loans[0]));
    }

    @Test
    public void calculateVehicleDepreciationTest(){
        assertEquals(new BigDecimal(15200), LoanUtil.calculateVehicleDepreciation((VehicleLoan)loans[10]));
    }

    @Test(expected = NullPointerException.class)
    public void calculateInterestWithNullTest(){
        LoanUtil.calculateInterest(null);
    }

}