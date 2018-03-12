package it.academy.app;

import it.academy.domain.Loan;
import it.academy.domain.LoanRiskType;
import it.academy.initializer.DomainInitializer;
import it.academy.initializer.Task1DomainInitializer;
import it.academy.initializer.Task2DomainInitializer;
import it.academy.initializer.Task3DomainInitializer;
import it.academy.service.LoanService;

import java.util.Arrays;

public class ClientApp {

    public static void main(String[] args) {

        Loan[] loans = getInitializer().initializeLoans();
        LoanService service = new LoanService(loans);

        System.out.println(service.findLoansByRiskType(LoanRiskType.HIGH_RISK, null).length);

        System.out.println(service.calculateAverageLoanCost());

        System.out.println(service.calculateAverageLoanCost(LoanRiskType.NORMAL_RISK));
        System.out.println(service.calculateAverageLoanCost(LoanRiskType.HIGH_RISK));
        System.out.println(service.calculateAverageLoanCost(LoanRiskType.LOW_RISK));

        System.out.println(service.findAverageCostOfHighRiskLoans());

        System.out.println(service.findMaximumPriceOfNonExpiredLoans());

        System.out.println("---------------------");
//--------------------------------------------------------------------------------------
        loans = getInitializer2().initializeLoans();
        service = new LoanService(loans);

        System.out.println(service.findNormalRiskVehicleLoans().size());

        System.out.println(service.findMaximumAgeOfLowRiskLoanedVehicles());

        System.out.println(service.findPersonalRealEstateLoans().size());

        System.out.println(service.findExpiredHighRiskVehicleLoansOfHighestDuration().size()
                + "  " + service.findExpiredHighRiskVehicleLoansOfHighestDuration().get(0).getTermInYears());
        System.out.println("---------------------");
//--------------------------------------------------------------------------------------
        loans = getInitializer3().initializeLoans();
        service = new LoanService(loans);

        System.out.println(service.findLowRiskHarvesterLoans().size());

        System.out.println(service.findExpiredLandLoansInReservation().size());

        System.out.println(service.findLoansOfHigherThanAverageDepreciation().size());

        System.out.println("---------------------");
//--------------------------------------------------------------------------------------
        for(String s : service.findVehicleModels()){
            System.out.println(s);
        }
    }


    public static DomainInitializer getInitializer() {
        return new Task1DomainInitializer();
    }

    public static DomainInitializer getInitializer2() {
        return new Task2DomainInitializer();
    }

    public static DomainInitializer getInitializer3() {
        return new Task3DomainInitializer();
    }

}
