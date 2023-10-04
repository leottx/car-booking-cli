package booking;

public class BookingDAO {
    private final static Booking[] bookings;

    static {
        bookings = new Booking[10];
    }

    public Booking[] getAllBookings() {
        return bookings;
    }

}
