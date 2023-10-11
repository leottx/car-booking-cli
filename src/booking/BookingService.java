package booking;

import car.Car;
import car.CarService;
import user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookingService {
    private final CarService carService = new CarService();
    private final BookingDAO bookingDAO = new BookingDAO();

    public Car[] getAvailableCars() {
        return getCars(carService.getAllCars());
    }

    public Car[] getAvailableElectricCars() {
        return getCars(carService.getElectricCars());
    }

    public Car[] getCars(Car[] cars) {
        // Get all bookings
        Booking[] bookings = bookingDAO.getAllBookings();

        if (cars.length == 0) {
            return new Car[0];
        }

        int availableCarsCount = 0;

        for (Car car : cars) {

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

        int index = 0;
        // Populate available cars
        for (Car car : cars) {
            // Lets check if car part of any booking.
            // If not then its available but this time we add it to available cars
            boolean booked = false;
            for (Booking booking : bookings) {
                if (booking == null || !booking.getCar().equals(car)) {
                    continue;
                }
                booked = true;
            }
            if (!booked) {
                availableCars[index++] = car;
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
                LocalDateTime bookingTime = LocalDateTime.now();
                bookingDAO.book(new Booking(bookingId, user, car, bookingTime, false));
                return bookingId;
            }
        }

        throw new IllegalArgumentException(String.format("No car with reg number %s available", carReg));
    }

    public Booking[] getAllBookings() {
        Booking[] bookings = bookingDAO.getAllBookings();

        int numberOfBookings = 0;

        for (Booking booking: bookings) {
            if (booking != null) {
                numberOfBookings++;
            }
        }

        if (numberOfBookings == 0) {
            return new Booking[0];
        }

        Booking[] realBookings = new Booking[numberOfBookings];

        int index = 0;
        for (Booking booking: bookings) {
            if (booking != null) {
                realBookings[index++] = booking;
            }
        }

        return realBookings;
    }

    public Booking[] getUserBookings(User user) {
        int userBookingsCount = 0;

        for(Booking booking: getAllBookings()) {
            if (!booking.getUser().equals(user)) {
                continue;
            }
            userBookingsCount++;
        }

        Booking[] userBookings = new Booking[userBookingsCount];

        int index = 0;
        for(Booking booking: getAllBookings()) {
            if (!booking.getUser().equals(user)) {
                continue;
            }
            userBookings[index++] = booking;
        }

        return userBookings;
    }
}
