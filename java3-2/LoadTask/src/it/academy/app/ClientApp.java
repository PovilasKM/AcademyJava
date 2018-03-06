package it.academy.app;

import it.academy.domain.Loan;
import it.academy.domain.LoanRiskType;
import it.academy.initializer.DomainInitializer;
import it.academy.initializer.Task1DomainInitializer;
import it.academy.initializer.Task2DomainInitializer;
import it.academy.service.LoanService;

public class ClientApp {

    public static void main(String[] args) {

        Loan[] loans = getInitializer().initializeLoans();
        LoanService service = new LoanService(loans);

        service.setHighRiskLoans(service.calculateSpecificRiskLoans(LoanRiskType.HIGH_RISK));
        System.out.println(service.getHighRiskLoans().length);

        service.setAverageLoanCost(service.calculateAverageLoanCost());
        System.out.println(service.getAverageLoanCost());

        System.out.println(service.getAverageLoanCost(LoanRiskType.NORMAL_RISK));
        System.out.println(service.getAverageLoanCost(LoanRiskType.HIGH_RISK));
        System.out.println(service.getAverageLoanCost(LoanRiskType.LOW_RISK));

        service.setAverageCostOfHighRiskLoans(service.calculateAverageCosttOfHighRiskLoans());
        System.out.println(service.getAverageCostOfHighRiskLoans());

        service.setMaximumPriceOfNonExpiredLoans(service.calculateMaximumPriceOfNonExpiredLoans());
        System.out.println(service.getMaximumPriceOfNonExpiredLoans());

        System.out.println();

        loans = getInitializer2().initializeLoans();
        service = new LoanService(loans);

        service.setNormalRiskVehicleLoans(service.calculateNormalRiskVehicleLoans());
        System.out.println(service.getNormalRiskVehicleLoans());

        service.setMaximumAgeOfLowRiskLoanedVehicles(service.calculateMaximumAgeOfLowRiskLoanedVehicles());
        System.out.println(service.getMaximumAgeOfLowRiskLoanedVehicles());

        service.setPersonalRealEstateLoans(service.calculatePersonalRealEstateLoans());
        System.out.println(service.getPersonalRealEstateLoans());

        service.setExpiredHighRiskVehicleLoansOfHighestDuration(
                service.calculateExpiredHighRiskVehicleLoansOfHighestDuration());
        System.out.println(service.getExpiredHighRiskVehicleLoansOfHighestDuration());


    }


    public static DomainInitializer getInitializer() {
        return new Task1DomainInitializer();
    }

    public static DomainInitializer getInitializer2() {
        return new Task2DomainInitializer();
    }

}
