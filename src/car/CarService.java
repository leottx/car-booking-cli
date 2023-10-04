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
}
