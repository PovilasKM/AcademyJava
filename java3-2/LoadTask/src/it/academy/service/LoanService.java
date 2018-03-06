package it.academy.service;

import it.academy.domain.Loan;
import it.academy.domain.LoanRiskType;
import it.academy.domain.RealEstateLoan;
import it.academy.domain.VehicleLoan;
import it.academy.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

public class LoanService {

    private Loan[] loans;
    private Loan[] highRiskLoans;
    private Loan[] normalRiskVehicleLoans;
    private Loan[] personalRealEstateLoans;
    private Loan[] expiredHighRiskVehicleLoansOfHighestDuration;

    private BigDecimal averageLoanCost;
    private BigDecimal averageCostOfHighRiskLoans;
    private BigDecimal maximumPriceOfNonExpiredLoans;

    private int maximumAgeOfLowRiskLoanedVehicles;

    public LoanService(Loan[] loans) {
        this.loans = loans;
    }

    public Loan[] getHighRiskLoans() {
        return highRiskLoans;
    }

    public void setHighRiskLoans(Loan[] highRiskLoans) {
        this.highRiskLoans = highRiskLoans;
    }

    public BigDecimal getAverageLoanCost() {
        return averageLoanCost;
    }

    public void setAverageLoanCost(BigDecimal averageLoanCost) {
        this.averageLoanCost = averageLoanCost;
    }

    public BigDecimal calculateAverageLoanCost() {
        BigDecimal averageLoanCost = new BigDecimal(0);
        for (Loan loan : loans) {
            averageLoanCost = averageLoanCost.add(loan.getTotalLoanCost());
        }
        System.out.println("aaaaaa " + averageLoanCost);
        return averageLoanCost.divide(new BigDecimal(loans.length), BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getAverageLoanCost(LoanRiskType riskType) {
        Loan[] specificLoans = this.calculateSpecificRiskLoans(riskType);
        BigDecimal averageLoanCost = new BigDecimal("0");
        for (Loan loan : specificLoans) {
            averageLoanCost = averageLoanCost.add(loan.getTotalLoanCost());
        }

        return averageLoanCost.divide(new BigDecimal(specificLoans.length), BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal calculateAverageCosttOfHighRiskLoans() {
        Loan[] highRiskLoans = this.calculateSpecificRiskLoans(LoanRiskType.HIGH_RISK);
        BigDecimal averageLoanCost = new BigDecimal("0");
        for (Loan loan : highRiskLoans) {
            averageLoanCost = averageLoanCost.add(loan.getTotalLoanCost());
        }

        return averageLoanCost.divide(new BigDecimal(highRiskLoans.length), BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getAverageCostOfHighRiskLoans() {
        return averageCostOfHighRiskLoans;
    }

    public void setAverageCostOfHighRiskLoans(BigDecimal averageCostOfHighRiskLoans) {
        this.averageCostOfHighRiskLoans = averageCostOfHighRiskLoans;
    }

    public BigDecimal getMaximumPriceOfNonExpiredLoans() {
        return maximumPriceOfNonExpiredLoans;
    }

    public void setMaximumPriceOfNonExpiredLoans(BigDecimal maxPriceOfNonExpiredLoans) {
        this.maximumPriceOfNonExpiredLoans = maxPriceOfNonExpiredLoans;
    }

    public BigDecimal calculateMaximumPriceOfNonExpiredLoans() {
        BigDecimal maxPrice = null;
        for (Loan loan : loans) {
            if (maxPrice == null && loan.isValid()) {
                maxPrice = loan.getPrice();
            }
            if (loan.isValid() && loan.getPrice().compareTo(maxPrice) == 1) {
                maxPrice = loan.getPrice();
            }
        }
        return maxPrice;
    }

    public Loan[] calculateSpecificRiskLoans(LoanRiskType riskType) {
        int highRiskLoanCount = 0;
        Loan[] highRiskLoans;
        for (Loan loan : loans) {
            if (loan.getRiskType() == riskType) {
                highRiskLoanCount++;
            }
        }

        highRiskLoans = new Loan[highRiskLoanCount];
        highRiskLoanCount = 0;

        for (Loan loan : loans) {
            if (loan.getRiskType() == riskType) {
                highRiskLoans[highRiskLoanCount] = loan;
                highRiskLoanCount++;
            }
        }

        return highRiskLoans;
    }

    public Loan[] getNormalRiskVehicleLoans() {
        return normalRiskVehicleLoans;
    }

    public void setNormalRiskVehicleLoans(Loan[] normalRiskVehicleLoans) {
        this.normalRiskVehicleLoans = normalRiskVehicleLoans;
    }

    public Loan[] getPersonalRealEstateLoans() {
        return personalRealEstateLoans;
    }

    public void setPersonalRealEstateLoans(Loan[] personalRealEstateLoans) {
        this.personalRealEstateLoans = personalRealEstateLoans;
    }

    public Loan[] getExpiredHighRiskVehicleLoansOfHighestDuration() {
        return expiredHighRiskVehicleLoansOfHighestDuration;
    }

    public void setExpiredHighRiskVehicleLoansOfHighestDuration(Loan[] expiredHighRiskVehicleLoansOfHighestDuration) {
        this.expiredHighRiskVehicleLoansOfHighestDuration = expiredHighRiskVehicleLoansOfHighestDuration;
    }

    public int getMaximumAgeOfLowRiskLoanedVehicles() {
        return maximumAgeOfLowRiskLoanedVehicles;
    }

    public void setMaximumAgeOfLowRiskLoanedVehicles(int maximumAgeOfLowRiskLoanedVehicles) {
        this.maximumAgeOfLowRiskLoanedVehicles = maximumAgeOfLowRiskLoanedVehicles;
    }

    public Loan[] calculateNormalRiskVehicleLoans() {
        int normalVehicleRiskCount = 0;
        Loan[] normalRiskVehicleLoans;
        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan) {
                if (((VehicleLoan) loan).isRiskNormal()) {
                    normalVehicleRiskCount++;
                }

            }
        }

        normalRiskVehicleLoans = new Loan[normalVehicleRiskCount];
        normalVehicleRiskCount = 0;

        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan) {
                if (((VehicleLoan) loan).isRiskNormal()) {
                    normalRiskVehicleLoans[normalVehicleRiskCount] = loan;
                    normalVehicleRiskCount++;
                }
            }
        }

        return normalRiskVehicleLoans;
    }

    public int calculateMaximumAgeOfLowRiskLoanedVehicles() {
        int maxAge = 0;
        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan) {
                if (loan.getRiskType() == LoanRiskType.LOW_RISK) {
                    int newAge = (int) DateUtil.differenceInDays((((VehicleLoan) loan).getManufactured()),
                            new Date()) / 365;
                    if (newAge > maxAge) {
                        maxAge = newAge;
                    }
                }
            }
        }
        return maxAge;
    }

