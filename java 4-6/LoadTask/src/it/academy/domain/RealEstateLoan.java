package it.academy.domain;

import java.util.Objects;

public class RealEstateLoan extends Loan {

    private String district;
    private float area;
    private RealEstatePurpose purpose;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public RealEstatePurpose getPurpose() {
        return purpose;
    }

    public void setPurpose(RealEstatePurpose purpose) {
        this.purpose = purpose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RealEstateLoan)) return false;
        if (!super.equals(o)) return false;
        RealEstateLoan that = (RealEstateLoan) o;
        return Float.compare(that.getArea(), getArea()) == 0 &&
                Objects.equals(getDistrict(), that.getDistrict()) &&
                getPurpose() == that.getPurpose();
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getDistrict(), getArea(), getPurpose());
    }
}
