package it.academy.domain;

import it.academy.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

public class Loan {
    private Date creationDate;
    private int termInYears;
    private String name;
    private BigDecimal interestRate;
    private BigDecimal price;

    private LoanRiskType riskType;

    public BigDecimal calculateInterest(){
        return price.multiply((interestRate.divide(new BigDecimal(100))));
    }

    public BigDecimal getInterestRate(){
        return interestRate;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public BigDecimal getTotalLoanCost(){
        return price.add(this.calculateInterest());
    }

    public boolean isValid(){
        return DateUtil.addYears(creationDate, termInYears).after(new Date());
    }

    public int calculateLoanDuration(){
        return ((int) DateUtil.differenceInDays(creationDate, new Date())) / 365;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setInterestRate(BigDecimal interestRate){
        this.interestRate = interestRate;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setRiskType(LoanRiskType riskType) {
        this.riskType = riskType;
    }

    public void setTermInYears(int termInYears) {
        this.termInYears = termInYears;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LoanRiskType getRiskType() {
        return riskType;
    }

}
