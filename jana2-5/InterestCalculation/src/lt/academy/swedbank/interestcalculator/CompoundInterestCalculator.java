package lt.academy.swedbank.interestcalculator;

import java.util.Arrays;
import java.util.Scanner;

public class CompoundInterestCalculator {

    private static double amount;

    private static double temporaryInterestRates[];
    private static double interestRates[];
    private static int interestRateCounter;
    private static int periodLength;
    private static String frequency;
    private static double[][] periodicAmounts;

    private static final int PERCENT = 100;

    public static void main(String[] args) {

        double amountAfterYear = 0;
        double lastElement = 0;

        readCompoundInterestData();

        periodicAmounts = new double[interestRates.length][periodLength];

        for (int i = 0; i < interestRates.length; i++) {
            for (int j = 0; j < periodLength; j++) {
                lastElement = (j - 1 >= 0) ? (lastElement + periodicAmounts[i][j - 1]) : 0;
                periodicAmounts[i][j] = calculateAmountAfterYear(amountAfterYear, interestRates[i], j, findFrequency()) - lastElement;
            }
        }

        printMatrix();

    }

    private static void readCompoundInterestData() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Amount: ");
            amount = scanner.nextDouble();
            if (amount > 0) {
                break;
            } else {
                System.out.println("Invalid input! Try again! ");
            }
        }


        interestRateCounter = 0;
        interestRates = new double[0];
        double interestRate;
        do {
            System.out.print("Interest rate (%): ");
            interestRate = scanner.nextDouble();
            if (interestRate > 0 && interestRate <= 100) {

                    interestRateCounter++;
                    temporaryInterestRates = new double[interestRateCounter];
                    System.arraycopy(interestRates, 0, temporaryInterestRates, 0, interestRateCounter - 1);
                    interestRates = temporaryInterestRates;
                    interestRates[interestRateCounter - 1] = interestRate;

            } else if(interestRate == 0){
                break;
            }else {
                System.out.println("Invalid input! Try again! ");
            }


        } while (interestRate != 0);

        while (true) {
            System.out.print("Period length (years): ");
            periodLength = scanner.nextInt();
            if (periodLength> 0) {
                break;
            } else {
                System.out.println("Invalid input! Try again! ");
            }
        }
        while (true) {
            System.out.print("Compound frequency: ");
            frequency = scanner.next();
            if (Arrays.asList(new String[]{"D", "W", "M", "Q", "H", "Y"}).contains(frequency)) {
                break;
            } else {
                System.out.println("Invalid input! Try again! ");
            }
        }


    }

    private static double calculateAmountAfterYear(double amountAfterYear, double interestRate, int degree, int n) {

        return amount * Math.pow((1 + (interestRate / PERCENT) / n), n * (degree + 1)) - amount;
        // screw this
    }

    private static int findFrequency() {

        switch (frequency) {
            case "D":
                return 365;
            case "W":
                return 52;
            case "M":
                return 12;
            case "Q":
                return 4;
            case "H":
                return 2;
            case "Y":
            default:
                return 1;
        }
    }

    private static void printMatrix() {
        for (double[] row : periodicAmounts) {
            for (double number : row) {
                System.out.printf("%.2f ", number);
            }
            System.out.println();
        }
    }
}