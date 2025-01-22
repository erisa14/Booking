package com.booking;

import com.booking.models.Booking;
import com.booking.models.BookingBackup;
import com.booking.models.Event;
import com.booking.repositories.BookingBackupRepository;
import com.booking.repositories.BookingRepository;
import com.booking.repositories.EventRepository;
import com.booking.services.BookingService;
import com.booking.services.ChaosMonkeyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class BookingServiceTest {
    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookingBackupRepository bookingBackupRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ChaosMonkeyService chaosMonkeyService;

    @InjectMocks
    private BookingService bookingService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBookingsByEvent_Success() {
        Long eventId = 1L;

        Event event = new Event();
        event.setId(eventId);
        event.setEventName("Test Event");
        event.setEventDate(LocalDateTime.now().plusDays(1));

        Booking booking1 = new Booking(1L, "Erisa Sadikllari", "erisa@example.com", event);
        Booking booking2 = new Booking(2L, "Erisa Sadikllari", "erisa@example.com", event);

        when(bookingRepository.findByEventIdIs(eventId)).thenReturn(Arrays.asList(booking1, booking2));
        doNothing().when(chaosMonkeyService).simulateFailure();

        List<Booking> bookings = bookingService.getAllBookingsByEvent(eventId);

        assertEquals(2, bookings.size());
        verify(bookingRepository, times(1)).findByEventIdIs(eventId);
    }

    @Test
    void testGetAllBookingsByEvent_FallbackToBackup() {
        Long eventId = 1L;

        Event event = new Event();
        event.setId(eventId);
        event.setEventName("Test Event");
        event.setEventDate(LocalDateTime.now().plusDays(1));

        BookingBackup backup1 = new BookingBackup(1L, "Erisa Sadikllari", "erisa@example.com", eventId);
        BookingBackup backup2 = new BookingBackup(2L, "Erisa Sadikllari", "erisa@example.com", eventId);

        doThrow(new RuntimeException("Database failure")).when(chaosMonkeyService).simulateFailure();
        when(bookingBackupRepository.findAll()).thenReturn(Arrays.asList(backup1, backup2));
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        List<Booking> bookings = bookingService.getAllBookingsByEvent(eventId);

        assertEquals(2, bookings.size());
        assertEquals("Erisa Sadikllari", bookings.get(0).getUserName());
        assertEquals("Erisa Sadikllari", bookings.get(1).getUserName());

        verify(bookingBackupRepository, times(1)).findAll();
        verify(eventRepository, times(2)).findById(eventId);
    }


    @Test
    public void testCreateBooking_ValidInput() {
        Booking booking = new Booking();
        booking.setUserName("Erisa");
        booking.setEmail("erisa@example.com");
        Event event = new Event();
        event.setId(1L);
        booking.setEvent(event);

        Mockito.when(bookingRepository.save(Mockito.any(Booking.class))).thenReturn(booking);

        bookingService.createBooking(booking);

        Mockito.verify(bookingRepository, Mockito.times(1)).save(booking);
        Mockito.verify(bookingBackupRepository, Mockito.times(1)).save(Mockito.any(BookingBackup.class));
    }

    @Test
    public void testCreateBooking_InvalidInput() {
        Booking invalidBooking = new Booking();
        invalidBooking.setUserName("");
        invalidBooking.setEmail("invalid-email");
        Event event = new Event();
        event.setId(1L);
        invalidBooking.setEvent(event);

        Assertions.assertThrows(RuntimeException.class, () -> {
            bookingService.createBooking(invalidBooking);
        });

        Mockito.verify(bookingRepository, Mockito.never()).save(Mockito.any(Booking.class));
        Mockito.verify(bookingBackupRepository, Mockito.never()).save(Mockito.any(BookingBackup.class));
    }
}
