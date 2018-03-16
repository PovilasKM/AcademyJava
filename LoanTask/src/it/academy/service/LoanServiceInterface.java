package it.academy.service;

import it.academy.domain.Loan;
import it.academy.domain.LoanIterable;
import it.academy.domain.LoanRiskType;
import it.academy.domain.RealEstatePurpose;

import java.math.BigDecimal;
import java.util.*;

public interface LoanServiceInterface {
    BigDecimal calculateAverageLoanCost();

    BigDecimal calculateAverageLoanCost(LoanRiskType riskType);

    BigDecimal findAverageCostOfHighRiskLoans();

    BigDecimal findMaximumPriceOfNonExpiredLoans();

    List<Loan> findNormalRiskVehicleLoans();

    int findMaximumAgeOfLowRiskLoanedVehicles();

    List<Loan> findPersonalRealEstateLoans();

    List<Loan> findExpiredHighRiskVehicleLoansOfHighestDuration();

    List<Loan> findLowRiskHarvesterLoans();

    List<Loan> findExpiredLandLoansInReservation();

    List<Loan> findLoansOfHigherThanAverageDepreciation();

    LoanIterable findLoansByClass(LoanIterable loans, Class classType);

    LoanIterable findLoansByRiskType(LoanRiskType riskType, LoanIterable loans);

    List<Loan> findLoansByRealEstatePurpose(RealEstatePurpose purpose, LoanIterable loans);

    Set<String> findVehicleModels();

    Map<LoanRiskType, Collection<Loan>> groupLoansByRiskType();

    Set<Loan> prioritizeLoans();
}
