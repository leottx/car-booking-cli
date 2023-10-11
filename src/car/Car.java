package car;

import java.math.BigDecimal;
import java.util.Objects;

public class Car {
    private Brand brand;
    private String regNumber;
    private BigDecimal rentalPrice;
    private boolean isElectric;

    public Car(Brand brand, String regNumber, BigDecimal rentalPrice, boolean isElectric) {
        this.brand = brand;
        this.regNumber = regNumber;
        this.rentalPrice = rentalPrice;
        this.isElectric = isElectric;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public boolean isElectric() {
        return isElectric;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand=" + brand +
                ", regNumber='" + regNumber + '\'' +
                ", rentalPrice=" + rentalPrice +
                ", isElectric=" + isElectric +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return isElectric == car.isElectric && brand == car.brand && Objects.equals(regNumber, car.regNumber) && Objects.equals(rentalPrice, car.rentalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, regNumber, rentalPrice, isElectric);
    }
}
