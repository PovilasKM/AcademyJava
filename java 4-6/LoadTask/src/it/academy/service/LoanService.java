package it.academy.service;

import it.academy.domain.*;
import it.academy.util.DateUtil;
import it.academy.util.LoanUtil;

import java.math.BigDecimal;
import java.util.*;

public class LoanService implements LoanServiceInterface {

    private Loan[] loans;

    private List<Loan> highRiskLoans;
    private List<Loan> normalRiskVehicleLoans;
    private List<Loan> personalRealEstateLoans;
    private List<Loan> expiredHighRiskVehicleLoansOfHighestDuration;

    private List<Loan> lowRiskHarvesterLoans;
    private List<Loan> expiredLandLoansInReservation;
    private List<Loan> loansOfHigherThanAverageDepreciation;

    private BigDecimal averageLoanCost;
    private BigDecimal averageCostOfHighRiskLoans;
    private BigDecimal maximumPriceOfNonExpiredLoans;

    private int maximumAgeOfLowRiskLoanedVehicles;

    public LoanService(Loan[] loans) {
        this.loans = loans;
    }

    @Override
    public List<Loan> getHighRiskLoans() {
        return highRiskLoans;
    }

    @Override
    public void setHighRiskLoans(List<Loan> highRiskLoans) {
        this.highRiskLoans = highRiskLoans;
    }

    @Override
    public BigDecimal getAverageLoanCost() {
        return averageLoanCost;
    }

    @Override
    public void setAverageLoanCost(BigDecimal averageLoanCost) {
        this.averageLoanCost = averageLoanCost;
    }

