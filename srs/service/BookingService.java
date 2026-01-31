package service;

import dao.BookingDAO;
import dao.RideDAO;

import java.sql.ResultSet;

public class BookingService {

    private BookingDAO bookingDAO = new BookingDAO();
    private RideDAO rideDAO = new RideDAO();

    // Book ride (already exists)
    public void bookRide(int rideId, int userId, int seatsRequested) throws Exception {

        ResultSet rs = rideDAO.getRideForBooking(rideId);

        if (!rs.next()) {
            System.out.println(" Ride not available");
            return;
        }

        int availableSeats = rs.getInt("seats");
        int farePerSeat = rs.getInt("fare_per_seat");

        if (seatsRequested > availableSeats) {
            System.out.println(" Not enough seats available");
            return;
        }

        int totalFare = seatsRequested * farePerSeat;

        bookingDAO.bookRide(
                new model.Booking(rideId, userId, seatsRequested, totalFare)
        );

        rideDAO.reduceSeats(rideId, seatsRequested);

        System.out.println("Booking successful");
        System.out.println("Total Fare: ₹" + totalFare);
    }

    // 🔥 CANCEL BOOKING + SEAT ROLLBACK
    public void cancelBooking(int bookingId, int loggedInUserId) throws Exception {

        ResultSet rs = bookingDAO.getBookingById(bookingId);

        if (!rs.next()) {
            System.out.println(" Booking not found");
            return;
        }

        int bookingUserId = rs.getInt("user_id");
        int rideId = rs.getInt("ride_id");
        int seatsBooked = rs.getInt("seats_booked");

        // Ownership check
        if (bookingUserId != loggedInUserId) {
            System.out.println("You can cancel only your own booking");
            return;
        }

        // Delete booking
        bookingDAO.deleteBooking(bookingId);

        // Restore seats
        rideDAO.rollbackSeats(rideId, seatsBooked);

        System.out.println(" Booking cancelled");
        System.out.println("Seats restored: " + seatsBooked);
    }
    // BookingService.java

    public void showMyBookings(int userId) throws Exception {

        ResultSet rs = bookingDAO.getBookingsByUser(userId);

        System.out.println("\nMY BOOKINGS");
        System.out.println("BOOKING_ID | ROUTE | SEATS | TOTAL FARE");
        System.out.println("----------------------------------------");

        boolean found = false;

        while (rs.next()) {
            found = true;
            System.out.println(
                    rs.getInt("id") + " | " +
                            rs.getString("source") + " → " +
                            rs.getString("destination") + " | " +
                            rs.getInt("seats_booked") + " | ₹" +
                            rs.getInt("total_fare")
            );
        }

        if (!found) {
            System.out.println(" No bookings found");
        }
    }

}