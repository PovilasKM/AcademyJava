package it.academy;


import it.academy.domain.Loan;
import it.academy.domain.LoanRiskType;
import it.academy.service.LoanService;

public class Task3Test {
  public static void main(String[] args) {
    LoanService loanService = new LoanService(
      createLoans(
        new LoanData("1", LoanRiskType.LOW_RISK),
        new LoanData("2", LoanRiskType.NORMAL_RISK),
        new LoanData("3", LoanRiskType.NORMAL_RISK),
        new LoanData("4", LoanRiskType.HIGH_RISK),
        new LoanData("5", LoanRiskType.LOW_RISK),
        new LoanData("6", LoanRiskType.NORMAL_RISK)
      )
    );

    for (LoanRiskType riskType : loanService.groupLoansByRiskType().keySet()) {
      System.out.print(riskType + " -> ");
      for (Loan loan : loanService.groupLoansByRiskType().get(riskType)) {
        System.out.print(loan.getName() + " ");
      }
      System.out.println();
    }
  }

  private static Loan[] createLoans(LoanData... loanData) {
    Loan[] loans = new Loan[loanData.length];
    for (int i = 0; i < loanData.length; i++) {
      loans[i] = createLoan(loanData[i].name, loanData[i].riskType);
    }
    return loans;
  }

  private static Loan createLoan(String name, LoanRiskType riskType) {
    Loan loan = new Loan();
    loan.setName(name);
    loan.setRiskType(riskType);
    return loan;
  }

  private static class LoanData {
    private final String name;
    private final LoanRiskType riskType;

    private LoanData(String name, LoanRiskType riskType) {
      this.name = name;
      this.riskType = riskType;
    }
  }
}