    @Override
    public BigDecimal calculateAverageLoanCost() {
        BigDecimal averageLoanCost = new BigDecimal(0);
        for (Loan loan : loans) {
            averageLoanCost = averageLoanCost.add(loan.getTotalLoanCost());
        }
        return averageLoanCost.divide(new BigDecimal(loans.length), BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public BigDecimal getAverageLoanCost(LoanRiskType riskType) {
        Loan[] specificLoans = this.findLoansByRiskType(riskType, loans);
        BigDecimal averageLoanCost = new BigDecimal("0");
        for (Loan loan : specificLoans) {
            averageLoanCost = averageLoanCost.add(loan.getTotalLoanCost());
        }

        return averageLoanCost.divide(new BigDecimal(specificLoans.length), BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public BigDecimal findAverageCostOfHighRiskLoans() {
        Loan[] highRiskLoans = this.findLoansByRiskType(LoanRiskType.HIGH_RISK, loans);
        BigDecimal averageLoanCost = new BigDecimal("0");
        for (Loan loan : highRiskLoans) {
            averageLoanCost = averageLoanCost.add(loan.getTotalLoanCost());
        }

        return averageLoanCost.divide(new BigDecimal(highRiskLoans.length), BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public BigDecimal getAverageCostOfHighRiskLoans() {
        return averageCostOfHighRiskLoans;
    }

    @Override
    public void setAverageCostOfHighRiskLoans(BigDecimal averageCostOfHighRiskLoans) {
        this.averageCostOfHighRiskLoans = averageCostOfHighRiskLoans;
    }

    @Override
    public BigDecimal getMaximumPriceOfNonExpiredLoans() {
        return maximumPriceOfNonExpiredLoans;
    }

    @Override
    public void setMaximumPriceOfNonExpiredLoans(BigDecimal maxPriceOfNonExpiredLoans) {
        this.maximumPriceOfNonExpiredLoans = maxPriceOfNonExpiredLoans;
    }

    @Override
    public BigDecimal findMaximumPriceOfNonExpiredLoans() {
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

    @Override
    public List<Loan> getNormalRiskVehicleLoans() {
        return normalRiskVehicleLoans;
    }

    @Override
    public void setNormalRiskVehicleLoans(List<Loan> normalRiskVehicleLoans) {
        this.normalRiskVehicleLoans = normalRiskVehicleLoans;
    }

    @Override
    public List<Loan> getPersonalRealEstateLoans() {
        return personalRealEstateLoans;
    }

    @Override
    public void setPersonalRealEstateLoans(List<Loan> personalRealEstateLoans) {
        this.personalRealEstateLoans = personalRealEstateLoans;
    }

    @Override
    public List<Loan> getExpiredHighRiskVehicleLoansOfHighestDuration() {
        return expiredHighRiskVehicleLoansOfHighestDuration;
    }

    @Override
    public void setExpiredHighRiskVehicleLoansOfHighestDuration(List<Loan> expiredHighRiskVehicleLoansOfHighestDuration) {
        this.expiredHighRiskVehicleLoansOfHighestDuration = expiredHighRiskVehicleLoansOfHighestDuration;
    }

    @Override
    public int getMaximumAgeOfLowRiskLoanedVehicles() {
        return maximumAgeOfLowRiskLoanedVehicles;
    }

    @Override
    public void setMaximumAgeOfLowRiskLoanedVehicles(int maximumAgeOfLowRiskLoanedVehicles) {
        this.maximumAgeOfLowRiskLoanedVehicles = maximumAgeOfLowRiskLoanedVehicles;
    }

    @Override
    public List<Loan> findNormalRiskVehicleLoans() {
        return new ArrayList(Arrays.asList(findLoansByClass(findLoansByRiskType(LoanRiskType.NORMAL_RISK, loans), VehicleLoan.class)));

    }

    @Override
    public int findMaximumAgeOfLowRiskLoanedVehicles() {
        int maxAge = 0;
        for (Loan loan : findLoansByClass(findLoansByRiskType(LoanRiskType.LOW_RISK, loans), VehicleLoan.class)) {
            int newAge = (int) DateUtil.differenceInDays(new Date(),
                    ((VehicleLoan) loan).getManufactured())
                    / 365;
            if (newAge > maxAge) {
                maxAge = newAge;
            }
        }
        return maxAge;
    }

    @Override
    public List<Loan> findPersonalRealEstateLoans() {
        return new ArrayList<Loan>(Arrays.asList(findLoansByRealEstatePurpose(RealEstatePurpose.PERSONAL, findLoansByClass(loans, RealEstateLoan.class))));
    }

    @Override
    public List<Loan> findExpiredHighRiskVehicleLoansOfHighestDuration() { // todo: redo
        int highestDuration = 0;
        int loanCount = 0;
        for (Loan loan : findLoansByClass(findLoansByRiskType(LoanRiskType.HIGH_RISK, loans), VehicleLoan.class)) {
            if (!loan.isValid()) {
                int duration = loan.getTermInYears();
                if (duration == highestDuration) {
                    loanCount++;
                } else if (duration > highestDuration) {
                    loanCount = 1;
                    highestDuration = duration;
                }
            }
        }
        Loan[] expiredHighestRiskLoans = new Loan[loanCount];
        loanCount = 0;

        for (Loan loan : loans) {
            if (!loan.isValid()) {
                if (loan.getTermInYears() == highestDuration) {
                    expiredHighestRiskLoans[loanCount] = loan;
                    loanCount++;
                }
            }
        }

        return new ArrayList<Loan>(Arrays.asList(expiredHighestRiskLoans));
    }

    @Override
    public List<Loan> getLowRiskHarvesterLoans() {
        return lowRiskHarvesterLoans;
    }

    @Override
    public void setLowRiskHarvesterLoans(List<Loan> lowRiskHarvesterLoans) {
        this.lowRiskHarvesterLoans = lowRiskHarvesterLoans;
    }

    public List<Loan> getExpiredLandLoansInReservation() {
        return expiredLandLoansInReservation;
    }

    public void setExpiredLandLoansInReservation(List<Loan> expiredLandLoansInReservation) {
        this.expiredLandLoansInReservation = expiredLandLoansInReservation;
    }

    @Override
    public List<Loan> getLoansOfHigherThanAverageDepreciation() {
        return loansOfHigherThanAverageDepreciation;
    }

    @Override
    public void setLoansOfHigherThanAverageDepreciation(List<Loan> loansOfHigherThanAverageDepreciation) {
        this.loansOfHigherThanAverageDepreciation = loansOfHigherThanAverageDepreciation;
    }

    @Override
    public List<Loan> findLowRiskHarvesterLoans() {
        return new ArrayList<Loan>(Arrays.asList(findLoansByClass(findLoansByRiskType(LoanRiskType.LOW_RISK, loans), HarvesterLoan.class)));
    }

    @Override
    public List<Loan> findExpiredLandLoansInReservation() {
        int expiredLandLoanInReservationCount = 0;
        for (Loan loan : findLoansByClass(loans, LandLoan.class)) {
            if (!loan.isValid()) {
                if (((LandLoan) loan).isInReservation()) {
                    expiredLandLoanInReservationCount++;
                }
            }
        }

        Loan[] expiredLandInReservationLoans = new Loan[expiredLandLoanInReservationCount];
        expiredLandLoanInReservationCount = 0;

        for (Loan loan : loans) {
            if (!loan.isValid()) {
                if (loan instanceof LandLoan) {
                    if (((LandLoan) loan).isInReservation()) {
                        expiredLandInReservationLoans[expiredLandLoanInReservationCount] = loan;
                        expiredLandLoanInReservationCount++;
                    }
                }
            }
        }

        return new ArrayList<Loan>(Arrays.asList(expiredLandInReservationLoans));
    }

    @Override
    public ArrayList<Loan> findLoansOfHigherThanAverageDepreciation() {
        BigDecimal totalDepreciation = new BigDecimal(0);
        int vehicleLoanCount = 0;

        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan) {
                totalDepreciation.add(LoanUtil.calculateVehicleDepreciation((VehicleLoan) loan));
                vehicleLoanCount++;
            }
        }

        int vehicleOfHigherThanAverageDepreciationcount = 0;
        BigDecimal averageDepreciation = totalDepreciation.divide(new BigDecimal(vehicleLoanCount));

        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan) {
                if (LoanUtil.calculateVehicleDepreciation((VehicleLoan) loan).compareTo(averageDepreciation) == 1) {
                    vehicleOfHigherThanAverageDepreciationcount++;
                }
            }
        }

        Loan[] vehicleOfHigherThanAverageDepreciationLoans = new Loan[vehicleOfHigherThanAverageDepreciationcount];
        vehicleOfHigherThanAverageDepreciationcount = 0;

        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan) {
                if (LoanUtil.calculateVehicleDepreciation((VehicleLoan) loan).compareTo(averageDepreciation) == 1) {
                    vehicleOfHigherThanAverageDepreciationLoans[vehicleOfHigherThanAverageDepreciationcount] = loan;
                    vehicleOfHigherThanAverageDepreciationcount++;
                }
            }
        }

        return new ArrayList<Loan>(Arrays.asList(vehicleOfHigherThanAverageDepreciationLoans));
    }

    public Loan[] findLoansByClass(Loan[] loans, Class classType) {
        int classCount = 0;
        for (Loan loan : loans) {
            if (classType.isInstance(loan)) {
                classCount++;
            }
        }

        Loan[] classLoans = new Loan[classCount];
        classCount = 0;

        for (Loan loan : loans) {
            if (classType.isInstance(loan)) {
                classLoans[classCount] = loan;
                classCount++;
            }
        }

        return classLoans;
    }

    public Loan[] findLoansByRiskType(LoanRiskType riskType, Loan[] loans) {
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

    public Loan[] findLoansByRealEstatePurpose(RealEstatePurpose purpose, Loan[] loans) {
        int highRiskLoanCount = 0;
        Loan[] highRiskLoans;
        for (Loan loan : loans) {
            if (((RealEstateLoan) loan).getPurpose() == purpose) {
                highRiskLoanCount++;
            }
        }

        highRiskLoans = new Loan[highRiskLoanCount];
        highRiskLoanCount = 0;

        for (Loan loan : loans) {
            if (((RealEstateLoan) loan).getPurpose() == purpose) {
                highRiskLoans[highRiskLoanCount] = loan;
                highRiskLoanCount++;
            }
        }

        return highRiskLoans;
    }

    public Set<String> findVehicleModels() {
        Set<String> models = new HashSet<>();

        for (Loan loan : findLoansByClass(loans, VehicleLoan.class)) {
            models.add(((VehicleLoan) loan).getModel());
        }

        return models;
    }

    public Map<LoanRiskType, Collection<Loan>> groupLoansByRiskType() {
        Map<LoanRiskType, Collection<Loan>> loans = new TreeMap<>();

        for (Loan loan : this.loans) {
            if (!loans.containsKey(loan.getRiskType())) {
                loans.put(loan.getRiskType(), new ArrayList<Loan>());
                loans.get(loan.getRiskType()).add(loan);
            } else {
                loans.get(loan.getRiskType()).add(loan);
            }
        }
        return loans;
    }

    public Set<Loan> prioritizeLoans() {
        Set<Loan> loans = new HashSet<>();

        for (Loan loan : this.loans) {
            loans.add(loan);
        }

        Collections.sort(loans, new LoanComparator());

        return loans;
    }
}
