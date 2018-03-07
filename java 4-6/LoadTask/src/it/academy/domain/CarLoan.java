package it.academy.domain;

import java.util.Comparator;
import java.util.Objects;

public class CarLoan extends VehicleLoan implements Comparable<CarLoan>{

    private float enginePower;

    public float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(float enginePower) {
        this.enginePower = enginePower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarLoan)) return false;
        if (!super.equals(o)) return false;
        CarLoan carLoan = (CarLoan) o;
        return Float.compare(carLoan.getEnginePower(), getEnginePower()) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getEnginePower());
    }

    public int compareTo(CarLoan carLoan){

        if(carLoan.getPrice().compareTo(this.getPrice()) == 1) return 1;
        if(carLoan.getPrice().compareTo(this.getPrice()) == -1) return -1;
        if(carLoan.getEnginePower() > this.getEnginePower()) return 1;
        if(carLoan.getEnginePower() < this.getEnginePower()) return -1;
        return this.getInterestRate().compareTo(carLoan.getInterestRate());

    }
}
