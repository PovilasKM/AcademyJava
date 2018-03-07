package it.academy;


import it.academy.domain.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Task4Test {
  private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

  private static final Date YESTERDAY = stringToDate("2018-03-06");
  private static final Date TODAY = stringToDate("2018-03-07");
  private static final int TERM_IN_YEARS = 1;
  private static final String LOAN = "Loan";
  private static final BigDecimal PRICE = BigDecimal.TEN;
  private static final BigDecimal INTEREST_RATE = BigDecimal.ONE;
  private static final LoanRiskType RISK_TYPE = LoanRiskType.LOW_RISK;
  private static final int MAXIMUM_AGE = 10;
  private static final String MODEL = "VAZ";
  private static final RealEstatePurpose REAL_ESTATE_PURPOSE = RealEstatePurpose.PERSONAL;
  private static final float AREA = 120f;
  private static final String VILNIUS = "Vilnius";

  public static void main(String[] args) {
    Loan loan = createLoan(TODAY, TERM_IN_YEARS, LOAN, PRICE, INTEREST_RATE, RISK_TYPE);
    VehicleLoan vehicleLoan
      = createVehicleLoan(TODAY, TERM_IN_YEARS, LOAN, PRICE, INTEREST_RATE, RISK_TYPE, YESTERDAY, MAXIMUM_AGE, MODEL);
    RealEstateLoan realEstateLoan = createRealEstateLoan(TODAY, TERM_IN_YEARS, LOAN, PRICE, INTEREST_RATE, RISK_TYPE,
      REAL_ESTATE_PURPOSE, AREA, VILNIUS);

    System.out.println("Testing equals()...");

    printEquals(loan, loan);
    printEquals(loan, vehicleLoan);
    printEquals(loan, realEstateLoan);
    printEquals(vehicleLoan, realEstateLoan);

    printEquals(loan, createLoan(TODAY, TERM_IN_YEARS, LOAN, PRICE, INTEREST_RATE, LoanRiskType.HIGH_RISK));
    printEquals(loan, createLoan(TODAY, TERM_IN_YEARS, LOAN, PRICE, BigDecimal.ZERO, RISK_TYPE));
    printEquals(loan, createLoan(TODAY, TERM_IN_YEARS, LOAN, INTEREST_RATE, INTEREST_RATE, RISK_TYPE));
    printEquals(loan, createLoan(TODAY, TERM_IN_YEARS, "Loan2", PRICE, INTEREST_RATE, RISK_TYPE));
    printEquals(loan, createLoan(TODAY, 2, LOAN, PRICE, INTEREST_RATE, RISK_TYPE));
    printEquals(loan, createLoan(YESTERDAY, TERM_IN_YEARS, LOAN, PRICE, INTEREST_RATE, RISK_TYPE));

    List<Loan> loansAsList = new ArrayList<>();
    loansAsList.add(loan);
    System.out.println(loansAsList.contains(createLoan(TODAY, TERM_IN_YEARS, LOAN, PRICE, INTEREST_RATE, RISK_TYPE)));
    System.out.println(
      loansAsList.contains(createRealEstateLoan(TODAY, TERM_IN_YEARS, LOAN, PRICE, INTEREST_RATE, RISK_TYPE, REAL_ESTATE_PURPOSE, AREA, VILNIUS)));

    System.out.println("\nTesting hashCode()...");

    Set<Loan> loansAsSet = new HashSet<>();
    loansAsSet.add(loan);
    loansAsSet.add(realEstateLoan);
    loansAsSet.add(loan);
    loansAsSet.add(realEstateLoan);
    System.out.println(loansAsSet.size());
    System.out.println(loansAsSet.contains(createLoan(TODAY, TERM_IN_YEARS, LOAN, PRICE, INTEREST_RATE, RISK_TYPE)));
    System.out.println(
      loansAsSet.contains(
        createVehicleLoan(TODAY, TERM_IN_YEARS, LOAN, PRICE, INTEREST_RATE, RISK_TYPE, YESTERDAY, MAXIMUM_AGE, MODEL)));
  }

  private static void printEquals(Loan loan1, Loan loan2) {
    System.out.println(loan1.equals(loan2) + "  " + loan2.equals(loan1));
  }

  private static Loan createLoan(Date creationDate, int termInYears, String name, BigDecimal price, BigDecimal interestRate,
                                 LoanRiskType riskType) {
    Loan loan = new Loan();
    loan.setCreationDate(creationDate);
    loan.setTermInYears(termInYears);
    loan.setName(name);
    loan.setPrice(price);
    loan.setInterestRate(interestRate);
    loan.setRiskType(riskType);
    return loan;
  }

  private static VehicleLoan createVehicleLoan(Date creationDate, int termInYears, String name, BigDecimal price,
                                               BigDecimal interestRate, LoanRiskType riskType, Date manufacturedDate,
                                               int maximumAge, String model) {
    VehicleLoan vehicleLoan = new VehicleLoan();
    vehicleLoan.setCreationDate(creationDate);
    vehicleLoan.setTermInYears(termInYears);
    vehicleLoan.setName(name);
    vehicleLoan.setPrice(price);
    vehicleLoan.setInterestRate(interestRate);
    vehicleLoan.setRiskType(riskType);
    vehicleLoan.setManufactured(manufacturedDate);
    vehicleLoan.setMaximumAge(maximumAge);
    vehicleLoan.setModel(model);
    return vehicleLoan;
  }

  private static RealEstateLoan createRealEstateLoan(Date creationDate, int termInYears, String name, BigDecimal price,
                                                     BigDecimal interestRate, LoanRiskType riskType, RealEstatePurpose purpose,
                                                     float area, String district) {
    RealEstateLoan realEstateLoan = new RealEstateLoan();
    realEstateLoan.setCreationDate(creationDate);
    realEstateLoan.setTermInYears(termInYears);
    realEstateLoan.setName(name);
    realEstateLoan.setPrice(price);
    realEstateLoan.setInterestRate(interestRate);
    realEstateLoan.setRiskType(riskType);
    realEstateLoan.setPurpose(purpose);
    realEstateLoan.setArea(area);
    realEstateLoan.setDistrict(district);
    return realEstateLoan;
  }

  private static Date stringToDate(String dateString) {
    try {
      return SIMPLE_DATE_FORMAT.parse(dateString);
    }
    catch (ParseException e) {
      return null;
    }
  }
}
