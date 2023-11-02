import booking.Booking;
import booking.BookingService;
import car.Car;
import user.User;
import user.UserService;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        BookingService bookingService = new BookingService();
        UserService userService = new UserService();

        while (exit == false) {
            try {
                printBookingMenu();
                String input = scanner.nextLine();

                switch (Integer.parseInt(input)) {
                    case 1 -> bookCar(userService, bookingService, scanner);
                    case 2 -> printAllUserBookedCars(userService, bookingService, scanner);
                    case 3 -> printAllBookings(bookingService);
                    case 4 -> printAllAvailableCars(bookingService);
                    case 5 -> printAllAvailableEletricCars(bookingService);
                    case 6 -> printAllUsers(userService);
                    case 7 -> exit = true;
                    default -> System.out.println(input + " isn't a valid option");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printBookingMenu() {
        System.out.println("1 - Book car");
        System.out.println("2 - View all user booked cars");
        System.out.println("3 - View all bookings");
        System.out.println("4 - View available cars");
        System.out.println("5 - View available eletric cars");
        System.out.println("6 - View all users");
        System.out.println("7 - Exit");
    }

    private static void bookCar(UserService userService, BookingService bookingService, Scanner scanner) {
        printAllAvailableCars(bookingService);

        System.out.println("Select car reg number: ");
        String carReg = scanner.nextLine();

        try {
            printAllUsers(userService);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Select user id: ");
        String userId = scanner.nextLine();

        try {
            User user = userService.getUserById(UUID.fromString(userId));

            if (user == null) {
                System.out.println("No user found with id " + userId);
            } else {
                UUID bookingId = bookingService.book(carReg, user);
                String confirmationMsg = "🎉 Successfully booked car with reg number %s for user %s Booking ref: %s".formatted(carReg, user, bookingId);
                System.out.println(confirmationMsg);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printAllUserBookedCars(UserService userService, BookingService bookingService, Scanner scanner) throws FileNotFoundException{
        printAllUsers(userService);
        System.out.println("Select user id: ");
        UUID userId = UUID.fromString(scanner.nextLine());
        User user = userService.getUserById(userId);

        if (user == null) {
            System.out.println("No user found with id " + userId);
            return;
        }

        Booking[] userBookings = bookingService.getUserBookings(user);

        if (userBookings.length == 0) {
            System.out.println(String.format("User %s has no booked cars", user));
            return;
        }

        for(Booking userBooking: userBookings) {
            System.out.println(userBooking);
        }
    }

    private static void printAllBookings(BookingService bookingService) {
        Booking[] bookings = bookingService.getAllBookings();

        if (bookings.length == 0) {
            System.out.println("There is no bookings");
            return;
        }

        for (Booking booking: bookings) {
            System.out.println(booking);
        }
    }

    private static void printAllAvailableCars(BookingService bookingService) {
        Car[] availableCars = bookingService.getAvailableCars();

        if (availableCars.length == 0) {
            System.out.println("❌ No car available for renting");
            return;
        }

        System.out.println("Available cars: ");

        for (Car car : availableCars
        ) {
            System.out.println(car);
        }
    }

    private static void printAllAvailableEletricCars(BookingService bookingService) {
        Car[] availableElectricCars = bookingService.getAvailableElectricCars();

        if (availableElectricCars.length == 0) {
            System.out.println("No electric cars available for renting");
            return;
        }

        for(Car electricCar: availableElectricCars) {
            System.out.println(electricCar);
        }
    }

    private static void printAllUsers(UserService userService) {
        User[] users = userService.getAllUsers();

        System.out.println("Users: ");

        for (User user :
                users) {
            System.out.println(user);
        }
    }
}
