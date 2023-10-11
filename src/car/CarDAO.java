package car;

import java.math.BigDecimal;

public class CarDAO {
    private static final Car[] CARS;

    static {
        CARS = new Car[]{
                new Car(Brand.MERCEDES, "12345", new BigDecimal("89.60"), false),
                new Car(Brand.TESLA, "74569", new BigDecimal("90.55"), true),
                new Car(Brand.AUDI, "98653", new BigDecimal("65.68"), false)
        };
    }

    ;

    public Car[] getAllCars() {
        return CARS;
    }
}
