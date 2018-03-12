package it.academy.domain;

import java.util.Objects;

public class HarvesterLoan extends VehicleLoan{

    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HarvesterLoan)) return false;
        if (!super.equals(o)) return false;
        HarvesterLoan that = (HarvesterLoan) o;
        return getCapacity() == that.getCapacity();
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getCapacity());
    }
}
