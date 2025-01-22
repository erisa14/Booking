package com.booking.controllers;

import com.booking.models.Booking;
import com.booking.models.Event;
import com.booking.services.BookingService;
import com.booking.services.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookingController {
    @Autowired
    private EventService eventService;

    @Autowired
    private BookingService bookingService;

    //Pamja per te krijuar eventin - get mapping
    @GetMapping("/createEvent")
    public String createEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "createEvent";
    }

    //Form per te krijuar eventin dhe shtuar ne db - post mapping
    @PostMapping("/create")
    public String createEvent(@Valid @ModelAttribute Event event, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("event", event);
            return "createEvent";
        }
        eventService.createEvent(event);
        model.addAttribute("message", "Event created successfully!");
        return "redirect:/events";
    }

    //Pamja per te shfaqur gjithe eventet
    @GetMapping("/events")
    public String listEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "events";
    }

    //Pamja per te krijuar nje rezervim per nje event te zgjedhur - get mapping
    @GetMapping("/create/booking/{eventId}")
    public String showBooking(@PathVariable("eventId") Long eventId, Model model, RedirectAttributes redirectAttributes) {
        Event event = eventService.getEventById(eventId);
        if (event == null) {
            redirectAttributes.addFlashAttribute("error", "Event not found!");
            return "redirect:/events";
        }
        Booking booking = new Booking();
        booking.setEvent(event);
        model.addAttribute("booking", booking);
        model.addAttribute("event", event);
        return "createBooking";
    }

    //Form per te krijuar dhe ruajtur nje rezervim
    @PostMapping("/create/booking")
    public String createBooking(@Valid @ModelAttribute("booking") Booking booking, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "createBooking";
        }
        try {
            bookingService.createBooking(booking);
            model.addAttribute("success", "Booking successfully created!");
            return "redirect:/events";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to create booking: " + e.getMessage());
            return "createBooking";
        }
    }

    //Pamja per te pare gjithe rezervimet
    @GetMapping("/bookings/{eventId}")
    public String getBookingsByEvent(@PathVariable("eventId") Long eventId, Model model) {
        try {
            List<Booking> bookings = bookingService.getAllBookingsByEvent(eventId);
            Event event=eventService.getEventById(eventId);
            model.addAttribute("event", event);
            model.addAttribute("bookings", bookings);
            return "bookings";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to fetch bookings: " + e.getMessage());
            return "redirect:/events";
        }
    }
}
