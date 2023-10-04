package booking;

import car.Car;
import car.CarService;
import user.User;

import java.util.UUID;

public class BookingService {
    private final CarService carService = new CarService();
    private final BookingDAO bookingDAO = new BookingDAO();

    public Car[] getAvailableCars() {
        // Get all cars
        Car[] cars = carService.getAllCars();
        // Get all bookings
        Booking[] bookings = bookingDAO.getAllBookings();


        if (cars.length == 0) {
            return new Car[0];
        }

        int availableCarsCount = 0;

        for (Car car :
                cars) {

            boolean isBooked = false;

            // Iterates bookings array
            for (Booking booking :
                    bookings) {
                if (booking == null || !booking.getCar().equals(car)) {
                    continue;
                }
                isBooked = true;
            }
            if (!isBooked) {
                ++availableCarsCount;
            }
        }

        Car[] availableCars = new Car[availableCarsCount];


        for (int i = 0; i < cars.length; i++) {
            boolean isBooked = false;

            for (Booking booking:
                 bookings) {
                if (booking == null || !booking.getCar().equals(cars[i])) {
                    continue;
                }
                isBooked = true;
            }
            if(!isBooked) {
                availableCars[i] = cars[i];
            }
        }

        return availableCars;
    }

    public UUID book(String carReg, User user) {
        Car[] availableCars = getAvailableCars();

        if (availableCars.length == 0) {
            throw new IllegalArgumentException("No car available for renting");
        }

        for (Car availableCar: availableCars) {
            if(availableCar.getRegNumber().equals(carReg)) {
                Car car = carService.getCar(carReg);
                UUID bookingId = UUID.randomUUID();
                // bookingDAO.book(new Booking(user, car));
            }
        }
    }

}
