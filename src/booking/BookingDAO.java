package booking;

import java.util.UUID;

public class BookingDAO {
    private final static Booking[] bookings;

    static {
        bookings = new Booking[10];
    }

    public Booking[] getAllBookings() {
        return bookings;
    }

    public void book(Booking booking) {
        int nextFreeIndex = -1;

        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i] == null) {
                nextFreeIndex = i;
            }
        }

        if (nextFreeIndex > -1) {
            bookings[nextFreeIndex] = booking;
            return;
        }

        // If there is no free index, copy all booking to a new bigger array.
        Booking[] newBookings = new Booking[bookings.length + 10];

        for (int i = 0; i < bookings.length; i++) {
            newBookings[i] = bookings[i];
        }

        newBookings[bookings.length] = booking;
    }

}
