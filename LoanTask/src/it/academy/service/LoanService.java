package it.academy.service;

import it.academy.domain.*;
import it.academy.util.DateUtil;
import it.academy.util.LoanUtil;

import java.math.BigDecimal;
import java.util.*;

public class LoanService implements LoanServiceInterface {

    private LoanIterable loans;

    public LoanService(Loan[] loans) {
        this.loans = new LoanIterable(loans);
    }

    public LoanService(LoanIterable loans) {
        this.loans = loans;
    }

    public void calculateLoanTotalCost() {
        for (Loan loan : loans) {
            loan.setTotalLoanCost(LoanUtil.calculateTotalLoanCost(loan));
        }
    }

    @Override
    public BigDecimal calculateAverageLoanCost() {
        BigDecimal averageLoanCost = BigDecimal.ZERO;
        for (Loan loan : loans) {
            averageLoanCost = averageLoanCost.add(LoanUtil.calculateTotalLoanCost(loan));
        }
        return averageLoanCost.divide(new BigDecimal(loans.length), BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public BigDecimal calculateAverageLoanCost(LoanRiskType riskType) {
        BigDecimal averageLoanCost = BigDecimal.ZERO;
        for (Loan loan : findLoansByRiskType(riskType, loans)) {
            averageLoanCost = averageLoanCost.add(LoanUtil.calculateTotalLoanCost(loan));
        }
        return averageLoanCost.divide(new BigDecimal(findLoansByRiskType(riskType, loans).length), BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public BigDecimal findAverageCostOfHighRiskLoans() {
        BigDecimal averageLoanCost = BigDecimal.ZERO;
        for (Loan loan : findLoansByRiskType(LoanRiskType.HIGH_RISK, loans)) {
            averageLoanCost = averageLoanCost.add(LoanUtil.calculateTotalLoanCost(loan));
        }

        return averageLoanCost.divide(new BigDecimal(findLoansByRiskType(LoanRiskType.HIGH_RISK, loans).length), BigDecimal.ROUND_HALF_UP);
    }


    @Override
    public BigDecimal findMaximumPriceOfNonExpiredLoans() {
        BigDecimal maxPrice = null;
        for (Loan loan : loans) {
            if (maxPrice == null && LoanUtil.isValid(loan)) {
                maxPrice = loan.getPrice();
            }
            if (LoanUtil.isValid(loan) && loan.getPrice().compareTo(maxPrice) == 1) {
                maxPrice = loan.getPrice();
            }
        }
        return maxPrice;
    }

    @Override
    public List<Loan> findNormalRiskVehicleLoans() {
        return new ArrayList(Arrays.asList(findLoansByClass(findLoansByRiskType(LoanRiskType.NORMAL_RISK, loans), VehicleLoan.class)));

    }

    @Override
    public int findMaximumAgeOfLowRiskLoanedVehicles() {
        int maxAge = 0;
        int daysInYear = 365;
        for (Loan loan : findLoansByClass(findLoansByRiskType(LoanRiskType.LOW_RISK, loans), VehicleLoan.class)) {
            int newAge = (int) DateUtil.differenceInDays(new Date(),
                    ((VehicleLoan) loan).getManufactured())
                    / daysInYear;
            if (newAge > maxAge) {
                maxAge = newAge;
            }
        }
        return maxAge;
    }

    @Override
    public List<Loan> findPersonalRealEstateLoans() {
        return new ArrayList<Loan>(findLoansByRealEstatePurpose(RealEstatePurpose.PERSONAL, findLoansByClass(loans, RealEstateLoan.class)));
    }

    @Override
    public List<Loan> findExpiredHighRiskVehicleLoansOfHighestDuration() {
        int highestDuration = 0;
        List<Loan> expiredHighestRiskLoans = new ArrayList<>();
        for (Loan loan : findLoansByClass(findLoansByRiskType(LoanRiskType.HIGH_RISK, loans), VehicleLoan.class)) {
            if (!LoanUtil.isValid(loan)) {
                int duration = loan.getTermInYears();
                if (duration == highestDuration) {
                    expiredHighestRiskLoans.add(loan);
                } else if (duration > highestDuration) {
                    expiredHighestRiskLoans.removeAll(expiredHighestRiskLoans);
                    expiredHighestRiskLoans.add(loan);
                }
            }
        }

        return expiredHighestRiskLoans;
    }

    @Override
    public List<Loan> findLowRiskHarvesterLoans() {
        ArrayList<Loan> lowRiskHarvestLoans = new ArrayList<>();

        for (Loan loan : findLoansByClass(findLoansByRiskType(LoanRiskType.LOW_RISK, loans), HarvesterLoan.class)) {
            lowRiskHarvestLoans.add(loan);
        }

        return lowRiskHarvestLoans;
    }

    @Override
    public List<Loan> findExpiredLandLoansInReservation() {
        List<Loan> expiredLandInReservationLoans = new ArrayList<>();
        for (Loan loan : findLoansByClass(loans, LandLoan.class)) {
            if (!LoanUtil.isValid(loan)) {
                if (((LandLoan) loan).isInReservation()) {
                    expiredLandInReservationLoans.add(loan);
                }
            }
        }

        return expiredLandInReservationLoans;
    }

    @Override
    public List<Loan> findLoansOfHigherThanAverageDepreciation() {
        BigDecimal totalDepreciation = BigDecimal.ZERO;
        LoanIterable vehicleLoans = findLoansByClass(loans, VehicleLoan.class);
        int vehicleLoanCount = 0;

        for (Loan loan : vehicleLoans) {
            totalDepreciation = totalDepreciation.add(LoanUtil.calculateVehicleDepreciation((VehicleLoan) loan));
            vehicleLoanCount++;
        }

        BigDecimal averageDepreciation = totalDepreciation.divide(new BigDecimal(vehicleLoanCount), BigDecimal.ROUND_CEILING);
        List<Loan> vehicleOfHigherThanAverageDepreciationLoans = new ArrayList<>();

        for (Loan loan : vehicleLoans) {
            BigDecimal vehicleDepreciation = LoanUtil.calculateVehicleDepreciation((VehicleLoan) loan);
            if (vehicleDepreciation.compareTo(averageDepreciation) > 0 && vehicleDepreciation.compareTo(loan.getPrice()) < 0) {
                vehicleOfHigherThanAverageDepreciationLoans.add(loan);
            }
        }

        return vehicleOfHigherThanAverageDepreciationLoans;
    }

    @Override
    public LoanIterable findLoansByClass(LoanIterable loans, Class classType) {
        List<Loan> classLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (classType.isInstance(loan)) {
                classLoans.add(loan);
            }
        }

        return new LoanIterable(classLoans);
    }

    @Override
    public LoanIterable findLoansByRiskType(LoanRiskType riskType, LoanIterable loans) {
        List<Loan> highRiskLoans = new ArrayList<>();
        if (loans == null) {
            loans = this.loans;
        }
        for (Loan loan : loans) {
            if (loan.getRiskType() == riskType) {
                highRiskLoans.add(loan);
            }
        }
        return new LoanIterable(highRiskLoans);
    }

    @Override
    public List<Loan> findLoansByRealEstatePurpose(RealEstatePurpose purpose, LoanIterable loans) {
        List<Loan> highRiskLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (((RealEstateLoan) loan).getPurpose() == purpose) {
                highRiskLoans.add(loan);
            }
        }

        return highRiskLoans;
    }

    @Override
    public Set<String> findVehicleModels() {
        Set<String> models = new HashSet<>();

        for (Loan loan : findLoansByClass(loans, VehicleLoan.class)) {
            models.add(((VehicleLoan) loan).getModel());
        }

        return models;
    }

    @Override
    public Map<LoanRiskType, Collection<Loan>> groupLoansByRiskType() {
        Map<LoanRiskType, Collection<Loan>> groupedLoans = new TreeMap<>();

        for (Loan loan : this.loans) {
            if (!groupedLoans.containsKey(loan.getRiskType())) {
                groupedLoans.put(loan.getRiskType(), new ArrayList<Loan>());
                groupedLoans.get(loan.getRiskType()).add(loan);
            } else {
                groupedLoans.get(loan.getRiskType()).add(loan);
            }
        }
        return groupedLoans;
    }

    @Override
    public Set<Loan> prioritizeLoans() {
        Set<Loan> loans = new TreeSet<>(new LoanComparator());
        for (Loan loan : this.loans) {
            loans.add(loan);
        }
        return loans;
    }
}
