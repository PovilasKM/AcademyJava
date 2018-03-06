package it.academy.domain;

import it.academy.util.DateUtil;

import java.util.Date;

public class VehicleLoan extends Loan {

    private Date manufactured;
    private String model;
    private int age;
    private int maximumAge;

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getMaximumAge() {
        return maximumAge;
    }

    public void setMaximumAge(int maximumAge) {
        this.maximumAge = maximumAge;
    }

    public Date getManufactured() {
        return manufactured;
    }

    public void setManufactured(Date manufactured) {
        this.manufactured = manufactured;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isRiskNormal(){
        return DateUtil.differenceInDays(manufactured,new Date())/365 < maximumAge;
    }
}
