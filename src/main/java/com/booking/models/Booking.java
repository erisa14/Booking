package com.booking.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "User name is required!")
    private String userName;

    @NotEmpty(message = "Email is required!")
    @Email
    private String email;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty(message = "User name is required!") String getUserName() {
        return userName;
    }

    public void setUserName(@NotEmpty(message = "User name is required!") String userName) {
        this.userName = userName;
    }

    public @NotEmpty(message = "Email is required!") @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "Email is required!") @Email String email) {
        this.email = email;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Booking(Long id, String userName, String email, Event event) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.event = event;
    }
    public Booking() {}
}
