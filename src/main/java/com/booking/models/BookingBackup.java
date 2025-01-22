package com.booking.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "bookings_backup")
public class BookingBackup {
    @Id
    private Long id;

    @NotEmpty(message = "User name is required!")
    private String userName;

    @NotEmpty(message = "Email is required!")
    @Email
    private String email;

    @NotNull
    private Long eventId;

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

    public @NotNull Long getEventId() {
        return eventId;
    }

    public void setEventId(@NotNull Long eventId) {
        this.eventId = eventId;
    }

    public BookingBackup(Long id, String userName, String email, Long eventId) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.eventId = eventId;
    }

    public BookingBackup() {
    }
}
