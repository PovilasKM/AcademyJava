package it.academy.app;

import it.academy.domain.Loan;
import it.academy.domain.LoanRiskType;
import it.academy.initializer.DomainInitializer;
import it.academy.initializer.Task1DomainInitializer;
import it.academy.initializer.Task2DomainInitializer;
import it.academy.initializer.Task3DomainInitializer;
import it.academy.service.LoanService;
import it.academy.service.LoanServiceInterface;

import java.util.Arrays;

public class ClientApp {

    public static void main(String[] args) {

        Loan[] loans = getInitializer().initializeLoans();
        LoanService service = new LoanService(loans);

        service.setHighRiskLoans(Arrays.asList(service.findLoansByRiskType(LoanRiskType.HIGH_RISK, loans)));
        System.out.println(service.getHighRiskLoans().size());

        service.setAverageLoanCost(service.calculateAverageLoanCost());
        System.out.println(service.getAverageLoanCost());

        System.out.println(service.getAverageLoanCost(LoanRiskType.NORMAL_RISK));
        System.out.println(service.getAverageLoanCost(LoanRiskType.HIGH_RISK));
        System.out.println(service.getAverageLoanCost(LoanRiskType.LOW_RISK));

        service.setAverageCostOfHighRiskLoans(service.findAverageCostOfHighRiskLoans());
        System.out.println(service.getAverageCostOfHighRiskLoans());

        service.setMaximumPriceOfNonExpiredLoans(service.findMaximumPriceOfNonExpiredLoans());
        System.out.println(service.getMaximumPriceOfNonExpiredLoans());

        System.out.println("---------------------");
//--------------------------------------------------------------------------------------
        loans = getInitializer2().initializeLoans();
        service = new LoanService(loans);

        service.setNormalRiskVehicleLoans(service.findNormalRiskVehicleLoans());
        System.out.println(service.getNormalRiskVehicleLoans().size());

        service.setMaximumAgeOfLowRiskLoanedVehicles(service.findMaximumAgeOfLowRiskLoanedVehicles());
        System.out.println(service.getMaximumAgeOfLowRiskLoanedVehicles());

        service.setPersonalRealEstateLoans(service.findPersonalRealEstateLoans());
        System.out.println(service.getPersonalRealEstateLoans().size());

        service.setExpiredHighRiskVehicleLoansOfHighestDuration(
                service.findExpiredHighRiskVehicleLoansOfHighestDuration());
        System.out.println(service.getExpiredHighRiskVehicleLoansOfHighestDuration().size()
                + "  " + service.getExpiredHighRiskVehicleLoansOfHighestDuration().get(0).getTermInYears());
        System.out.println("---------------------");
//--------------------------------------------------------------------------------------
        loans = getInitializer3().initializeLoans();
        service = new LoanService(loans);

        service.setLowRiskHarvesterLoans(service.findLowRiskHarvesterLoans());
        System.out.println(service.getLowRiskHarvesterLoans().size());

        service.setExpiredLandLoansInReservation(service.findExpiredLandLoansInReservation());
        System.out.println(service.getExpiredLandLoansInReservation().size());

        service.setLoansOfHigherThanAverageDepreciation(service.findLoansOfHigherThanAverageDepreciation());
        System.out.println(service.getLoansOfHigherThanAverageDepreciation().size());

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
