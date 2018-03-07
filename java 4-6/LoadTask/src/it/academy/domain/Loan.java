package it.academy.domain;

import it.academy.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Loan {
    private Date creationDate;
    private int termInYears;
    private String name;
    private BigDecimal interestRate;
    private BigDecimal price;
    private LoanRiskType riskType;

    private BigDecimal calculateInterest() {
        return price.multiply((interestRate.divide(new BigDecimal(100))));
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTotalLoanCost() {
        return price.add(this.calculateInterest());
    }

    public boolean isValid() {
        return DateUtil.addYears(creationDate, termInYears).after(new Date());
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setInterestRate(BigDecimal interestRate) {
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

    public int getTermInYears() {
        return termInYears;
    }

    public String getName() {
        return name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loan)) return false;
        Loan loan = (Loan) o;
        return getTermInYears() == loan.getTermInYears() &&
                Objects.equals(getCreationDate(), loan.getCreationDate()) &&
                Objects.equals(getName(), loan.getName()) &&
                Objects.equals(getInterestRate(), loan.getInterestRate()) &&
                Objects.equals(getPrice(), loan.getPrice()) &&
                getRiskType() == loan.getRiskType();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCreationDate(), getTermInYears(), getName(), getInterestRate(), getPrice(), getRiskType());
    }
}
