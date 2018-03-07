package it.academy.service;

import it.academy.domain.Loan;
import it.academy.domain.LoanRiskType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface LoanServiceInterface {
    List<Loan> getHighRiskLoans();

    void setHighRiskLoans(List<Loan> highRiskLoans);

    BigDecimal getAverageLoanCost();

    void setAverageLoanCost(BigDecimal averageLoanCost);

    BigDecimal calculateAverageLoanCost();

    BigDecimal getAverageLoanCost(LoanRiskType riskType);

    BigDecimal findAverageCostOfHighRiskLoans();

    BigDecimal getAverageCostOfHighRiskLoans();

    void setAverageCostOfHighRiskLoans(BigDecimal averageCostOfHighRiskLoans);

    BigDecimal getMaximumPriceOfNonExpiredLoans();

    void setMaximumPriceOfNonExpiredLoans(BigDecimal maxPriceOfNonExpiredLoans);

    BigDecimal findMaximumPriceOfNonExpiredLoans();

    List<Loan> getNormalRiskVehicleLoans();

    void setNormalRiskVehicleLoans(List<Loan> normalRiskVehicleLoans);

    List<Loan> getPersonalRealEstateLoans();

    void setPersonalRealEstateLoans(List<Loan> personalRealEstateLoans);

    List<Loan> getExpiredHighRiskVehicleLoansOfHighestDuration();

    void setExpiredHighRiskVehicleLoansOfHighestDuration(List<Loan> expiredHighRiskVehicleLoansOfHighestDuration);

    int getMaximumAgeOfLowRiskLoanedVehicles();

    void setMaximumAgeOfLowRiskLoanedVehicles(int maximumAgeOfLowRiskLoanedVehicles);

    List<Loan> findNormalRiskVehicleLoans();

    int findMaximumAgeOfLowRiskLoanedVehicles();

    List<Loan> findPersonalRealEstateLoans();

    List<Loan> findExpiredHighRiskVehicleLoansOfHighestDuration();

    List<Loan> getLowRiskHarvesterLoans();

    List<Loan> getExpiredLandLoansInReservation();

    List<Loan> getLoansOfHigherThanAverageDepreciation();

    void setLowRiskHarvesterLoans(List<Loan> lowRiskHarvesterLoans);

    void setExpiredLandLoansInReservation(List<Loan> expiredLandLoansInReservation);

    void setLoansOfHigherThanAverageDepreciation(List<Loan> loansOfHigherThanAverageDepreciation);

    List<Loan> findLowRiskHarvesterLoans();

    List<Loan> findExpiredLandLoansInReservation();

    List<Loan> findLoansOfHigherThanAverageDepreciation();

    Loan[] findLoansByRiskType(LoanRiskType riskType, Loan[] loans);
}
