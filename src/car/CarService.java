package car;

public class CarService {
    private final CarDAO carDAO = new CarDAO();

    public Car getCar(String regNumber) {
        for (Car car: getAllCars()) {
            if(car.getRegNumber().equals(regNumber)) {
                return car;
            }
        }

        throw new IllegalArgumentException(String.format("Car with reg %s not found", regNumber));
    }

    public Car[] getAllCars() {
        return carDAO.getAllCars();
    }

    public Car[] getElectricCars() {
        Car[] cars = getAllCars();
        int electricCarsCount = 0;

        for(Car car : cars) {
            if(!car.isElectric()) {
                continue;
            }
            electricCarsCount++;
        }

        Car[] electricCars = new Car[electricCarsCount];

        int index = 0;
        for(Car car : cars) {
            if (!car.isElectric()) {
                continue;
            }
            electricCars[index] = car;
        }

        return electricCars;
    }
}
