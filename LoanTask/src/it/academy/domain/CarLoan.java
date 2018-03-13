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

    //Missing @Override
    public int compareTo(CarLoan carLoan){
        //You can simplify it by using "compareTo(..)" result directly, you don't need to convert it to 1 and -1.
        //Also, do not use "this" keyword where it is not necessary. "this" should be used in case you are referring to an object
        // itself (ex.: "equals" method - "this == o"), or a field you are trying to access is being obstructed by a parameter or
        // a local variable of the same name (ex.: setters).
        if(carLoan.getPrice().compareTo(this.getPrice()) > 0) return 1;
        if(carLoan.getPrice().compareTo(this.getPrice()) < 0) return -1;
        if(carLoan.getEnginePower() > this.getEnginePower()) return 1;
        if(carLoan.getEnginePower() < this.getEnginePower()) return -1;
        return this.getInterestRate().compareTo(carLoan.getInterestRate());

    }
}
