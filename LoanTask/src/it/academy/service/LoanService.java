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

    //The idea of the last assignment (Task 7) was to use this constructor instead of one above.
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

        //You can re-use "calculateAverageLoanCost" here like this:
        //return calculateAverageLoanCost(LoanRiskType.HIGH_RISK);
    }


    @Override
    public BigDecimal findMaximumPriceOfNonExpiredLoans() {
        BigDecimal maxPrice = null;
        for (Loan loan : loans) {
            //This can be removed, if you would assign "maxPrice" some min. default value. In our case, "price" can never be less than 0.
            if (maxPrice == null && LoanUtil.isValid(loan)) {
                maxPrice = loan.getPrice();
            }
            //Never rely on a comparator returning you an exact value (exception - 0). See what Intellij is suggesting you.
            if (LoanUtil.isValid(loan) && loan.getPrice().compareTo(maxPrice) == 1) {
                maxPrice = loan.getPrice();
            }
        }
        return maxPrice;
    }

    @Override
    public List<Loan> findNormalRiskVehicleLoans() {
        //First of all "new ArrayList" is missing a "diamond" or type
        //Next, once you add it, you will notice that "Arrays.asList" result type is not compatible with one "ArrayList"
        // constructor can take.
        //The problem here: your "findLoansByClass" method is returning "LoanIterable". I think it would be better if it returned a
        // collection/list.
        return new ArrayList(Arrays.asList(findLoansByClass(findLoansByRiskType(LoanRiskType.NORMAL_RISK, loans), VehicleLoan.class)));

    }

    @Override
    public int findMaximumAgeOfLowRiskLoanedVehicles() {
        int maxAge = 0;
        //This is a constant, I would recommend declaring it as one (hint: "private static final int ...")
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
        //Since Java 1.7, "<Loan>" -> "<>"
        return new ArrayList<Loan>(findLoansByRealEstatePurpose(RealEstatePurpose.PERSONAL, findLoansByClass(loans, RealEstateLoan.class)));
    }

    @Override
    public List<Loan> findExpiredHighRiskVehicleLoansOfHighestDuration() {
        int highestDuration = 0;
        //"expiredHighestRiskLoans" can be of "List<Loan>" type.
        ArrayList<Loan> expiredHighestRiskLoans = new ArrayList<>();
        for (Loan loan : findLoansByClass(findLoansByRiskType(LoanRiskType.HIGH_RISK, loans), VehicleLoan.class)) {
            if (!LoanUtil.isValid(loan)) {
                int duration = loan.getTermInYears();
                if (duration == highestDuration) {
                    expiredHighestRiskLoans.add(loan);
                } else if (duration > highestDuration) {
                    //"Interesting" solution
                    //Just a thought, first you could find what that "highestDuration" is and then add Loans to
                    // "expiredHighestRiskLoans" in a separate loop.
                    //But I guess this also works.
                    expiredHighestRiskLoans.removeAll(expiredHighestRiskLoans);
                    expiredHighestRiskLoans.add(loan);
                }
            }
        }

        return expiredHighestRiskLoans;
    }

    @Override
    public List<Loan> findLowRiskHarvesterLoans() {
        //Use less concrete types for collections
        ArrayList<Loan> lowRiskHarvestLoans = new ArrayList<>();

        for (Loan loan : findLoansByClass(findLoansByRiskType(LoanRiskType.LOW_RISK, loans), HarvesterLoan.class)) {
            lowRiskHarvestLoans.add(loan);
        }

        return lowRiskHarvestLoans;
    }

    @Override
    public List<Loan> findExpiredLandLoansInReservation() {
        //Use less concrete types for collections
        ArrayList<Loan> expiredLandInReservationLoans = new ArrayList<>();
        for (Loan loan : findLoansByClass(loans, LandLoan.class)) {
            //You can put "!LoanUtil.isValid(loan)" and "((LandLoan) loan).isInReservation()" into a single if statement.
            if (!LoanUtil.isValid(loan)) {
                if (((LandLoan) loan).isInReservation()) {
                    expiredLandInReservationLoans.add(loan);
                }
            }
        }

        return expiredLandInReservationLoans;
    }

    @Override
    //Use less concrete types for collections
    public ArrayList<Loan> findLoansOfHigherThanAverageDepreciation() {
        BigDecimal totalDepreciation = BigDecimal.ZERO;
        LoanIterable vehicleLoans = findLoansByClass(loans, VehicleLoan.class);
        int vehicleLoanCount = 0;

        for (Loan loan : vehicleLoans) {
            totalDepreciation = totalDepreciation.add(LoanUtil.calculateVehicleDepreciation((VehicleLoan) loan));
            vehicleLoanCount++;
        }

        BigDecimal averageDepreciation = totalDepreciation.divide(new BigDecimal(vehicleLoanCount), BigDecimal.ROUND_CEILING);
        //Use less concrete types for collections
        ArrayList<Loan> vehicleOfHigherThanAverageDepreciationLoans = new ArrayList<>();

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
        ArrayList<Loan> classLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (classType.isInstance(loan)) {
                classLoans.add(loan);
            }
        }

        return new LoanIterable(classLoans);
    }

    @Override
    public LoanIterable findLoansByRiskType(LoanRiskType riskType, LoanIterable loans) {
        ArrayList<Loan> highRiskLoans = new ArrayList<>();
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
        //Use less concrete types for collections
        ArrayList<Loan> highRiskLoans = new ArrayList<>();
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
        //Remember, use more descriptive names for your collections. "loans" is no good.
        Map<LoanRiskType, Collection<Loan>> loans = new TreeMap<>();

        for (Loan loan : this.loans) {
            //You actually can simplify your loop body to this:
            //if (!loans.containsKey(loan.getRiskType())) {
            //    loans.put(loan.getRiskType(), new ArrayList<Loan>());
            //}
            //loans.get(loan.getRiskType()).add(loan);


            if (!loans.containsKey(loan.getRiskType())) {
                loans.put(loan.getRiskType(), new ArrayList<Loan>());
                loans.get(loan.getRiskType()).add(loan);
            } else {
                loans.get(loan.getRiskType()).add(loan);
            }
        }
        return loans;
    }

    @Override
    public Set<Loan> prioritizeLoans() {
        //Remember, use more descriptive names for your collections. "loans" is no good.
        Set<Loan> loans = new TreeSet<>(new LoanComparator());
        for (Loan loan : this.loans) {
            loans.add(loan);
        }
        return loans;
    }
}
