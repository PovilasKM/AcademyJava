package it.academy.util;

import it.academy.domain.Loan;
import it.academy.domain.VehicleLoan;

import java.math.BigDecimal;
import java.util.Date;

public class LoanUtil {

    public static BigDecimal calculateVehicleDepreciation(VehicleLoan loan) {
        BigDecimal vehicleDepreciation;

        vehicleDepreciation = loan.getPrice().multiply(new BigDecimal(
                ((int) DateUtil.differenceInDays(new Date(), loan.getManufactured()))
                / 365) ).divide(new BigDecimal(loan.getMaximumAge()), BigDecimal.ROUND_CEILING);
        return vehicleDepreciation;
    }

    public static boolean isValid(Loan loan) {
        return DateUtil.addYears(loan.getCreationDate(), loan.getTermInYears()).after(new Date());
    }

    public static BigDecimal calculateInterest(Loan loan) {
        return loan.getPrice().multiply((loan.getInterestRate().divide(new BigDecimal(100), 2 , BigDecimal.ROUND_CEILING)));
    }

    public static BigDecimal calculateTotalLoanCost(Loan loan) {
        return loan.getPrice().add(LoanUtil.calculateInterest(loan));
    }
}
