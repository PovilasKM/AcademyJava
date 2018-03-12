package it.academy.domain;

import java.util.Date;
import java.util.Objects;

public class HouseLoan extends RealEstateLoan{

    private int floorCount;
    private Date constructionDate;

    public int getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }

    public Date getConstructionDate() {
        return constructionDate;
    }

    public void setConstructionDate(Date constructionDate) {
        this.constructionDate = constructionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HouseLoan)) return false;
        if (!super.equals(o)) return false;
        HouseLoan houseLoan = (HouseLoan) o;
        return getFloorCount() == houseLoan.getFloorCount() &&
                Objects.equals(getConstructionDate(), houseLoan.getConstructionDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getFloorCount(), getConstructionDate());
    }
}
