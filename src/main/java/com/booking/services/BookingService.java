package com.booking.services;

import com.booking.models.Booking;
import com.booking.models.BookingBackup;
import com.booking.models.Event;
import com.booking.repositories.BookingBackupRepository;
import com.booking.repositories.BookingRepository;
import com.booking.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingBackupRepository bookingBackupRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ChaosMonkeyService chaosMonkeyService;

    public void createBooking(Booking booking) {
        Booking savedBooking = bookingRepository.save(booking);

        BookingBackup bookingBackup = new BookingBackup();

        bookingBackup.setId(savedBooking.getId());
        bookingBackup.setUserName(savedBooking.getUserName());
        bookingBackup.setEmail(savedBooking.getEmail());
        bookingBackup.setEventId(savedBooking.getEvent().getId());

        bookingBackupRepository.save(bookingBackup);
    }

    public List<Booking> getAllBookingsByEvent(Long eventId) {
        try {
            chaosMonkeyService.simulateFailure();
            return bookingRepository.findByEventIdIs(eventId);
        } catch (Exception e) {
            return recoverBookingFromBackupByEvent(eventId);
        }
    }

    public List<Booking> recoverBookingFromBackupByEvent(Long eventId) {
        return bookingBackupRepository.findAll().stream().filter
                (backup -> backup.getEventId().equals(eventId)).map(backup -> {
            Booking booking = new Booking();
            booking.setId(backup.getId());
            booking.setUserName(backup.getUserName());
            booking.setEmail(backup.getEmail());

            Event event = eventRepository.findById(backup.getEventId()).orElse(null);
            booking.setEvent(event);
            return booking;
        }).collect(Collectors.toList());
    }

}
