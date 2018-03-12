package it.academy.initializer;


import it.academy.domain.*;
import it.academy.util.DateUtil;

import java.math.BigDecimal;

public class Task2DomainInitializer implements DomainInitializer {

    @Override
    public Loan[] initializeLoans() {
        Loan[] loans = new Loan[10];

        loans[0] = new RealEstateLoan();

        loans[0].setCreationDate(DateUtil.getDateFromString("2010-01-01"));
        loans[0].setInterestRate(new BigDecimal(15.0));
        loans[0].setPrice(new BigDecimal(10000));
        loans[0].setRiskType(LoanRiskType.HIGH_RISK);
        loans[0].setTermInYears(2);
        ((RealEstateLoan)loans[0]).setArea(100);
        ((RealEstateLoan)loans[0]).setDistrict("Vilnius, Fabijoniškių raj.");
        ((RealEstateLoan)loans[0]).setPurpose(RealEstatePurpose.COMMERCIAL);
        loans[0].setName("RealEstateLoan 0");

        loans[1] = new RealEstateLoan();

        loans[1].setCreationDate(DateUtil.getDateFromString("2017-01-01"));
        loans[1].setInterestRate(new BigDecimal(17.0));
        loans[1].setPrice(new BigDecimal(100000));
        loans[1].setRiskType(LoanRiskType.HIGH_RISK);
        loans[1].setTermInYears(5);
        ((RealEstateLoan)loans[1]).setArea(50);
        ((RealEstateLoan)loans[1]).setDistrict("Klaipėda, Melnragė");
        ((RealEstateLoan)loans[1]).setPurpose(RealEstatePurpose.PERSONAL);
        loans[1].setName("RealEstateLoan 1");

        loans[2] = new RealEstateLoan();

        loans[2].setCreationDate(DateUtil.getDateFromString("2010-01-01"));
        loans[2].setInterestRate(new BigDecimal(15.0));
        loans[2].setPrice(new BigDecimal(20000000));
        loans[2].setRiskType(LoanRiskType.HIGH_RISK);
        loans[2].setTermInYears(5);
        ((RealEstateLoan)loans[2]).setArea(500);
        ((RealEstateLoan)loans[2]).setDistrict("Kaunas, Senamiestis");
        ((RealEstateLoan)loans[2]).setPurpose(RealEstatePurpose.COMMERCIAL);
        loans[2].setName("RealEstateLoan 2");

        loans[3] = new RealEstateLoan();

        loans[3].setCreationDate(DateUtil.getDateFromString("2015-01-01"));
        loans[3].setInterestRate(new BigDecimal(12.0));
        loans[3].setPrice(new BigDecimal(19000));
        loans[3].setRiskType(LoanRiskType.NORMAL_RISK);
        loans[3].setTermInYears(20);
        ((RealEstateLoan)loans[3]).setArea(100);
        ((RealEstateLoan)loans[3]).setDistrict("Kazlų Rūda");
        ((RealEstateLoan)loans[3]).setPurpose(RealEstatePurpose.COMMERCIAL);
        loans[3].setName("RealEstateLoan 3");

        loans[4] = new RealEstateLoan();

        loans[4].setCreationDate(DateUtil.getDateFromString("2014-01-01"));
        loans[4].setInterestRate(new BigDecimal(20.0));
        loans[4].setPrice(new BigDecimal(5000));
        loans[4].setRiskType(LoanRiskType.LOW_RISK);
        loans[4].setTermInYears(15);
        ((RealEstateLoan)loans[4]).setArea(100);
        ((RealEstateLoan)loans[4]).setDistrict("Vilnius, Naujininkų raj.");
        ((RealEstateLoan)loans[4]).setPurpose(RealEstatePurpose.PERSONAL);
        loans[4].setName("RealEstateLoan 4");

        loans[5] = new VehicleLoan();

        loans[5].setCreationDate(DateUtil.getDateFromString("2013-01-01"));
        loans[5].setInterestRate(new BigDecimal(15.0));
        loans[5].setPrice(new BigDecimal(30000));
        loans[5].setRiskType(LoanRiskType.NORMAL_RISK);
        loans[5].setTermInYears(2);
        ((VehicleLoan)loans[5]).setManufactured(DateUtil.getDateFromString("2010-01-01"));
        ((VehicleLoan)loans[5]).setMaximumAge(5);
        ((VehicleLoan)loans[5]).setModel("Fiat Punto");
        loans[5].setName("VehicleLoan 5");

        loans[6] = new VehicleLoan();

        loans[6].setCreationDate(DateUtil.getDateFromString("2014-01-01"));
        loans[6].setInterestRate(new BigDecimal(11.0));
        loans[6].setPrice(new BigDecimal(19000));
        loans[6].setRiskType(LoanRiskType.HIGH_RISK);
        loans[6].setTermInYears(4);
        ((VehicleLoan)loans[6]).setManufactured(DateUtil.getDateFromString("2014-01-01"));
        ((VehicleLoan)loans[6]).setMaximumAge(5);
        ((VehicleLoan)loans[6]).setModel("ZAZ 3000");
        loans[6].setName("VehicleLoan 6");

        loans[7] = new VehicleLoan();

        loans[7].setCreationDate(DateUtil.getDateFromString("2015-01-01"));
        loans[7].setInterestRate(new BigDecimal(15.0));
        loans[7].setPrice(new BigDecimal(14000));
        loans[7].setRiskType(LoanRiskType.NORMAL_RISK);
        loans[7].setTermInYears(2);
        ((VehicleLoan)loans[7]).setManufactured(DateUtil.getDateFromString("2012-01-01"));
        ((VehicleLoan)loans[7]).setMaximumAge(10);
        ((VehicleLoan)loans[7]).setModel("Volkswagen Golf");
        loans[7].setName("VehicleLoan 7");

        loans[8] = new VehicleLoan();

        loans[8].setCreationDate(DateUtil.getDateFromString("2016-01-01"));
        loans[8].setInterestRate(new BigDecimal(5.0));
        loans[8].setPrice(new BigDecimal(10000));
        loans[8].setRiskType(LoanRiskType.NORMAL_RISK);
        loans[8].setTermInYears(2);
        ((VehicleLoan)loans[8]).setManufactured(DateUtil.getDateFromString("1998-01-01"));
        ((VehicleLoan)loans[8]).setMaximumAge(10);
        ((VehicleLoan)loans[5]).setModel("Porsche 911");
        loans[8].setName("VehicleLoan 8");

        loans[9] = new VehicleLoan();

        loans[9].setCreationDate(DateUtil.getDateFromString("2018-01-01"));
        loans[9].setInterestRate(new BigDecimal(19.0));
        loans[9].setPrice(new BigDecimal(2500));
        loans[9].setRiskType(LoanRiskType.LOW_RISK);
        loans[9].setTermInYears(3);
        ((VehicleLoan)loans[9]).setManufactured(DateUtil.getDateFromString("2017-01-01"));
        ((VehicleLoan)loans[9]).setMaximumAge(15);
        ((VehicleLoan)loans[9]).setModel("Opel Astra");
        loans[9].setName("VehicleLoan 9");

        return loans;
    }

}
