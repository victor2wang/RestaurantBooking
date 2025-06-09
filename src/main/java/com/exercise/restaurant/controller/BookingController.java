package com.exercise.restaurant.controller;

import com.exercise.restaurant.model.Booking;
import com.exercise.restaurant.vo.CustomerBookingVO;
import com.exercise.util.TableSize;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Path("/bookings")
public class BookingController {

    private static final Logger log = LoggerFactory.getLogger(BookingController.class);
    ConcurrentHashMap<String, Booking> bookingMP = new ConcurrentHashMap<String, Booking>();
    final int SLOT_INTERVAL = 2;
    public BookingController() {
    }

    @GET
    @Path("/{phone}")
    @Produces("application/json")
    public Booking getBookingByPhone(@PathParam("phone") String phone) {

        Booking booking = bookingMP.get(phone);
        if (booking == null) {
            throw new NotFoundException("No Booking found with the phone of " + phone);
        }
        return booking;
    }

    @GET
    @Path("/queryByDate")
    @Produces("application/json")
    public List<Booking> getBookingsByDate(@QueryParam("bookedDate") String bookedDate) {
        LocalDate queryDate = LocalDate.parse(bookedDate);
        var bookings = new ArrayList<Booking>();
        for (Booking booking : bookingMP.values()){
            LocalDate curDate = booking.getBookedDateTime().toLocalDate();
            if (curDate.equals(queryDate)){
                bookings.add(booking);
            }

        }
        return bookings;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBooking(CustomerBookingVO bookingVO){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime DateTimeTobeBooked =  LocalDateTime.parse(bookingVO.getBookedDateTime(),formatter);
        //check if the slot is taken,assume that the bookedDateTime uses the start-time of the slot to be booked
        boolean slotTaken=false;
        for (Booking booking : bookingMP.values()){
            long hoursDifference = ChronoUnit.HOURS.between(DateTimeTobeBooked, booking.getBookedDateTime());
            log.info("hoursDifference:" + hoursDifference);
            if (Math.abs(hoursDifference) < SLOT_INTERVAL) {
                slotTaken = true;
                break;
            }
        }
        if (slotTaken){
            return Response.status(527).entity("The slot has been taken").build();
        }

        Booking booking= new Booking(UUID.randomUUID(),bookingVO.getCustomerPhone(), TableSize.valueOf(bookingVO.getTableSize()), LocalDateTime.parse(bookingVO.getBookedDateTime(),formatter),bookingVO.getRestaurantName());
        bookingMP.put(bookingVO.getCustomerPhone(),booking);
        return Response.status(Response.Status.CREATED).entity("Slot successfully booked").build();
        //return  "Slot successfully booked";
    }
}