    public Loan[] calculatePersonalRealEstateLoans() {
        int PersonalRealEstateCount = 0;
        Loan[] personalRealEstateLoans;
        for (Loan loan : loans) {
            if (loan instanceof RealEstateLoan) {
                PersonalRealEstateCount++;
            }
        }

        personalRealEstateLoans = new Loan[PersonalRealEstateCount];
        PersonalRealEstateCount = 0;

        for (Loan loan : loans) {
            if (loan instanceof RealEstateLoan) {
                personalRealEstateLoans[PersonalRealEstateCount] = loan;
                PersonalRealEstateCount++;
            }
        }

        return personalRealEstateLoans;
    }

    public Loan[] calculateExpiredHighRiskVehicleLoansOfHighestDuration() {
        int highestDuration = 0;
        int loanCount = 0;
        for (Loan loan : loans) {
            if (!loan.isValid()) {
                if (loan.calculateLoanDuration() == highestDuration) {
                    loanCount++;
                } else if (loan.calculateLoanDuration() > highestDuration) {
                    loanCount = 1;
                    highestDuration = loan.calculateLoanDuration();
                }
            }
        }

        Loan[] expiredHighestRiskLoans = new Loan[loanCount];
        loanCount = 0;

        for (Loan loan : loans) {
            if (loan.calculateLoanDuration() == highestDuration) {
                expiredHighestRiskLoans[loanCount] = loan;
                loanCount++;
            }
        }

        return expiredHighestRiskLoans;
    }

}
