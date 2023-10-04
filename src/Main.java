import booking.BookingService;
import car.Car;
import user.User;
import user.UserService;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        BookingService bookingService = new BookingService();
        UserService userService = new UserService();

        while(exit == false) {
            try {
                printBookingMenu();
                String input = scanner.nextLine();

                switch (Integer.parseInt(input)) {
                    case 1 -> bookCar(userService, bookingService, scanner);
                    case 2 -> printAllUserBookedCars();
                    case 3 -> printAllBookings();
                    case 4 -> printAllAvailableCars(bookingService);
                    case 5 -> printAllAvailableEletricCars();
                    case 6 -> printAllUsers(userService);
                    case 7 -> exit = true;
                    default -> System.out.println(input + "isn't a valid option");
                }
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void printBookingMenu() {
        System.out.println("1 - Book car");
        System.out.println("2 - View all user booked cars");
        System.out.println("3 - View all bookings");
        System.out.println("4 - View available cars");
        System.out.println("5 - View available eletric cars");
        System.out.println("6 - View all users");
        System.out.println("7 - Exit");
    }

    public static void bookCar(UserService userService, BookingService bookingService, Scanner scanner) {
        printAllAvailableCars(bookingService);

        System.out.println("Select car reg number: ");
        String carReg = scanner.nextLine();

        printAllUsers(userService);
        System.out.println("Select user id: ");
        String userId = scanner.nextLine();

        try {
            User user = userService.getUserById(UUID.fromString(userId));

            if(user == null) {
                System.out.println("No user found with id " + userId);
            } else {
                UUID bookingId = bookingService.book(carReg, user);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    };

    public static void printAllUserBookedCars() {};

    public static void printAllBookings() {};

    public static void printAllAvailableCars(BookingService bookingService) {
        Car[] availableCars = bookingService.getAvailableCars();

        System.out.println("Available cars: ");

        for (Car car: availableCars
             ) {
            System.out.println(car);
        }
    };

    public static void printAllAvailableEletricCars() {};

    public static void printAllUsers(UserService userService) {
        User[] users = userService.getAllUsers();

        System.out.println("Users: ");

        for (User user:
             users) {
            System.out.println(user);
        }
    };
}
