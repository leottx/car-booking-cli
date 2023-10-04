package booking;

import user.User;
import car.Car;

public class Booking {
    private User user;
    private Car car;

    public Booking(User user, Car car) {
        this.user = user;
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }
}
