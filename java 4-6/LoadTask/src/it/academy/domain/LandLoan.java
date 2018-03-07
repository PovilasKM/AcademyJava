package it.academy.domain;

import java.util.Objects;

public class LandLoan extends RealEstateLoan {

    private boolean inReservation;

    public boolean isInReservation(){
        return inReservation;
    }

    public void setInReservation(boolean inReservation) {
        this.inReservation = inReservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LandLoan)) return false;
        if (!super.equals(o)) return false;
        LandLoan landLoan = (LandLoan) o;
        return isInReservation() == landLoan.isInReservation();
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), isInReservation());
    }
}
